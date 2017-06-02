<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar llibre per ISBN</title>
    </head>
    <body bgcolor="#FFFF99" >


        <%@ include file="myHeader.html" %>

        <form action="GestioLlibres?accio=eliminar" method="post">            

            <center><b>Dades del llibre:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" border="0" align="center">               
                <tr>
                    <td align="right">ISBN:</td>
                    <td><input type="Text" name="isbn_" size="13"></td>
                    <td colspan="2" align="center"><input type="Submit" value="Eliminar"></td>
                </tr>               

            </table>   


            <% String resposta = (String) request.getAttribute("eliminat");%>
            <a ><%=(resposta == null) ? "" : resposta%> </a>
        </form>
        <br>
        <br>
        <a href="index.jsp">Tornar</a>


    </body>
</html>
