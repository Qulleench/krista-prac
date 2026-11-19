package ru.rsatu.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebFilter(urlPatterns = {"/*"})
public class StatisticsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Не считаем запросы к самому сервлету статистики
        String path = httpRequest.getServletPath();
        if (!"/statistics".equals(path)) {

            // Получаем счетчик
            ServletContext context = request.getServletContext();
            AtomicLong counter = (AtomicLong) context.getAttribute("requestCount");

            // Создание счетчика
            if (counter == null) {
                synchronized (StatisticsFilter.class) {
                    counter = (AtomicLong) context.getAttribute("requestCount");
                    if (counter == null) {
                        counter = new AtomicLong(0);
                        context.setAttribute("requestCount", counter);
                    }
                }
            }

            // Увеличиваем счётчик
            counter.incrementAndGet();
        }

        // Передаём запрос дальше
        chain.doFilter(request, response);
    }
}