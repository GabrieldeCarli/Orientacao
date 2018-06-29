/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.ProfessorDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Professor;

/**
 *
 * @author renil
 */
@WebServlet(name = "ProfessorResource", urlPatterns = {"/professor"})
public class ProfessorResource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("nome") != null) {
            request.setAttribute("professores", ProfessorDAO.buscaProfessor(request.getParameter("nome")));
        }

        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("BuscarProfessorView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("nome") != null) {
            Professor pro = new Professor(request.getParameter("nome"));
            
            ProfessorDAO.cadastraProfessor(pro);
            request.setAttribute("inserido", true);
            
            Gson gson = new Gson();
            String json = gson.toJson(pro);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("CadastrarProfessorView.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
}
