<%-- 
    Document   : Servdir
    Created on : 30 мар. 2022 г., 20:29:29
    Author     : Andrey Belov
--%>

<%@page import="beans.Service"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список услуг</title>
    </head>
    <body>
                <%request.setCharacterEncoding("UTF-8");%> 
        
                <jsp:useBean id="servDir" class="beans.ServiceDir" scope="session" />
        
                <c:if test="${!empty param.findstr}"  var="val" scope="session" >
                    ${servDir.setFindStr(param.findstr)}
                    ${servDir.fill( sessionScope.DataSource)}
                    <% 
                            for(Service sb:  servDir.getServices()){
                            System.out.println("--- "+ sb.getName());
                        }
                    %>
                </c:if>

                        <h1>Список услуг</h1>
                        <!<!-- форма поиска -->
                <form action="servdir.jsp" method="get">
                    <table>
                        <tr>
                            <td aligh="left">Поиск: &nbsp; <input type="text" name="findstr" value="" /><td>
                            <td aligh="left"> <input type="submit" value="Найти" /><td>
                        </td>
                    </table>
                </form>
                <form action='#' method="get">
                    <table>
                        <tr>
                            <td align="left"><input type="submit" value="Выбрать" formMethod="get" formAction="rating.jsp"/></td>
                            <td align="left"><input type="submit" name="create" value="Создать..." formMethod="get" formAction="serv.jsp"/></td>
                        </tr>
                    </table>
                    <hr/>    
                    <table border="1" >
                        <th> </th><th>Наименование</th><th>Ред.</th><th>Удалить</th>
                        <c:forEach var="serv" items="${serviceDir.services}" >
                                    <jsp:useBean id="serv" class="beans.Service" scope="page" />
                                    <tr>
                                        <td><input type="radio"  name="serv_rbt" value="${serv.serviceId}" /></td>
                                        <td>${serv.name}</td>
                                        <td><a href="serv.jsp?id=${serv.serviceId}">Изменить</a></td>
                                        <td><a href="servDel?id=${serv.serviceId}">Удалить</a></td>
                                    </tr>
                                </c:forEach>

                    </table>

                </form>

            </body>
</html>
        