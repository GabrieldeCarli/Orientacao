<%-- 
    Document   : LoginView
    Created on : 09/06/2018, 18:30:42
    Author     : Caroline
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Login</title>
    </head>
    <body>        
        <div>
            <% if (request.getParameter("erro") != null) { %>
                <p>Erro ao fazer login</p>
            <%  } %>
            <form action="./login" method="post">            
                Usuário: <input required="" type="text" name="email"/> <%--email type--%>
                <br> 
                Senha: <input required="" type="password" name="senha"/> 
                <input type="submit" value="Login">
            </form>
        </div>    
    </body>
</html>
