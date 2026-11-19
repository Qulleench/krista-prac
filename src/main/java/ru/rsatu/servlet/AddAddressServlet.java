package ru.rsatu.servlet;

import ru.rsatu.entity.Address;
import ru.rsatu.service.StudentService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Long studentId = Long.parseLong(req.getParameter("studentId"));
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String house = req.getParameter("house");

        Address address = new Address(city, street, house);
        studentService.addAddressToStudent(studentId, address);

        // Возвращаемся на страницу детализации этого же студента
        resp.sendRedirect("studentDetails?id=" + studentId);
    }
}