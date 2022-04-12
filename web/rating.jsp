<%-- 
    Document   : rating
    Created on : 4 апр. 2022 г., 14:32:05
    Author     : Andrey Belov
--%>

<%@page import="beans.Rating"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Редактирование оценки</title>
    </head>
    <body>
        <form action='#' method="post">
            <jsp:useBean id="rating" class="beans.Rating" scope="session" />

            <c:if test="${empty param.create}" >
                <br/>
            </c:if> 
            <c:if test="${!empty param.master_rbt}" >
                ${rating.master.load(sessionScope.DataSource, param.master_rbt)}
            </c:if> 
            <c:if test="${!empty param.serv_rbt}" >
                ${rating.service.load(sessionScope.DataSource, param.serv_rbt)}
            </c:if> 

            <table>
                <c:choose>
                    <c:when test="${empty param.create}">
                        <tr> <th colspan="2" align="center">Редактирование оценки </th> </tr> 
                            </c:when>
                            <c:otherwise>
                        <tr> <th colspan="2" align="center">Новая оценка </th> </tr> 
                            </c:otherwise>
                        </c:choose>
                <tr>
                    <td align="right">Мастер:</td> 
                    <td align="left" ><div name = 'master' style="border: 1px solid black;height: 20px">${rating.master.LName} </div> </td>
                    <td> <input type="submit" name='mastersel' value='Выбрать' formMethod="get" formAction="masterdir.jsp"/></td>
                </tr>  
                <tr>
                    <td align="right">Услуга:</td> 
                    <td align="left" ><div name = 'service' style="border: 1px solid black;height: 20px">${rating.service.name} </div> </td>
                    <td> <input type="submit" name='servsel' value='Выбрать' formMethod="get" formAction="servdir.jsp"/></td>
                </tr>  
                <tr>
                    <td align="right">Дата:</td> 
                    <td align="left" ><input type="text" name='date' value='${rating.ratingDate.toString()}' /> </td>
                </tr>
                <tr>
                    <td align="right">Оценка:</td> 
                    <td align="left" ><input type="text" name='mark' value='${rating.mark}' /> </td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" name='save' value='Сохранить' formMethod="post" formAction="servsave"/></td> 
                    <td align="left"><input type="submit" name='cancel' value='Отмена' formMethod="post" formAction="servdir.jsp"/></td>
                </tr>   
            </table>
            <input type="hidden" name="servid" value="${rating.service.serviceId}" />
<!--            <input type="hidden" name="id" value="" />-->
        </form>
    </body>
</html>