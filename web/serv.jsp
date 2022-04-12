<%-- 
    Document   : serv
    Created on : 4 апр. 2022 г., 13:17:07
    Author     : Andrey Belov
--%>

<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование услуги</title>
    </head>
    <body>
        <form action='servdir.jsp' method="post">
            <jsp:useBean id="serv" class="beans.Service" scope="session" />
            
            <c:if test="${empty param.create}" >
                ${serv.setCon(sessionScope.DataSource)}
                ${serv.load(param.id)}
            </c:if> 
            
            <table>
                <c:choose>
                    <c:when test="${empty param.create}">
                        <tr> <th colspan="2" align="center">Редактирование услуги </th> </tr> 
                    </c:when>
                    <c:otherwise>
                        <tr> <th colspan="2" align="center">Новая услуга </th> </tr> 
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td align="right">Наименование:</td> 
                    <td align="left" ><input type="text" name='name' value='${serv.name}' /> </td>
                </tr>   <tr>
                    <td align="right"><input type="submit" name='save' value='Сохранить' formMethod="post" formAction="servsaveserv"/></td> 
                    <td align="left"><input type="submit" name='cancel' value='Отмена' formMethod="post" formAction="servdir.jsp"/></td>
                </tr>   
            </table>
            <input type="hidden" name="id" value="${param.id}" />
        </form>

    </body>
</html>