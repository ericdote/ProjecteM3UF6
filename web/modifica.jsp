<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
Aquesta .jsp només apareix un cop hem cercat per ISBN.
-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifica llibre</title>
    </head>
    <body bgcolor="#FFFF99" >
        <% Llibre resposta = (Llibre) request.getAttribute("cercat");%>
        <%@ include file="myHeader.html" %>
        <form action="GestioLlibres?accio=modificar" method="post">          
            <center><b>Dades del llibre:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" border="0" align="center">
                <tr>
                    <td align="right">Títol:</td>
                    <td><input type="Text" name="titol_" size="30" value="<%=resposta == null ? "" : resposta.getTitol()%>" ></td>
                </tr>
                <tr>
                    <td align="right">Autor:</td>
                    <td><input type="Text" name="autor_" size="30" value="<%=resposta == null ? "" : resposta.getAutor()%>" ></td>
                </tr>
                <tr>
                    <td align="right">Editorial:</td>
                    <td><input type="Text" name="editorial_" size="20" value="<%=resposta == null ? "" : resposta.getEditorial()%>" ></td> 
                </tr>               
                <tr>
                    <td align="right">Any edició:</td>
                    <td><input type="Text" name="anyEdicio_" size="4" value="<%=resposta == null ? "" : resposta.getAnyEdicio()%>" ></td>
                </tr>
                <tr>
                    <td align="right">Estoc:</td>
                    <td><input type="Text" name="estoc_" size="3" value="<%=resposta == null ? "" : resposta.getEstoc()%>" ></td>
                </tr>
                <tr>
                    <td align="right">ISBN:</td>
                    <td><input type="Text" name="isbn_" size="13" value="<%=resposta == null ? "" : resposta.getIsbn()%>" readonly></td>
                    <td colspan="2" align="center"><input type="Submit" value="Modificar"></td>
                </tr>               
                <% String resposta2 = (String) request.getAttribute("modificat");%>
                <a ><%=(resposta2 == null) ? "" : resposta2%> </a>
            </table>   
        </form>
        <br>
        <br>
        <a href="index.jsp">Tornar</a>
    </body>
</html>
