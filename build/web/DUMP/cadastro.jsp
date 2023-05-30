<%-- 
    Document   : menu
    Created on : 03/05/2023, 20:09:55
    Author     : Fatec
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <form method="post" action="./cadastroAluno">
            <h1>Cadastro de Anulo</h1>
            <input type="text" name="Nome" placeholder="Nome">
            <input type="number" name="Matricula" placeholder="Matricula">
            <input type="text" name="situacao" placeholder="situacao">
            <input type="text" name="disciplina" placeholder="disciplina">
            
            <button type="submit" >CADASTRAR</button>
            <button onClick="history.go(0);">Refresh Page</button>
        </form>
    </body>
</html>
