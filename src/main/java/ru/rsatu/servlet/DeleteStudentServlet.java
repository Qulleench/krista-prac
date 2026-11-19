package ru.rsatu.servlet;

import ru.rsatu.service.StudentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            Long id = Long.parseLong(idStr);
            studentService.deleteStudent(id);
        }
        resp.sendRedirect("students");
    }
}