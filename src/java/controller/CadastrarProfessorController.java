/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caroline
 */
@WebServlet(urlPatterns = {"/cadastrar-professor"})
public class CadastrarProfessorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("nome") != null) {
            UserDAO con = new UserDAO();
            con.cadastraProfessor(request.getParameter("nome"));
            request.setAttribute("inserido", true);
        }
       
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("CadastrarProfessorView.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
