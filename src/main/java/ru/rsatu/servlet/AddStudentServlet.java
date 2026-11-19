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

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); // Чтобы корректно читать русские буквы

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        Student student = new Student(firstName, lastName, email);
        studentService.addStudent(student);

        // После добавления перенаправляем на список
        resp.sendRedirect("students");
    }
}