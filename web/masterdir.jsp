<%-- 
    Document   : masterdir
    Created on : 4 апр. 2022 г., 15:24:56
    Author     : Andrey Belov
--%>

<%@page import="beans.Master"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Справочник мастеров</title>
    </head>
    <body>
        <%request.setCharacterEncoding("UTF-8");%>

        <jsp:useBean id="masterDir" class="beans.MasterDir" scope="session" />

        <c:if test="${!empty param.findstr}"  var="val" scope="session" >
            ${masterDir.setFindStr(param.findstr)}
            ${masterDir.fill( sessionScope.DataSource)}
        </c:if> 

        <h1>Справочник мастеров</h1>
        <!--  Форма поиска -->
        <form action="masterdir.jsp" method="get">
            <table>
                <tr>
                    <td align="left">Поиск: &nbsp; <input type="text" name='findstr' value='' /></td> 
                    <td align="left"><input type="submit" value="Найти"/></td>
                </tr>   
            </table>
        </form>
        <form action='#' method="get">
            <table>
                <tr>
                    <td align="left"><input type="submit" value="Выбрать" formMethod="get" formAction="rating.jsp"/></td> 
                    <td align="left"><input type="submit" name="create" value="Создать..." formMethod="get" formAction="master.jsp"/></td>
                </tr>   
            </table>
            <hr/>
            <table border="1" >
                <th> </th><th>Фамилия</th><th>Имя</th><th>Ред.</th><th>Удалить</th>
                <c:forEach var="master" items="${masterDir.masters}" >
                    <jsp:useBean id="master" class="beans.Master" scope="page" />
                    <tr>
                        <td><input type="radio"  name="master_rbt" value="${master.masterId}" /></td>
                        <td>${master.lName}</td>
                        <td>${master.fName}</td>
                        <td><a href="master.jsp?id=${master.masterId}">Изменить</a></td>
                        <td><a href="masterDel?id=${master.masterId}">Удалить</a></td>
                    </tr>
                </c:forEach>

            </table>
        </form>

    </body>
</html>