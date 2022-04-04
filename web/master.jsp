<%-- 
    Document   : master
    Created on : 4 апр. 2022 г., 15:17:07
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
        <title>Редактирование данных о мастере</title>
    </head>
    <body>
        <form action='masterdir.jsp' method="post">
            <jsp:useBean id="master" class="beans.Master" scope="page" />
            
            <c:if test="${empty param.create}" >
                ${master.setCon(sessionScope.DataSource)}
                ${master.load(param.id)}
            </c:if> 
            
            <table>
                <c:choose>
                    <c:when test="${empty param.create}">
                        <tr> <th colspan="2" align="center">Редактирование данных о мастере </th> </tr> 
                    </c:when>
                    <c:otherwise>
                        <tr> <th colspan="2" align="center">Новый мастер </th> </tr> 
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td align="right">Фамилия:</td> 
                    <td align="left" ><input type="text" name='l_name' value='${master.lName}' /> </td>
                </tr>   
                <tr>
                    <td align="right">Имя:</td> 
                    <td align="left" ><input type="text" name='f_name' value='${master.fName}' /> </td>
                </tr>   
                <tr>
                    <td align="right">Район:</td> 
                    <td align="left" ><input type="text" name='location' value='${master.location}' /> </td>
                </tr>   
                <tr>
                    <td align="right"><input type="submit" name='save' value='Сохранить' formMethod="post" formAction="subsave"/></td> 
                    <td align="left"><input type="submit" name='cancel' value='Отмена' formMethod="post" formAction="masterdir.jsp"/></td>
                </tr>   
            </table>
            <input type="hidden" name="id" value="${param.id}" />
        </form>

    </body>
</html>