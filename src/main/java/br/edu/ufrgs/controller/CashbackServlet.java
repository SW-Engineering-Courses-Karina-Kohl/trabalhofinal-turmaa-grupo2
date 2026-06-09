package br.edu.ufrgs.controller;

import br.edu.ufrgs.persistence.LeitorCSV;
import br.edu.ufrgs.model.Cliente;
import br.edu.ufrgs.persistence.GerenciadorDeArquivos;
import br.edu.ufrgs.service.CalculadoraCashback;
import br.edu.ufrgs.model.Venda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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

            // 2. Link do Backend: Processa o motor de regras e devolve a lista de clientes consolidados
            CalculadoraCashback calculadora = new CalculadoraCashback();
            List<Cliente> listaCalculada = calculadora.calcularCashbackFinal(vendasBrutas);

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

    }
}
