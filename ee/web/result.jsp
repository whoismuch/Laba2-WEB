<%@ page import="com.AreaCheckServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: khumachbayramova
  Date: 27.09.2020
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Laba1</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">
    <em>
        <p style="color: purple">Байрамова Хумай</p>
        <p style="color: purple">Вариант №2802</p>
        <p style="color: purple">Группа №P3132</p>
    </em>
</div>

<div class="centerBorder">
    <table>

        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
        </tr>
        <%
            ArrayList<HashMap<String, Object>> datas;
            if (request.getServletContext( ).getAttribute("history") != null) {
                datas = (ArrayList<HashMap<String, Object>>) request.getServletContext( ).getAttribute("history");
                for (HashMap<String, Object> map : datas) {
        %>
        <tr>
            <%
                if (map.get("Result").equals("Данные неверны")) {
            %>
            <td colspan="4" style="color: red;">Данные неверны</td>
            <%
            } else {
            %>
            <td><% out.print(String.format("%.2f", Float.parseFloat(map.get("X").toString( ))));%></td>
            <td><% out.print(String.format("%.2f", Float.parseFloat(map.get("Y").toString( ))));%></td>
            <td><% out.print(map.get("R"));%></td>
            <td><% out.print(map.get("Result"));%></td>
            <%
                }
            %>
        </tr>
        <%
                }
            }
        %>

    </table>
</div>

</body>
</html>
