package ru.rsatu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/triggerError")
public class ErrorTriggerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String code = req.getParameter("code");

        if (code == null) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("<html><body>");
            resp.getWriter().write("<h2>Тест обработчика ошибок</h2>");
            resp.getWriter().write("<p>Добавьте параметр <b>code</b> к URL, чтобы вызвать ошибку.</p>");
            resp.getWriter().write("<ul>");
            resp.getWriter().write("<li><a href='triggerError?code=404'>Ошибка 404 (не найдено)</a></li>");
            resp.getWriter().write("<li><a href='triggerError?code=500'>Ошибка 500 (внутренняя)</a></li>");
            resp.getWriter().write("<li><a href='triggerError?code=boom'>Исключение (RuntimeException)</a></li>");
            resp.getWriter().write("</ul>");
            resp.getWriter().write("</body></html>");
            return;
        }

        switch (code) {
            case "404":
                // Отправляем ошибку 404
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Запрашиваемый ресурс не найден");
                break;
            case "500":
                // Отправляем ошибку 500
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла внутренняя ошибка сервера");
                break;
            case "boom":
                // Кидаем исключение
                throw new RuntimeException("Тестовое исключение: что-то пошло не так");
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неизвестный код ошибки: " + code);
        }
    }
}