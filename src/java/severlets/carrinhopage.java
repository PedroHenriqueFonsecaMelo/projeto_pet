/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package severlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.buf.StringUtils;

/**
 *
 * @author Pedro Henrique
 */
public class carrinhopage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Set<Entry<String, String[]>> aux = request.getParameterMap().entrySet();

        for (Entry as : aux) {
            System.out.println(as.getKey());
            String[] helloWorld = (String[]) as.getValue();
            System.out.println(Arrays.toString(helloWorld));
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet carrinhopage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet carrinhopage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String toCarrinhoTable(String nome, String preco) {
        StringBuilder str = new StringBuilder();

        str.append(
                "<tr><td><div class=\"product\"><imgsrc=\"https://picsum.photos/200/200\""
                + "alt=\"\"><div class=\"info\"><div class=\"nome\">"
                + nome);
        str.append("</div><div class=\"categoria\">categoria</div></div></div></td><td>R$:");
        str.append(preco);
        str.append("</td><td><divclass=\"qtde\"><buttoniclass=\"bxbx-minus\">"
                + "</button><span class=\"num\">1</span><button i class=\"bxbx-plus\">"
                + "</button></div></td>"
                + "<td>" +
                str.append(preco)
                + "</td><td><button class=\"remove\">"
                + "<i class='bxbx-x'></i></button></td></tr>");
        return str.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
