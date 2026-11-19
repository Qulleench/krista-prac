package ru.rsatu.rest;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Path("/files")
public class FileStorageResource {

    // Числовой ID
    @GET
    @Path("/{id: \\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByNumericId(@PathParam("id") String id) {
        long number = Long.parseLong(id);
        long result = number / 2;
        return "Числовой ID: " + id + " -> результат (id / 2): " + result;
    }

    // UUID v4
    @GET
    @Path("/{id: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getByUuid(@PathParam("id") String uuid) {
        // Сумма hex-цифр: берём все hex-цифры из UUID, переводим в числа и складываем
        String hexOnly = uuid.replace("-", "");
        long sum = 0;
        for (char c : hexOnly.toCharArray()) {
            sum += Character.digit(c, 16);
        }
        return "UUID: " + uuid + " -> сумма hex-цифр: " + sum;
    }

    // Миниатюра
    @GET
    @Path("/{name: [a-zA-Z0-9_-]+}-{width: \\d+}x{height: \\d+}.{ext: jpg|png}")
    @Produces({"image/jpeg", "image/png"})
    public Response getThumbnail(
            @PathParam("name") String name,
            @PathParam("width") int width,
            @PathParam("height") int height,
            @PathParam("ext") String ext) {

        // Формируем путь к исходному файлу
        String imagesDir = System.getProperty("jboss.home.dir")
                + File.separator
                + System.getProperty("my-app.images-dir");
        java.nio.file.Path sourcePath = java.nio.file.Paths.get(imagesDir, name + "." + ext);

        if (!Files.exists(sourcePath)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Файл не найден: " + sourcePath)
                    .build();
        }

        try {
            // Читаем исходное изображение
            BufferedImage original = ImageIO.read(sourcePath.toFile());
            if (original == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Не удалось прочитать изображение")
                        .build();
            }

            // Создаём пустое изображение нужного размера
            BufferedImage thumbnail = new BufferedImage(width, height,
                    ext.equals("png") ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);

            // Рисуем уменьшенную копию
            Graphics2D g = thumbnail.createGraphics();
            try {
                // Плавная интерполяция для качества
                g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                        java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(original, 0, 0, width, height, null);
            } finally {
                g.dispose();
            }

            // Определяем MIME-тип
            String mimeType = ext.equals("png") ? "image/png" : "image/jpeg";

            // Формируем поток и возвращаем ответ
            StreamingOutput stream = output -> {
                ImageIO.write(thumbnail, ext, output);
                output.flush();
            };

            return Response.ok(stream)
                    .type(mimeType)
                    .header("Content-Disposition",
                            "inline; filename=\"" + name + "-" + width + "x" + height + "." + ext + "\"")
                    .build();

        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Ошибка при обработке изображения: " + e.getMessage())
                    .build();
        }
    }
}