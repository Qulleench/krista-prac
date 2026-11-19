<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ошибка 500</title>
</head>
<body>
    <h1>500 — Внутренняя ошибка сервера</h1>
    <p>Сообщение сервера: <%= request.getAttribute("javax.servlet.error.message") %></p>
    <p>URI запроса: <%= request.getAttribute("javax.servlet.error.request_uri") %></p>
    <a href="<%= request.getContextPath() %>/triggerError">Назад к тесту</a>
</body>
</html>