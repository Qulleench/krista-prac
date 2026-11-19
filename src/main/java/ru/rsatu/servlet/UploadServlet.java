package ru.rsatu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Формируем путь из системных свойств
        String uploadPath = System.getProperty("jboss.home.dir")
                + File.separator
                + System.getProperty("my-app.upload-dir");

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        // Сохраняем каждый загруженный файл
        for (Part part : req.getParts()) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                part.write(uploadPath + File.separator + fileName);
            }
        }

        // Формируем ответ
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Файлы успешно загружены!</h2>");
        resp.getWriter().write("<p>Каталог: " + uploadPath + "</p>");
        resp.getWriter().write("<a href='upload.html'>Загрузить ещё</a>");
        resp.getWriter().write("</body></html>");
    }
}