package br.edu.ufrgs.controller;

import br.edu.ufrgs.persistence.LeitorCSV;
import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.persistence.ExportadorDadosCSV;
import br.edu.ufrgs.service.CalculadoraCashback;
import br.edu.ufrgs.service.ConsolidadorClientes;
import br.edu.ufrgs.service.FabricaCliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet("/processa")
public class CashbackServlet extends HttpServlet {

    /**
     * O doPost é acionado quando o usuário envia o arquivo CSV na tela.
     * Ele lê, calcula as regras de cashback/tier e salva o resultado na sessão.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pega o caminho do arquivo enviado pelo formulário do JSP
        String caminhoArquivo = request.getParameter("caminhoArquivo");

        if (caminhoArquivo == null || caminhoArquivo.isEmpty()) {
            request.setAttribute("erro", "Por favor, informe o caminho do arquivo CSV.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        try {
            LeitorCSV leitorCSV = new LeitorCSV();
            
            // 1. Link do Backend: Lê o arquivo de vendas brutas
            List<String> vendasBrutas = leitorCSV.lerArquivo(caminhoArquivo); 

                        if (vendasBrutas.isEmpty()) {
                                request.setAttribute("erro", "Nenhum dado foi lido do CSV. Confira se o caminho está correto e se o arquivo está montado no container.");
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                return;
                        }

            ConsolidadorClientes consolidador = new ConsolidadorClientes();
            Collection<Cliente> clientesConsolidados = consolidador.consolidar(vendasBrutas);

                        FabricaCliente fabricaCliente = new FabricaCliente();
                        List<Cliente> listaCalculada = new ArrayList<>();
                        for (Cliente clienteOriginal : clientesConsolidados) {
                                Cliente cliente = fabricaCliente.fabricarCliente(clienteOriginal);

                                // 2. Link do Backend: Processa o motor de regras e devolve a lista de clientes consolidados
                                CalculadoraCashback calculadora = new CalculadoraCashback();
                                cliente.setCashBackAcumulado(calculadora.calcularCashbackFinal(cliente));
                                listaCalculada.add(cliente);
                        }


            // 3. Salva a lista gerada na sessão para o doGet poder usá-la
            HttpSession session = request.getSession();
            session.setAttribute("listaCompleta", listaCalculada);

            // 4. Redireciona para o doGet exibir os dados na tela com segurança (Padrão Post-Redirect-Get)
            response.sendRedirect("processa");

        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao processar o arquivo: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    
    // 1. Recupera a lista calculada da sessão
    List<Cliente> listaCompleta = (List<Cliente>) session.getAttribute("listaCompleta");

    if (listaCompleta == null) {
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
    }

    String acao = request.getParameter("acao");

    // RF05 - Exportar arquivo final CSV
    if ("exportar".equals(acao)) {
        // Caminho temporário gravável dentro do container/runtime
        String caminhoNoServidor = Paths.get(System.getProperty("java.io.tmpdir"), "relatorio_fidelidade.csv").toString();

        // INSTÂNCIA E MÉTODO DO LUIS: Mantidos exatamente como ele criou (Lista + String caminho)
        ExportadorDadosCSV escritorCSV = new ExportadorDadosCSV();
        escritorCSV.exportaRelatorio(listaCompleta, caminhoNoServidor);

        // CONFIGURAÇÃO HTTP: Avisa o navegador que um arquivo CSV está chegando para download
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_fidelidade.csv\"");
        
        // FLUXO DE TRANSMISSÃO: Pega o arquivo gerado pelo Luis no HD e transmite para o usuário
        Path path = Paths.get(caminhoNoServidor);
        try {
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            // Se der erro ao ler o arquivo, joga para o console para debug
            e.printStackTrace();
        }
        return;
    }

    // RF04 - Filtrar por cliente_id ou listar tudo
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
