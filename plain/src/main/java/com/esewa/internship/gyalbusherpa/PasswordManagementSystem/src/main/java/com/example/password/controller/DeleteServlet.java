package com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.controller;

import com.example.dB.DbConnect;
import com.example.password.dao.PasswordDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        PasswordDao dao = new PasswordDao(DbConnect.getConn());


        try {
            dao.deletePassword(id);
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
