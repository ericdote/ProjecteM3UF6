<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cercar llibre per Titol</title>
    </head>
    <body bgcolor="#FFFF99" >

        <% Llibre resposta = (Llibre) request.getAttribute("cercatTitol");%>
        <%@ include file="myHeader.html" %>

        <form action="GestioLlibres?accio=cercarTitol" method="post">            

            <center><b>Dades del llibre:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" border="0" align="center">
                <tr>
                    <td align="right">ISBN</td>
                    <td><input type="Text" name="isbn_" size="30" value="<%=resposta == null ? "" : resposta.getIsbn()%>" readonly></td>
                </tr>
                <tr>
                    <td align="right">Autor:</td>
                    <td><input type="Text" name="autor_" size="30" value="<%=resposta == null ? "" : resposta.getAutor()%>" readonly></td>
                </tr>
                <tr>
                    <td align="right">Editorial:</td>
                    <td><input type="Text" name="editorial_" size="20" value="<%=resposta == null ? "" : resposta.getEditorial()%>" readonly></td> 
                </tr>               
                <tr>
                    <td align="right">Any edició:</td>
                    <td><input type="Text" name="anyEdicio_" size="4" value="<%=resposta == null ? "" : resposta.getAnyEdicio()%>" readonly></td>
                </tr>
                <tr>
                    <td align="right">Estoc:</td>
                    <td><input type="Text" name="estoc_" size="3" value="<%=resposta == null ? "" : resposta.getEstoc()%>" readonly></td>
                </tr>
                <tr>
                    <td align="right">Títol:</td>
                    <td><input type="Text" name="titol_" size="13" value="<%=resposta == null ? "" : resposta.getTitol()%>"></td>
                    <td colspan="2" align="center"><input type="Submit" value="Cercar"></td>
                </tr>               

            </table>   



        </form>
        <br>
        <br>
        <a href="index.jsp">Tornar</a>


    </body>
</html>
