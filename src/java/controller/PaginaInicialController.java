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

/**
 *
 * @author Caroline
 */
@WebServlet(urlPatterns = {"/pagina-inicial"})
public class PaginaInicialController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Valida login
        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        String parameter = request.getParameter("opcao");

        if (parameter != null && !parameter.isEmpty()) {
            switch (parameter) {
                case "Cadastrar Orientacao":
                    request.getRequestDispatcher("CadastrarOrientacaoController.java").forward(request, response);
                    break;
                    
                case "Cadastrar Professor":
                    request.getRequestDispatcher("CadastrarProfessorController.java").forward(request, response);
                    break;
                    
                case "Buscar Professor":
                    request.getRequestDispatcher("BuscarProfessorController.java").forward(request, response);
                    break;
                    
                case "Buscar Orientacao":
                    request.getRequestDispatcher("BuscarOrientacaoController.java").forward(request, response);
                    break;
            }
        }

        request.getRequestDispatcher("PaginaInicialView.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
