package ru.rsatu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AtomicLong counter = (AtomicLong) getServletContext().getAttribute("requestCount");
        long count = (counter != null) ? counter.get() : 0;

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Статистика запросов</h2>");
        resp.getWriter().write("<p>Всего обработано запросов: <b>" + count + "</b></p>");
        resp.getWriter().write("<a href='upload.html'>Вернуться на загрузку</a>");
        resp.getWriter().write("</body></html>");
    }
}