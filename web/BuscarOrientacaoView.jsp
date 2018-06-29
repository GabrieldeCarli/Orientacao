<%-- 
    Document   : BuscarOrientacaoView
    Created on : 09/06/2018, 22:00:54
    Author     : Caroline
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Orientacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Buscar Orientação</title>
    </head>

    <body>
        <h2>Buscar Orientação</h2>

        <div>
            <form action="./orientacao" method="get">
                <label for="nome">Tema</label>
                <input type="text" id="tema" name="tema">
                <label for="nomeA">Nome do Aluno</label>
                <input type="text" id="nomeA" name="aluno">
                <label for="nomeP">Nome do Professor</label>
                <input type="text" id="nomeP" name="professor">

                <input type="submit" />
                <a href="./menu" id="voltar">
                    Voltar
                </a>
            </form>

            <ul>
                <% if (request.getAttribute("orientacoes") != null) {
                        List<Orientacao> orientacoes = (ArrayList<Orientacao>) request.getAttribute("orientacoes");
                        for (Orientacao orientacao : orientacoes) {
                %>
                <li><%= orientacao.getTema()%></li>
                <li><%= orientacao.getAluno()%></li>
                <li><%= orientacao.getProfessor()%></li>
                    <% }
                        }%>
            </ul>
        </div>

    </body>
</html>
