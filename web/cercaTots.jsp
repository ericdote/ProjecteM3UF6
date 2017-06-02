<%@page import="java.util.List"%>
<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cercar llibre per ISBN</title>
    </head>
    <body bgcolor="#FFFF99" >

        <%@ include file="myHeader.html" %>
            <center><b>Realitza la cerca per ISBN:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" align="center" border="1">

                <tr>
                    <td align="right">ISBN</td> <td align="right">TITOL</td> <td align="right">AUTOR</td> <td align="right">EDITORIAL</td> <td align="right">ANYEDICIO</td> <td align="right">ESTOC</td>                    
                </tr>               
                <% List<Llibre> resposta = (List<Llibre>) request.getAttribute("cercatTots");%>
                <a ><%if (!resposta.isEmpty()) {
                            for (Llibre llibre : resposta) {
                                %>
                                <tr>
                                    <td><%= llibre.getIsbn()%></td><td><%= llibre.getTitol()%></td><td><%= llibre.getAutor()%></td><td><%= llibre.getEditorial()%></td><td><%= llibre.getAnyEdicio()%></td><td><%= llibre.getEstoc()%></td>
                                </tr>
                                <%
                            }
                        } else {
                            out.print("Sense llibres");
                        }
                    %>

            </table>   



        </form>
        <br>
        <br>
        <a href="index.jsp">Tornar</a>


    </body>
</html>
