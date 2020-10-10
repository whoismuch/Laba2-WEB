# Laba2-WEB
I'm growing up and improving my skills at programming, but it still smells like parasha

## Task:
Разработать веб-приложение на базе сервлетов и JSP, определяющее попадание точки на координатной плоскости в заданную область.

Приложение должно быть реализовано в соответствии с шаблоном MVC и состоять из следующих элементов:

- ControllerServlet, определяющий тип запроса, и, в зависимости от того, содержит ли запрос информацию о координатах точки и радиусе, делегирующий его обработку одному из перечисленных ниже компонентов. Все запросы внутри приложения должны передаваться этому сервлету (по методу GET или POST в зависимости от варианта задания), остальные сервлеты с веб-страниц напрямую вызываться не должны.
- AreaCheckServlet, осуществляющий проверку попадания точки в область на координатной плоскости и формирующий HTML-страницу с результатами проверки. Должен обрабатывать все запросы, содержащие сведения о координатах точки и радиусе области.
- Страница JSP, формирующая HTML-страницу с веб-формой. Должна обрабатывать все запросы, не содержащие сведений о координатах точки и радиусе области.

Разработанная страница JSP должна содержать:

- "Шапку", содержащую ФИО студента, номер группы и номер варианта.
- Форму, отправляющую данные на сервер.
- Набор полей для задания координат точки и радиуса области в соответствии с вариантом задания.
- Сценарий на языке JavaScript, осуществляющий валидацию значений, вводимых пользователем в поля формы.
- Интерактивный элемент, содержащий изображение области на координатной плоскости (в соответствии с вариантом задания) и реализующий следующую функциональность:
        - Если радиус области установлен, клик курсором мыши по изображению должен обрабатываться JavaScript-функцией, определяющей координаты точки, по которой                 кликнул пользователь и отправляющей полученные координаты на сервер для проверки факта попадания.
        - В противном случае, после клика по картинке должно выводиться сообщение о невозможности определения координат точки.
        - После проверки факта попадания точки в область изображение должно быть обновлено с учётом результатов этой проверки (т.е., на нём должна появиться новая точка).
- Таблицу с результатами предыдущих проверок. Список результатов должен браться из контекста приложения, HTTP-сессии или Bean-компонента в зависимости от варианта.

Страница, возвращаемая AreaCheckServlet, должна содержать:

- Таблицу, содержащую полученные параметры.
- Результат вычислений - факт попадания или непопадания точки в область.
- Ссылку на страницу с веб-формой для формирования нового запроса.


## 1. Java-сервлеты. Особенности реализации, ключевые методы, преимущества и недостатки относительно CGI и FastCGI.

__Servlet__ – в первую очередь это простой Java интерфейс, реализация которого расширяет функциональные возможности сервера. Сервлет взаимодействует с клиентами посредством принципа запрос-ответ. 

#### Что же такое сервлет ? В двух словах описать работу сервлета можно так:

Web-сервер, который умеет работать с сервлетами, запускает Java-машину, которая в свою очередь выполняет сервлет, а сервлет отдает данные, которые он сформирует. Т.е. при приходе запроса от клиента сервер с помощью специального конфигурационного файла может определить, какой сервлет выполнить, сервлет выполняется и создает HTML-страницу, которую сервер отправляет клиенту.

На сервер приходит запрос от клиента, запрос содержит внутри себя URL и параметры. Сервер имеет специальный конфигурационный файл, который ему сообщает о том, какой сервлет надо выполнить в случае прихода определенного URL. Сервлет выполняется (там вы можете использовать параметры) и создает HTML-страницу, которая отсылается клиенту.
Сервер по сути является контейнером (теперь уже не визуальных компонентов), который загружает сервлеты, выполняет их, вызывая определенные методы и получив от них результат, отправляет его клиенту.

Таким образом сервлет — это Java-класс, который наследуется обычно от класса • HttpServlet и переопределяет часть методов:
- doGet — если мы хотим, чтобы сервлет реагировал на GET запрос.
- doPost — если мы хотим, чтобы сервлет реагировал на POST запрос.
- doPut, doDelete — если мы хотим, чтобы сервлет реагировал на PUT и DELETE запрос (есть и такие в HTTP). Эти методы реализуются крайне редко, т.к. сами команды тоже очень редко встречаются.
- init, destroy — для управления ресурсами в момент создания сервлета и в момент его уничтожения.

Если же необходимо перехватывать все команды, то проще переопределить метод __service__. Именно этот метод вызывается при приходе запроса от клиента. В HttpServlet происходит разбор запроса и в соответствии с указанной командой вызывается метод doGet, doPost и т.д.


#### Важно !!!
- ServletContext живет до тех пор, пока живет веб-приложение. Он является общим для всех запросов во всех сеансах.
- HttpSession живет до тех пор, пока клиент взаимодействует с веб-приложением с одним и тем же экземпляром браузера, а время ожидания сеанса на стороне сервера не истекло. Он является общим для всех запросов в одном сеансе.
- HttpServletRequest и HttpServletResponse живут с момента получения сервлетом запроса HTTP от клиента, пока не будет получен полный ответ (веб-страница). Он не разделяется в других местах.
- Все экземпляры Servlet , Filter и Listener живут до тех пор, пока живет веб-приложение. Они являются общими для всех запросов во всех сеансах.
- Любой attribute , определенный в ServletContext, HttpServletRequest и HttpSession , будет жить до тех пор, пока живет рассматриваемый объект. Сам объект представляет собой "scope" в рамках управления бобами, таких как JSF, CDI, Spring и т.д. Эти фреймворки хранят свои ограниченные бобы как attribute из своей ближайшей совпадающей области.

#### Преимущества и недостатки относительно CGI и FastCGI

Преимущества:
-	Выполняются быстрее, чем CGI-сценарии.
-	Хорошая масштабируемость.
-	Надежность и безопасность (реализованы на JAVA)
-	Платформенно-независимы
-	Множество инструментов мониторинга и отладки

Недостатки:
-	Слабое разделение уровня представления и бизнес-логики.
-	Возможны конфликты при параллельной обработке запросов.

## 2. Контейнеры сервлетов. Жизненный цикл сервлета.

Сервлет-контейнер поддерживает только API сервлета (включая JSP, JSTL).

Сервер приложений поддерживает весь JavaEE - EJB, JMS, CDI, JTA, API сервлета (включая JSP, JSTL) и т.д.


#### Жизненный цикл сервлета
Для каждого сервлета движок сервлетов создает только одну копию. Вне зависимости от того, сколько запросов будет отправлено сервлету, все запросы будут обрабатываться толькой одной копией сервлета. Объект сервлета создается либо при запуске движка сервлетов, либо когда сервлет получает первый запрос. Затем для каждого запроса запускается поток, который обращается к объекту сервлета.

При работе с сервлетом движок сервлетов вызывает у класса сервлета ряд методов, которые определены в родительском абстрактном классе HttpServlet.

Когда движок сервлетов создает объект сервлета, у сервлета вызывается метод init().

```java
public void init(ServletConfig config) throws ServletException { 
}
```
Этот метод вызывается только один раз - при создании сервлета. Мы можем переопределить этот метод, чтобы определить в нем некоторую логику инициализации.

Когда к сервлету приходит запрос, движок сервлетов вызывает метод service() сервлета. А этот метод, исходя из типа запроса (GET, POST, PUT и т.д.) решает, какому методу сервлета (doGet, doPost и т.д.) обрабатывать этот запрос.

```java
public void service(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException{
        }
```
Этот метод также можно переопределить, однако в этом нет смысла. В реальности для обработки запроса переопределяются методы onGet, onPost и т.д., которые обрабатывают конкретные типы запросов.

Если объект сервлета долгое время не используется (к нему нет никаких запросов), или если происходит завершение работы движка сервлетов, то движок сервлетов выгружает из памяти все созданные экземпляры сервлетов. Однако до выгрузки сервлета из памяти у сервлета вызывается метод destroy().

```java
public void destroy()
```
При необходимости мы также можем его переопределить, например, определить в нем логику логгирования или что иное. В то же время следует учитывать, что если сервер вдруг упадет по какой-то причине, например, отключится электричество и т.д., тогда данный метод естественно не будет вызван и его логика не сработает.

Поскольку для обработки всех запросов создается один экземпляр сервлета, и все обращения к нему идут в отдельных потоках, то не рекомендуется в классе сервлета объявлять и использовать глобальные переменные, так как они не будут потокобезопасными.

## 3.Диспетчеризация запросов в сервлетах. Фильтры сервлетов.

#### Диспетчеризация запросов в сервлетах

- Сервлеты могут делегировать обработку запросов другим ресурсам (сервлетам, JSP и HTML-страницам).
- Диспетчеризация осуществляется с помощью реализаций интерфейса javax.servlet.RequestDispatcher.
- Два способа получения RequestDispatcher — через ServletRequest (абсолютный или относительный URL) и ServletContext (только абсолютный URL).
- Два способа делегирования обработки запроса — forward и include (include используется чтобы вставить вывод другого сервлета в текущий. forward позволяет провести предварительную обработку запроса и потом полностью передать управление другому сервлету)

Сервлетный фильтр занимается предварительной обработкой запроса, прежде чем тот попадает в сервлет, и/или последующей обработкой ответа, исходящего из сервлета.

#### Фильтры сервлетов

Сервлетные фильтры могут:

- Перехватывать инициацию сервлета прежде, чем сервлет будет инициирован;
- Определить содержание запроса прежде, чем сервлет будет инициирован;
- Модифицировать заголовки и данные запроса, в которые упаковывается поступающий запрос;
- Модифицировать заголовки и данные ответа, в которые упаковывается получаемый ответ;
- Перехватывать инициацию сервлета после обращения к сервлету.
Основой для формирования фильтров служит интерфейс javax.servlet.Filter, который реализует три метода:

```java
void init (FilterConfig config) throws ServletException;

void destroy ();

void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
```

## 5. Контекст сервлета - назначение, способы взаимодействия сервлетов с контекстом.

Для доступа из сервлета к параметрам WEB-приложения необходимо использовать интерфейс javax.servlet.ServletContext. Объект ServletContext является уникальным и доступен всем сервлетам.

ServletContext позволяет получить доступ к параметрам WEB-приложения, определенным в дескрипторе web.xml тегом <context-param> :
        
getAttribute () Гибкий способ получения информации о сервере через пары атрибутов имя/значение. Зависит от сервера.

GetMimeType () Возвращает тип MIME данного файла.

getRealPath () Этот метод преобразует относительный или виртуальный путь в новый путь относительно месторасположения корня HTML-документов сервера.

getServerInfo () Возвращает имя и версию сетевой службы, в которой исполняется сервлет.

getServlet () Возвращает объект Servlet указанного имени. Полезен при доступе к службам других сервлетов.

getServletNames () Возвращает список имен сервлетов, доступных в текущем пространстве имен.

log () Записывает информацию в файл регистрации сервлета. Имя файла регистрации и его формат зависят от сервера.

## 6. JavaServer Pages. Особенности, преимущества и недостатки по сравнению с сервлетами, область применения.

Страницы JSP — это текстовые файлы, содержащие статический HTML и JSP-элементы.
- JSP-элементы позволяют формировать динамическое содержимое.
- При загрузке в веб-контейнер страницы JSP транслируются компилятором (jasper) в сервлеты.
- Позволяют отделить бизнес-логику от уровня представления (если их комбинировать с сервлетами).

Преимущества:
- Высокая производительность — транслируются в
сервлеты.
- Не зависят от используемой платформы — код
пишется на Java.
- Позволяют использовать Java API.
- Простые для понимания — структура похожа на
обычный HTML.
 Недостатки:
- Трудно отлаживать, если приложение целиком
основано на JSP.
- Возможны конфликты при параллельной обработке
нескольких запросов.

ЕЩЕ!1!1!

- Модификация в сервлете является трудоемкой задачей, поскольку включает в себя перезагрузку, перекомпиляцию и перезапуск сервера. В то время как модификация JSP быстрая, просто нажмите кнопку обновления.

- В сервлете мы должны выполнить все, как бизнес-логику и логику представления, в одном файле сервлета. Принимая во внимание, что в JSP бизнес-логика изолирована от логики представления с помощью JavaBeans.

- Сервлет может принимать любые запросы к протоколу и может переопределять метод service (). В то время как JSP получает только HTTP-запросы и не может переопределить его метод service ().

- Более того, сервлет - это Java-код. Написание кода для сервлета сложнее, чем JSP, так как это HTML в Java. Принимая во внимание, что JSP - это код на основе HTML, а JSP прост в кодировании, так как это Java в HTML.

- Сервлеты выполняются на веб-сервере, таком как Tomcat, а программа JSP перед выполнением компилируется в сервлет Java. Как только он скомпилирован в сервлет, его жизненный цикл будет аналогичен сервлету. Однако у JSP есть свой индивидуальный API для жизненного цикла.

## 9. Правила записи Java-кода внутри JSP. Стандартные переменные, доступные в скриптлетах и выражениях.


2 варианта синтаксиса — на базе HTML и XML.

- Обозначаются тегами <% %> (HTML-вариант):
<html>
<%-- scripting element --%>
</html>
        
- Существует 5 типов JSP-элементов:
- Комментарий — <%-- Comment --%>;

  Поддерживаются 3 типа комментариев:
  * HTML-комментарии:
  <!-ч- This is an HTML comment.
  It will show up in the response. -ч->
  * JSP-комментарии:
  <%-- This is a JSP comment.
  It will only be seen in the JSP code.
  It will not show up in either the servlet code
  or the response.
  --%> 
  * Java-комментарии:
  <%
  /* This is a Java comment.
  It will show up in the servlet code.
  It will not show up in the response. */
  %>

- Директива — <%@ directive %>;

  Управляют процессом трансляции страницы в сервлет.
    * Директива страницы (Директива страницы используется для предоставления инструкций контейнеру. Эти инструкции относятся к текущей странице JSP.)
    <%@ page session=”false” %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="helpers.Point" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    (buffer, contentType, language, isThreadSafe)
    * Директива включения (Директива include используется для включения файла на этапе перевода. Эта директива указывает контейнеру объединять содержимое других       внешних файлов с текущим JSP на этапе трансляции.)
    <%@ include file=”incl/copyright.html” %> 
    Эквивалент на XML:<jsp:directive.include file="url"\>.
    URL должен быть относительным . Для подключения файла в процессе запроса а не в ходе трансляции используйте действие jsp:include.
       1) Директива include :
       <%@ include file="header.html" %>
       Статический : добавляет содержимое из значения атрибута файла на текущую страницу во время перевода . Директива изначально предназначалась для статических          шаблонов макета, таких как заголовки HTML.

      2) <jsp:include> Стандартное действие :
      <jsp:include page="header.jsp" />
      Динамический : добавляет содержимое из значения атрибута страницы на текущую страницу во время запроса . Был предназначен больше для динамического контента,       поступающего из JSP.

      3) <c:import>Тег JSTL:
      <c:import url=”http://www.example.com/foo/bar.html” />
      Динамический : добавляет содержимое из значения атрибута URL на текущую страницу во время запроса . Он работает примерно так же <jsp:include>, но он более         мощный и гибкий: в отличие от двух других включений, <c:import> URL-адрес может быть извне веб-контейнера !
    
    * Директива taglib ( Библиотеки тегов-это фрагменты кода Java, которые могут использоваться в JSP, но которые соответствуют определенному API и которые             выглядят как теги HTML в JSP. Файл описания библиотеки тегов (TLD) описывает имена этих тегов, какие атрибуты они могут иметь и какие классы Java реализуют их)
    
    Директива taglib имеет следующий синтаксис: 
    <%@ taglib uri="URI включаемой библиотеки тегов" prefix="имяПрефикса" %>
    Префикс "имяПрефикса" используется при обращении к библиотеке. Пример использования библиотеки тегов mytags:
    <%@ taglib uri="http://www.taglib/mytags" prefix="customs" %>
    . . .
    <customs:myTag>
    
- Объявление — <%! decl %>;(declarations не производят никакого вывода в стандартный выходной поток out. Переменные и методы, декларированные в объявлениях, инициализируются и становятся доступными для скриптлетов и других объявлений в момент инициализации страницы JSP.)

    Позволяют объявлять поля и методы:
    * Синтаксис:
    ```jsp
    <%! JavaClassDeclaration %>
    ```
    * Примеры:
    ```jsp
    <%!
    public static final String DEFAULT_NAME = “World”;
    %>
    <%!
    public String getName(HttpServletRequest request) {
    return request.getParameter(“name”);
    }
    %>
    <%! int counter = 0; %>
    ```
    
- Скриптлет — <% code %>; (Позволяют задать Java-код, который будет выполняться при обработке запросов (при вызове метода _jspService).)
    * Синтаксис:
    ```jsp
    <% JavaCode %>
    ```
    * Примеры:
    ```jsp
    <% int i = 0; %>
    <% if ( i > 10 ) %>
    I am a big number
    <% } else { %>
    I am a small number
    <% } %>
    ```

- Выражение — <%= expr %>. (Позволяют вывести результат вычисления выражения.)
    * Синтаксис:
    ```jsp
    <%= JavaExpression %>
    ```
    * Примеры:
    ```
     <B>Ten is <%= (2 * 5) %></B>
     Thank you, <I><%= name %></I>, for registering for the soccer league.
     The current day and time is: <%= new java.util.Date() %>
     ```


### Стандартные переменные, доступные в скриптлетах и выражениях.

В процессе трансляции контейнер добавляет в
метод _jspService ряд объектов, которые можно
использовать в скриптлетах и выражениях:

application     javax.servlet.ServletContext
config          javax.servlet.ServletConfig
exception       java.lang.Throwable.   (используется только на страницахперенаправлениях с информацией об ошибках (Error Pages).)
out             javax.servlet.jsp.JspWriter
page            java.lang.Object   (API для доступа к экземпляру класса сервлета, в который транслируется JSP.)
PageContext     javax.servlet.jsp.PageContext (PageContext — контекст JSP-страницы)
request         javax.servlet.ServletRequest
response        javax.servlet.ServletResponse
session         javax.servlet.http.HttpSession

## 10. Bean-компоненты и их использование в JSP.
JavaBeans — классы в языке Java, написанные по определённым правилам. Они используются для объединения нескольких объектов в один для удобной передачи данных.

JavaBean – это одноуровневые объекты, использующиеся для того, чтобы инкапсулировать в одном объекте код, данные или и то и другое. Компонент JavaBean может иметь свойства, методы и события, открытые для удаленного доступа.

```jsp
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
         "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<jsp:useBean id="hello" class="example.JavabeanHello" scope="page" />

<%! 
String getFormattedDate() 
{ 
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss"); 
    return sdf.format(new Date()); 
} 
%>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JavaBean с JSP!</title>
  </head>
  <body>
       <h2>${hello.message}</h2>
       <i>Сегодня <%= getFormattedDate() %></i>
  </body>
</html>
```

## 11. Стандартные теги JSP. Использование Expression Language (EL) в JSP.

### Стандартные теги JSP.

Стандартная библиотека тегов JSP (JavaServer Pages Standard Tag Library, JSTL) является расширением её спецификации, используемая для условной обработки, создания циклов, интернационализации страницы, разбора XML данных.

JSTL является альтернативой скриплетам, встроенным в JSP, то есть прямым вставкам Java кода. JSTL представляет собой набор тегов в стиле HTML, позволяющих обращаться к объектам Java и выполнять многие из конструкций языка Java.

Для подключения библиотеки тегов JSTL используются следующие выражения:

```jsp
// Основные теги создания циклов, определения условий, вывода информации на страницу и т.д.
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

// Теги для работы с XML-документами
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

// Теги для работы с базами данных 
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

// Теги для форматирования и интернационализации информации (i10n и i18n)
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
```
__Тэг <c:out>__
Тэг <c:out> используется для вывода данных на экран. Следующий код отобразит выражение, представленное в атрибуте value.

```jsp
// отображение текста 16+64*2
<c:out value="16+64*2" />
```

__Тэг <c:set>__
Тег c:set используется для определения переменной подобно <jsp:setProperty>. Пример использования :

```jsp
<c:set var="user" scope="session" value="Sergey" />
<c:out value="${user}" />
```
__Тэг <c:remove>__
Для удаления переменной следует использовать тэг <c:remove>.

```jsp
<c:remove var="user" scope="request" />
```

__Тэг <c:if>__
Тег <c:if> серьезно отличается от того, что мы используем в других языках программирования. Он не позволяет определить ветку else или elseif. Можно только проверить некоторое условие. Пример :

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Пример тега <c:if> библиотеки JSTL</title>
    </head>
    <body>
        <c:set var="salary" scope="session" value="${23400*2}"/>
        <c:if test="${salary > 45000}">
            <p>Salary = <c:out value="${salary}"/><p>
        </c:if>
    </body>
</html>
```

### Использование Expression Language (EL) в JSP.

Expression Language или сокращенно EL предоставляет компактный синтаксис для обращения к массивам, коллекциям, объектам и их свойствам внутри страницы jsp. Он довольн прост. Вставку окрывает знак $, затем в фигурные скобки {} заключается выводимое значение:

Откуда эти данные берутся? EL пытается найти значения для этих данных во всех доступных контекстах.

И EL просматривает все эти контексты в следующем порядке:

1. Контекст страницы (данные сохраняются в PageContext)

2. Контекст запроса

3. Контекст сессии

4. Контекст приложения

Соответственно, если контексты запроса и сессии содержат атрибут с одним и тем же именем, то будет использоваться атрибут из контекста запроса.

Затем найденное значение (если оно было найдено) конвертируется в строку и выводится на страницу.

