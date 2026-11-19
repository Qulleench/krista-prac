package ru.rsatu.servlet;

import ru.rsatu.entity.Address;
import ru.rsatu.entity.Student;
import ru.rsatu.service.StudentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studentDetails")
public class StudentDetailsServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("students");
            return;
        }

        Long id = Long.parseLong(idStr);
        Student student = studentService.getStudent(id);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        if (student == null) {
            resp.getWriter().write("<h2>Студент не найден</h2>");
        } else {
            resp.getWriter().write("<h2>Студент: " + student.getFirstName() + " " + student.getLastName() + "</h2>");
            resp.getWriter().write("<p>Email: " + student.getEmail() + "</p>");
            resp.getWriter().write("<h3>Адреса:</h3>");
            resp.getWriter().write("<ul>");
            if (student.getAddresses().isEmpty()) {
                resp.getWriter().write("<li>Адресов пока нет.</li>");
            } else {
                for (Address a : student.getAddresses()) {
                    resp.getWriter().write("<li>" + a.getCity() + ", ул. " + a.getStreet() + ", д. " + a.getHouse() + "</li>");
                }
            }
            resp.getWriter().write("</ul>");

            // Форма добавления адреса
            resp.getWriter().write("<h3>Добавить адрес:</h3>");
            resp.getWriter().write("<form method='post' action='addAddress'>");
            resp.getWriter().write("<input type='hidden' name='studentId' value='" + student.getId() + "'>");
            resp.getWriter().write("Город: <input type='text' name='city' required><br>");
            resp.getWriter().write("Улица: <input type='text' name='street' required><br>");
            resp.getWriter().write("Дом: <input type='text' name='house'><br>");
            resp.getWriter().write("<input type='submit' value='Добавить адрес'>");
            resp.getWriter().write("</form>");
        }
        resp.getWriter().write("<br><a href='students'>Назад к списку</a>");
        resp.getWriter().write("</body></html>");
    }
}