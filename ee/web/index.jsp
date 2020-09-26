<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Laba2</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="header">
    <em>
        <p style="color: purple">Байрамова Хумай</p>
        <p style="color: purple">Вариант №2828</p>
        <p style="color: purple">Группа №P3232</p>
    </em>
</div>


<div class="centerBorder">
    <form class="validateForm" action="controller" method="GET">
        <div class="commonGroup">
            <div class="fillItPls">Заполните тут все, позязя</div>
            <div class="XR">
                <div class="chooseR">

                    <label class="chooseRTitle"><p>Выберите R</p></label>
                    <label><input type="checkbox" id="1" name="chooseR" value="1">1</label>
                    <label><input type="checkbox" id="2" name="chooseR" value="2">2</label>
                    <br>
                    <label><input type="checkbox" id="3" name="chooseR" value="3">3</label>
                    <label><input type="checkbox" id="4" name="chooseR" value="4">4</label>
                    <br>
                    <label><input type="checkbox" id="5" name="chooseR" value="5">5</label>

                </div>

                <div class="selectX">
                    <select class="selectXInner" name="selectX">
                        <option selected="Выберите X" disabled="Выберите X">Выберите X</option>
                        <option name="selectX" value="-5">-5</option>
                        <option name="selectX" value="-4">-4</option>
                        <option name="selectX" value="-3">-3</option>
                        <option name="selectX" value="-2">-2</option>
                        <option name="selectX" value="-1">-1</option>
                        <option name="selectX" value="0">0</option>
                        <option name="selectX" value="1">1</option>
                        <option name="selectX" value="2">2</option>
                        <option name="selectX" value="3">3</option>
                    </select>
                </div>
            </div>

            <div class="enterY">
                <input class="enterYInner" name="enterY" type="text" placeholder="Введите значение Y от -3 до 5">
            </div>

            <div class="button">
                <input class="butt" type="submit" name="sendButton" value="Отправить">
            </div>

        </div>
    </form>

    <div class="svg">
        <svg width="300" height="300" class="svg-graph">

            <!--            Линии оси-->

            <line class="axis" x1="0" x2="300" y1="150" y2="150" stroke="black"></line>
            <line class="axis" x1="150" x2="150" y1="0" y2="300" stroke="black"></line>
            <polygon points="150,0 144,15 156,15" stroke="black"></polygon>
            <polygon points="300,150 285,156 285,144" stroke="black"></polygon>

            <line class="coor-line" x1="200" x2="200" y1="155" y2="145" stroke="black"></line>
            <line class="coor-line" x1="250" x2="250" y1="155" y2="145" stroke="black"></line>

            <line class="coor-line" x1="50" x2="50" y1="155" y2="145" stroke="black"></line>
            <line class="coor-line" x1="100" x2="100" y1="155" y2="145" stroke="black"></line>

            <line class="coor-line" x1="145" x2="155" y1="100" y2="100" stroke="black"></line>
            <line class="coor-line" x1="145" x2="155" y1="50" y2="50" stroke="black"></line>

            <line class="coor-line" x1="145" x2="155" y1="200" y2="200" stroke="black"></line>
            <line class="coor-line" x1="145" x2="155" y1="250" y2="250" stroke="black"></line>

            <text class="coor-text" x="195" y="140">R/2</text>
            <text class="coor-text" x="248" y="140">R</text>

            <text class="coor-text" x="40" y="140">-R</text>
            <text class="coor-text" x="90" y="140">-R/2</text>

            <text class="coor-text" x="160" y="105">R/2</text>
            <text class="coor-text" x="160" y="55">R</text>

            <text class="coor-text" x="160" y="205">-R/2</text>
            <text class="coor-text" x="160" y="255">-R</text>

            <text class="axis-text" x="290" y="170">X</text>
            <text class="axis-text" x="160" y="13">Y</text>

            <!-- first figure-->
            <polygon class="rectangle-figure" points="150,150 200,150 200,250, 150,250"
                     fill="blue" fill-opacity="0.7" stroke="blue"></polygon>

            <!-- second figure-->
            <polygon class="triangle-figure" points="100,150 150,150 150,250"
                     fill="blue" fill-opacity="0.7" stroke="blue"></polygon>

            <!-- third figure-->
            <path class="circle-figure" d="M 50 150 A 100 100, 180, 0, 1, 150 50 L 150 150 Z"
                  fill="blue" fill-opacity="0.7" stroke="blue"></path>

            <%--        <circle r="5" cx="0" cy="0" id="target-dot"></circle>--%>

            <polygon class="frame" points="0,0 0,300 300,300 300,0"></polygon>

        </svg>

        <%
            if (request.getAttribute("emptyparams") != null) {
                String message = "Ну не по-пацански это, выберите ";
                ArrayList<String> emptyparams = (ArrayList<String>) request.getAttribute("emptyparams");
                for (String param : emptyparams) {
                    message = message + param + " ";
                }
        %>
        <h5><b><p id="negodiay"><%out.println(message);%></p></b></h5>

        <%
            }
        %>

        <script src="validation.js"></script>

    </div>
</div>


</body>
</html>