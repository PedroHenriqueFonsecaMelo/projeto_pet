/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAOS.alunoDAO;
import controle.servicos;
import entidades.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fatec
 */
public class cadastroAluno extends HttpServlet {

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

        Aluno aluno = new Aluno();
        alunoDAO dao = new alunoDAO();
        int matricula = Integer.parseInt(request.getParameter("Matricula"));
        String nome = request.getParameter("Nome");
        String situacao = request.getParameter("situacao");
        String disciplina = request.getParameter("disciplina");

        aluno.setDisciplina(disciplina);
        aluno.setNome(nome);
        aluno.setStatus(situacao);
        aluno.setMatricula(matricula);
        dao.incluir(aluno);

        List<Aluno> lista = dao.listar();
        StringBuilder br = new StringBuilder();

        for (int i = 0; i < lista.size(); i++) {
            br.append(lista.get(i).toString()).append("<br>");
        }

        request.setAttribute("empresa", br.toString());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pesquisa.jsp");
        dispatcher.forward(request, response);

    }

    private void v(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameterMap().toString());

        String insertInto = "INSERT INTO `tbl_aluno`(`matricula`, `nome`, `disciplina`, `situacao`) values ("
                + request.getParameter("Matricula") + ",'"
                + request.getParameter("Nome") + "','"
                + request.getParameter("disciplina") + "','"
                + request.getParameter("situacao") + "');";

        servicos s = new servicos();
        s.exe(insertInto);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cadastroAluno</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Cadastrado com sucesso</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
