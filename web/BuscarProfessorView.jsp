<%-- 
    Document   : BuscarProfessorView
    Created on : 09/06/2018, 22:10:50
    Author     : Caroline
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Professor"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Buscar Professor</title>
    </head>    

    <body>

        <h2>Buscar Professor</h2>

        <div>
            <form action="./buscar-professor" method="post">
                <label for="nome">Nome do Professor</label>
                <input type="text" id="nome" name="nome">

                <input type="submit" />
                <a href="./pagina-inicial" id="voltar">
                    Voltar
                </a>
            </form>


            <ul>
                <% if (request.getAttribute("professores") != null) {
                        List<Professor> professores = (ArrayList<Professor>) request.getAttribute("professores");
                        for (Professor professor : professores) {
                %>
                <li><%= professor.getNome()%></li>
                    <% }
                        }%>
            </ul>

        </div>
    </body>
</html>
