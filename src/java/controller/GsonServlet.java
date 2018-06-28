package controller;

import com.google.gson.Gson;
import dao.UserDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Professor;

@WebServlet("/GsonServlet")
public class GsonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GsonServlet() {
        super();
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO user = new UserDAO();
        List<Professor> prof = null;
        try {
            user.cadastraProfessor("MARIA FOGO NO RABO");
            prof = user.buscaProfessor("");
        } catch (Exception ex) {
            Logger.getLogger(GsonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Gson gson = new Gson();
        String json = gson.toJson(prof);
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
