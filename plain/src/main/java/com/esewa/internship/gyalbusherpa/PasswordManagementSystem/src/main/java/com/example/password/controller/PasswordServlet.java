package com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.controller;

import com.example.dB.DbConnect;
import com.example.password.dao.PasswordDao;
import com.example.password.model.Password;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PasswordServlet", value = "/PasswordServlet")
public class PasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            String accountName = request.getParameter("accountName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Password p = new Password(accountName, userName, email, password);

            PasswordDao passwordDao = new PasswordDao(DbConnect.getConn());

            HttpSession session = request.getSession();

            passwordDao.insertData(p);

            session.setAttribute("successMsg", " success");
            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
