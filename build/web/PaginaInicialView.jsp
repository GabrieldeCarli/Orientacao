<%-- 
    Document   : PaginaInicialView
    Created on : 09/06/2018, 20:00:43
    Author     : Caroline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Página Inicial</title>
    </head>

    <body>

        <h2>Página Inicial</h2>

        <div>
            <form action="./buscar-professor" method="post">
                <button class="button" name="Buscar Professor">Buscar Professor</button>
            </form>
            <form action="./buscar-orientacao" method="post">
                <button class="button">Buscar Orientação</button>
            </form>
            <form action="./cadastrar-professor" method="post">   
                <button class="button">Cadastrar Professor</button>
            </form> 
            <form action="./cadastrar-orientacao" method="post">
                <button class="button">Cadastrar Orientação</button>
            </form>
        </div>

    </body>
</html>
