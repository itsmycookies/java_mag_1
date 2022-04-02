<%-- 
    Document   : Servdir
    Created on : 30 мар. 2022 г., 20:29:29
    Author     : Andrey Belov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список услуг</title>
    </head>
    <body>
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
    </table>
</form>
        
</body>
</html>
        