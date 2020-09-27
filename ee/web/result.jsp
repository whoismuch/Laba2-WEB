<%@ page import="com.AreaCheckServlet" %><%--
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
            AreaCheckServlet asc = (AreaCheckServlet) request.getAttribute("AreaCheckServlet");
            if (asc.getNewParamX( ) != null && asc.getNewParamY( ) != null || asc.getShit( )) {
                for (int i = 0; i < asc.getParamsR( ).size( ); i++) {
        %>
        <tr>
            <%
                if (asc.getShit( ) || asc.getResults( )[i].equals("Данные неверны")) {
            %>
            <td colspan="4" style="color: red;">Данные неверны</td>
            <%
            } else {
            %>
            <td><% out.print(asc.getNewParamX( ));%></td>
            <td><% out.print(asc.getNewParamY( ));%></td>
            <td><% out.print(asc.getParamsR( ).get(i));%></td>
            <td><% out.print(asc.getResults( )[i]);%></td>
            <%
                }
            %>
        </tr>
        <%
            }
        } else {
            for (int i = 0; i < asc.getParamsR( ).size( ); i++) {
        %>
        <tr>
            <%
                if (asc.getShit( ) || asc.getResults( )[i].equals("Данные неверны")) {
            %>
            <td colspan="4" style="color: red;">Данные неверны</td>
            <%
            } else {
            %>
            <td><% out.print(asc.getParamsX( )[i]);%></td>
            <td><% out.print(asc.getParamsY( )[i]);%></td>
            <td><% out.print(asc.getParamsR( ).get(i));%></td>
            <td><% out.print(asc.getResults( )[i]);%></td>
            <%
                        }
                    }
                }
            %>
        </tr>
        <%--        <?php foreach ($_SESSION['allResults'] as $value) { ?>--%>
        <%--        <tr>--%>

        <%--            <td><?php echo $value[0] ?></td>--%>
        <%--            <td><?php echo $value[1] ?></td>--%>
        <%--            <td><?php echo $value[2] ?></td>--%>
        <%--            <td><?php echo $value[3] ?></td>--%>
        <%--            <td><?php echo $value[4] ?></td>--%>
        <%--            <td><?php echo $value[5] ?></td>--%>

        <%--        </tr>--%>

        <%--        <?php } ?>--%>
    </table>
</div>

</body>
</html>
