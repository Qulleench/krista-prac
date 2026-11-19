<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
</head>
<body>
    <h1>Произошла ошибка</h1>
    <%
        // Получаем объект исключения из атрибутов запроса
        Throwable ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
        if (ex != null) {
    %>
        <p>Тип: <b><%= ex.getClass().getName() %></b></p>
        <p>Сообщение: <b><%= ex.getMessage() %></b></p>
    <%
        } else {
    %>
        <p>Сообщение: <%= request.getAttribute("javax.servlet.error.message") %></p>
    <%
        }
    %>
    <a href="<%= request.getContextPath() %>/triggerError">Назад к тесту</a>
</body>
</html>