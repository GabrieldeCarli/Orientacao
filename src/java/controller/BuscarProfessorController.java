/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;

/**
 *
 * @author Caroline
 */
@WebServlet(urlPatterns = {"/buscar-professor"})
public class BuscarProfessorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("nome") != null) {
            UserDAO con = new UserDAO();
            request.setAttribute("professores", con.buscaProfessor(request.getParameter("nome")));
        }

        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("BuscarProfessorView.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
