<%@ page import="com.AreaCheckServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="helpers.FullResult" %><%--
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

<div class="centerBorderT">

    <div class="link">
        <a href="/ee_war_exploded/index.jsp?in=true"><img src="/ee_war_exploded/strelka.jpg" width="80"
                                                          height="50"></a>
    </div>

    <table>

        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
        </tr>
        <%
            ArrayList<FullResult> historyList;
            if (request.getServletContext( ).getAttribute("history") != null) {
                historyList = (ArrayList<FullResult>) request.getServletContext( ).getAttribute("history");
                for (FullResult fullResult : historyList) {
        %>
        <tr>
            <%
                if (fullResult.getResult( ) == -1) {
            %>
            <td colspan="4" style="color: red;">Данные неверны</td>
            <%
            } else {
            %>
            <td><%=String.format("%.2f", fullResult.getX( ).floatValue()) %>
            </td>
            <td><%=String.format("%.2f", fullResult.getY( ).floatValue()) %>
            </td>
            <td><%=fullResult.getR( )%></td>
            <%
                switch (fullResult.getResult( )) {
                    case (0):
            %>
            <td><%= "А ты хорош" %>
            </td>
            <%
                    break;
                case (1):
            %>
            <td><%= "Это фейл" %>
            </td>
            <%
                    }
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
