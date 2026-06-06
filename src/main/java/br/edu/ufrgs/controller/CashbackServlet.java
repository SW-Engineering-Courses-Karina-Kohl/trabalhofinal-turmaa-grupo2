package br.edu.ufrgs.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/processa")
public class CashbackServlet extends HttpServlet  {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String acao = request.getParameter("acao");
          HttpSession session = request.getSession();
          List<Resultado> listaCompleta = (List<Resultado>) session.getAttribute("listaCompleta");

          if (listaCompleta == null){
            response.sendRedirect("index.jsp");
            return;
          }
          // Exportar arquivo final CSV (RF05)
          if ("exportar".equals(acao)){
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"relatorio_fidelidade.csv\"");

            LeitorCSV escritorCSV = new LeitorCSV();
            escritorCSV.exportarDadosCSV(listaCompleta, response.getWriter());
            return;
          }

          String filtrarClienteId = request.getParameter("clienteId");
          if (filtrarClienteId != null && !filtrarClienteId.isEmpty()){
            List<Resultado> listaFiltrada = listaCompleta.stream()
                    .filter(r -> r.getClienteId().equals(filtrarClienteId))
                    .toList();

                request.setAttribute("listaFiltrada", listaFiltrada);
                else {
                    request.setAttribute("listaFiltrada", listaCompleta);
                }

                request.getRequestDispatcher("index.jsp").forward(request, response);
          
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
