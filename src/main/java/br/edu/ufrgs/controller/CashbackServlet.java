package br.edu.ufrgs.controller;

import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.persistence.GerenciadorDeArquivos;
import br.edu.ufrgs.persistence.ExportadorDadosCSV;
import br.edu.ufrgs.service.CalculadoraCashback;
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

@WebServlet("/processa")
@MultipartConfig
public class CashbackServlet extends HttpServlet {

    /**
     * O doPost e acionado quando o usuario seleciona e envia o arquivo CSV na tela.
     * Ele cria um arquivo temporario dinamico no SO para garantir as permissoes no Docker.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        java.nio.file.Path localDestino = null;
        try {
            // 1. Captura o arquivo vindo do input 'name="arquivo"' do index.jsp
            Part filePart = request.getPart("arquivo");

            if (filePart == null || filePart.getSize() == 0) {
                request.setAttribute("erro", "Por favor, selecione um arquivo CSV valido.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            // 2. Cria um arquivo temporario em uma area gravavel pelo SO do Container
            localDestino = java.nio.file.Files.createTempFile("upload_temporario_", ".csv");
            String caminhoCompleto = localDestino.toAbsolutePath().toString();

            // 3. Salva fisicamente os bytes do upload nesse arquivo temporario
            try (InputStream input = filePart.getInputStream()) {
                java.nio.file.Files.copy(input, localDestino, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }

            // 4. Le e consolida os clientes usando o Gerenciador (que orquestra Leitor + Consolidador)
            GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
            Collection<Cliente> clientesConsolidados = gerenciador.lerVendas(caminhoCompleto);

            // 5. Exclui o arquivo temporario apos a leitura para liberar espaco
            java.nio.file.Files.deleteIfExists(localDestino);

            // 6. Prepara o motor de regras com a Fabrica e a Calculadora
            FabricaCliente fabrica = new FabricaCliente();
            CalculadoraCashback calculadora = new CalculadoraCashback();
            List<Cliente> listaCalculada = new ArrayList<>();

            // 7. Loop de Negocio: transforma clientes genericos em instancias reais de Tiers via polimorfismo
            for (Cliente cOriginal : clientesConsolidados) {
                Cliente clienteTipado = fabrica.fabricarCliente(cOriginal);
                clienteTipado.setCashBackAcumulado(calculadora.calcularCashbackFinal(clienteTipado));
                listaCalculada.add(clienteTipado);
            }

            // 8. Armazena o estado na Sessao para o fluxo de renderizacao (doGet)
            HttpSession session = request.getSession();
            session.setAttribute("listaCompleta", listaCalculada);

            // Padrao Post-Redirect-Get: redireciona com seguranca limpando a requisicao POST
            response.sendRedirect("processa");

        } catch (Exception e) {
            if (localDestino != null) {
                java.nio.file.Files.deleteIfExists(localDestino);
            }
            request.setAttribute("erro", "Erro ao processar o arquivo: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * O doGet gerencia a exibicao da interface, buscas com filtro e download do relatorio.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String acao = request.getParameter("acao");

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

        // RF05 - Fluxo de exportacao do arquivo final CSV para o usuario
        if ("exportar".equals(acao)) {
            java.nio.file.Path pathRelatorio = null;
            try {
                pathRelatorio = java.nio.file.Files.createTempFile("relatorio_fidelidade_", ".csv");
                String caminhoNoServidor = pathRelatorio.toAbsolutePath().toString();

                ExportadorDadosCSV escritorCSV = new ExportadorDadosCSV();
                escritorCSV.exportaRelatorio(listaCompleta, caminhoNoServidor);

                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_fidelidade.csv\"");

                java.nio.file.Files.copy(pathRelatorio, response.getOutputStream());
                response.getOutputStream().flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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
