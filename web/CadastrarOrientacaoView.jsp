<%-- 
    Document   : CadastrarOrientacaoView
    Created on : 09/06/2018, 22:23:05
    Author     : Caroline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Cadastrar Orientação</title>
    </head>
    <body>

        <h2>Cadastrar Orientação</h2>

        <div>
            <% if (request.getAttribute("inserido") != null) { %>
            <p> Inserido com sucesso! </p>
            <% }%>
            <form action="./orientacao" method="post">
                <label for="nome">Tema</label>
                <input required type="text" id="tema" name="tema">
                <label for="nomeA">Nome do Aluno</label>
                <input required type="text" id="aluno" name="aluno">
                <label for="nomeP">ID do Professor</label>
                <input  type="text" id="professor_id" name="professor_id">

                <input type="submit" />
                <a href="./menu" id="voltar">
                    Voltar
                </a>



            </form>
        </div>
    </body>
</html>