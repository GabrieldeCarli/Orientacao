/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.OrientacaoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Orientacao;

/**
 *
 * @author renil
 */
@WebServlet(name = "OrientacaoResource", urlPatterns = {"/orientacao"})
public class OrientacaoResource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("tema") != null && request.getParameter("aluno") != null && request.getParameter("professor") != null) {
            request.setAttribute("orientacoes", OrientacaoDAO.buscaOrientacao(request.getParameter("aluno"), request.getParameter("professor"), request.getParameter("tema")));
        }

        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("BuscarOrientacaoView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (session.getAttribute("usuario.logado") == null) {
            response.sendRedirect("./login");
            return;
        }

        if (request.getParameter("tema") != null && request.getParameter("aluno") != null && request.getParameter("professor_id") != null) {

            Orientacao ori = new Orientacao(request.getParameter("aluno"), Integer.parseInt(request.getParameter("professor_id")), request.getParameter("tema"));

            OrientacaoDAO.cadastraOrientacao(ori);
            request.setAttribute("inserido", true);

            Gson gson = new Gson();
            String json = gson.toJson(ori);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

        request.getRequestDispatcher("CadastrarOrientacaoView.jsp").forward(request, response);
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
