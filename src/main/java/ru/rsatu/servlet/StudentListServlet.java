package ru.rsatu.servlet;

import ru.rsatu.entity.Student;
import ru.rsatu.service.StudentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> students = studentService.getAllStudents();

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Список студентов</h2>");
        resp.getWriter().write("<a href='studentForm.html'>Добавить студента</a><br><br>");
        resp.getWriter().write("<table border='1' cellpadding='5'><tr><th>ID</th><th>Имя</th><th>Фамилия</th><th>Email</th><th>Действия</th></tr>");

        for (Student s : students) {
            resp.getWriter().write("<tr>");
            resp.getWriter().write("<td>" + s.getId() + "</td>");
            resp.getWriter().write("<td>" + s.getFirstName() + "</td>");
            resp.getWriter().write("<td>" + s.getLastName() + "</td>");
            resp.getWriter().write("<td>" + s.getEmail() + "</td>");
            resp.getWriter().write("<td>");
            resp.getWriter().write("<a href='studentDetails?id=" + s.getId() + "'>Детали</a> | ");
            // Удаление сделаем через POST-ссылку (простой вариант) или отдельную кнопку
            resp.getWriter().write("<a href='deleteStudent?id=" + s.getId() + "' onclick=\"return confirm('Удалить?');\">Удалить</a>");
            resp.getWriter().write("</td>");
            resp.getWriter().write("</tr>");
        }
        resp.getWriter().write("</table>");
        resp.getWriter().write("</body></html>");
    }
}