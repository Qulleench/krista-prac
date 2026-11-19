package ru.rsatu.cdi.ambiguous;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {

    @Inject
    private GreetingService defaultGreeting;

    @Inject
    @FormalGreeting
    private GreetingService formalGreeting;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Тест CDI</h2>");
        resp.getWriter().write("<ul>");
        resp.getWriter().write("<li><b>Без квалификатора:</b> "
                + defaultGreeting.greet() + "</li>");
        resp.getWriter().write("<li><b>@FormalGreeting:</b> "
                + formalGreeting.greet() + "</li>");
        resp.getWriter().write("</ul>");
        resp.getWriter().write("</body></html>");
    }
}