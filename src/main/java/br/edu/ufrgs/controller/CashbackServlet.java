package br.edu.ufrgs.controller;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.persistence.LeitorCSV;
import br.edu.ufrgs.persistence.ExportadorDadosCSV;
import br.edu.ufrgs.service.CalculadoraCashback;
import br.edu.ufrgs.service.ConsolidadorClientes;
import br.edu.ufrgs.service.FabricaCliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Controlador principal (Controller) responsável pelo gerenciamento do fluxo
 * de processamento, consolidação e filtragem de cashbacks de clientes.
 * * <p>Esta classe estende {@link HttpServlet} e atua como intermediária entre a 
 * interface do usuário (JSP) e a camada de regras de negócio, gerenciando o ciclo
 * de vida das requisições de upload de arquivos CSV e gerência de estado via sessão.</p>
 * @author Grupo 2 - Turma A
 */
@WebServlet("/processa")
@MultipartConfig // Ativa o suporte para receber arquivos binários do formulário JSP
public class CashbackServlet extends HttpServlet {

    /**
     * O doPost é acionado quando o usuário seleciona e envia o arquivo CSV na tela.
     * Ele cria um arquivo temporário dinâmico no SO para garantir as permissões no Docker.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.nio.file.Path localDestino = null;
        try {
            // 1. Captura o arquivo vindo do input 'name="arquivo"' do index.jsp
            Part filePart = request.getPart("arquivo"); 

            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("erro", "Por favor, selecione um arquivo CSV válido.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            // Cria um arquivo temporário em uma área segura e 100% gravável pelo SO do Container
            localDestino = java.nio.file.Files.createTempFile("upload_temporario_", ".csv");
            String caminhoCompleto = localDestino.toAbsolutePath().toString(); 

            // 3. Salva fisicamente os bytes do upload nesse arquivo temporário dinâmico
            try (InputStream input = filePart.getInputStream()) {
                java.nio.file.Files.copy(input, localDestino, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }

            // 4. Utiliza a função original LeitorCSV passando a String do caminho dinâmico obtido
            LeitorCSV leitorCSV = new LeitorCSV();
            List<String> vendasBrutas = leitorCSV.lerArquivo(caminhoCompleto); 

            // 5. Exclui o arquivo temporário imediatamente após a leitura para liberar espaço
            java.nio.file.Files.deleteIfExists(localDestino);

            // 6. Consolida as linhas de texto brutas em objetos Cliente genéricos
            ConsolidadorClientes consolidador = new ConsolidadorClientes();
            Collection<Cliente> clientesConsolidados = consolidador.consolidar(vendasBrutas);
            
            // 7. Prepara o motor de regras com a Fábrica e a Calculadora
            FabricaCliente fabrica = new FabricaCliente();
            CalculadoraCashback calculadora = new CalculadoraCashback();
            List<Cliente> listaCalculada = new ArrayList<>();

            // 8.Transforma clientes genéricos em instâncias reais de Tiers via polimorfismo
            for (Cliente cOriginal : clientesConsolidados) {
                Cliente clienteTipado = fabrica.fabricarCliente(cOriginal);
                clienteTipado.setCashBackAcumulado(calculadora.calcularCashbackFinal(clienteTipado));
                listaCalculada.add(clienteTipado);
            }

            // 9. Armazena o estado completo na Sessão para o fluxo de renderização (doGet)
            HttpSession session = request.getSession();
            session.setAttribute("listaCompleta", listaCalculada);

            // Redireciona com segurança limpando a requisição POST
            response.sendRedirect("processa");

        } catch (Exception e) {
            // Bloco de segurança para garantir que o arquivo seja deletado caso o processamento falhe no meio
            if (localDestino != null) {
                java.nio.file.Files.deleteIfExists(localDestino);
            }
            request.setAttribute("erro", "Erro ao processar o arquivo: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * O doGet gerencia a exibição da interface, buscas com filtro e download do relatório.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");
        //botao para limpar os dados deixados pelo csv
        if ("limpar".equals(acao)) {
            session.removeAttribute("listaCompleta");
            response.sendRedirect("processa");
            return;
        }

        List<Cliente> listaCompleta = (List<Cliente>) session.getAttribute("listaCompleta");

        if (listaCompleta == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        

        // RF05 - Fluxo de exportação do arquivo final CSV para o usuário.
        if ("exportar".equals(acao)) {
            java.nio.file.Path pathRelatorio = null;
            try {
                //Cria um arquivo temporário dinâmico também para a geração do relatório ExportadorCSV
                pathRelatorio = java.nio.file.Files.createTempFile("relatorio_fidelidade_", ".csv");
                String caminhoNoServidor = pathRelatorio.toAbsolutePath().toString();

                // Executa o exportadorCSV gerando o arquivo com o formatador dele
                ExportadorDadosCSV escritorCSV = new ExportadorDadosCSV();
                escritorCSV.exportaRelatorio(listaCompleta, caminhoNoServidor);

                // Seta os cabeçalhos HTTP para forçar a janela de download no navegador do usuário
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_fidelidade.csv\"");
                
                // Faz a ponte de streaming transferindo o arquivo gerado pelo ExportadorCSV direto para a resposta HTTP
                java.nio.file.Files.copy(pathRelatorio, response.getOutputStream());
                response.getOutputStream().flush();
                
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Garante que o arquivo temporário do relatório seja apagado após o download terminar
                if (pathRelatorio != null) {
                    java.nio.file.Files.deleteIfExists(pathRelatorio);
                }
            }
            return;
        }

        // RF04 - Motor de busca e filtragem por ID do cliente
        String filtrarClienteId = request.getParameter("clienteId");
        List<Cliente> listaParaExibicao;

        if (filtrarClienteId != null && !filtrarClienteId.isEmpty()) {
            try {
                int idFiltro = Integer.parseInt(filtrarClienteId);
                listaParaExibicao = listaCompleta.stream()
                        .filter(r -> r.getIdCliente() == idFiltro)
                        .toList();
            } catch (NumberFormatException ex) {
                listaParaExibicao = new ArrayList<>();
            }
        } else {
            listaParaExibicao = listaCompleta;
        }

        request.setAttribute("listaFiltrada", listaParaExibicao);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
