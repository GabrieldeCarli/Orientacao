<%-- 
    Document   : CadastrarProfessorView
    Created on : 09/06/2018, 22:17:33
    Author     : Caroline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Cadastrar Professor</title>
    </head>
    <body>

        <h2>Cadastrar Professor</h2>

        <div>
            <% if (request.getAttribute("inserido") != null) { %>
            <p> Inserido com sucesso! </p>
            <% }%>
            <form action="./cadastrar-professor" method="post">
                <label for="nome">Nome do Professor</label>
                <input required type="text" id="nome" name="nome">

                <input type="submit" />
                <a href="./pagina-inicial" id="voltar">
                    Voltar
                </a>
            </form>
        </div>
    </body>
</html>
