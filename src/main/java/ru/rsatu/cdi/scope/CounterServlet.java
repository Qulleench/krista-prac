package ru.rsatu.cdi.scope;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/counters")
public class CounterServlet extends HttpServlet {

    @Inject
    private ApplicationCounter applicationCounter;

    @Inject
    private SessionCounter sessionCounter;

    @Inject
    private RequestCounter requestCounter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        applicationCounter.increment();
        sessionCounter.increment();
        requestCounter.increment();

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Тест CDI: Scope</h2>");
        resp.getWriter().write("<table border='1' cellpadding='5'>");
        resp.getWriter().write("<tr><th>Scope</th><th>Значение счётчика</th></tr>");
        resp.getWriter().write("<tr><td>@ApplicationScoped</td><td>"
                + applicationCounter.getCount() + "</td></tr>");
        resp.getWriter().write("<tr><td>@SessionScoped</td><td>"
                + sessionCounter.getCount() + "</td></tr>");
        resp.getWriter().write("<tr><td>@RequestScoped</td><td>"
                + requestCounter.getCount() + "</td></tr>");
        resp.getWriter().write("</table>");
        resp.getWriter().write("<p>ID сессии: " + req.getSession().getId() + "</p>");
        resp.getWriter().write("</body></html>");
    }
}