<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cercar llibre per ISBN</title>
    </head>
    <body bgcolor="#FFFF99" >

        <% Llibre resposta = (Llibre) request.getAttribute("cercat");%>
        <%@ include file="myHeader.html" %>

        <form action="GestioLlibres?accio=cercar" method="post">            

            <center><b>Realitza la cerca per ISBN:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" border="0" align="center">

                <tr>
                    <td align="right">ISBN:</td>
                    <td><input type="Text" name="isbn_" size="13" value="<%=resposta == null ? "" : resposta.getIsbn()%>"></td>
                    <td colspan="2" align="center"><input type="Submit" value="Cercar"></td>
                </tr>               

            </table>   



        </form>
        <br>
        <br>
        <a href="index.jsp">Tornar</a>


    </body>
</html>
