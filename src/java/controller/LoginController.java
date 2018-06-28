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
import model.Usuario;

/**
 *
 * @author Caroline
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("LoginView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO con = new UserDAO();

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = con.buscaPorEmailESenha(email, senha);


        // Se usuário existir
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario.logado", usuario);
            response.sendRedirect("./pagina-inicial");
        } else {
            response.sendRedirect("./login?erro=true");
        }

    }
}
