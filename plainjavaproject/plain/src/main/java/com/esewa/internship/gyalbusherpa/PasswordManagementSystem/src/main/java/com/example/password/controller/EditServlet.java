package com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.controller;

import com.example.dB.DbConnect;
import com.example.password.dao.PasswordDao;
import com.example.password.model.Password;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            String accountName = request.getParameter("accountName");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            int id = Integer.parseInt(request.getParameter("id"));

            Password p = new Password(id, accountName, userName, email, password);

            PasswordDao dao = new PasswordDao(DbConnect.getConn());
            HttpSession session = request.getSession();

            dao.editPassword(p);

            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
