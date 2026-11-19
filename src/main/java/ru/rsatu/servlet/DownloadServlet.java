package ru.rsatu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Имя файла из параметра запроса
        String fileName = req.getParameter("fileName");

        if (fileName == null || fileName.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Не указано имя файла");
            return;
        }

        // Формируем путь к каталогу
        String uploadPath = System.getProperty("jboss.home.dir")
                + File.separator
                + System.getProperty("my-app.upload-dir");

        Path filePath = Paths.get(uploadPath, fileName);

        // Проверяем, что файл существует
        if (!Files.exists(filePath)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден: " + fileName);
            return;
        }

        // Устанавливаем заголовки для скачивания
        resp.setHeader("Content-Disposition",
                String.format("attachment; filename=\"%s\"", fileName));

        String contentType = getServletContext().getMimeType(fileName);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        resp.setContentType(contentType);

        // Копируем файл в поток ответа
        Files.copy(filePath, resp.getOutputStream());
    }
}