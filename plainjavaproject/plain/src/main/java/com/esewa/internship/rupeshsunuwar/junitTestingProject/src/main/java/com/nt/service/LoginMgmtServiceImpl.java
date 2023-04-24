package com.esewa.internship.rupeshsunuwar.junitTestingProject.src.main.java.com.nt.service;

import com.nt.dao.LoginDAO;

public class LoginMgmtServiceImpl implements LoginMgmtService {

    private LoginDAO loginDAO;

    public LoginMgmtServiceImpl(LoginDAO dao) {
        this.loginDAO = dao;

    }

    @Override
    public boolean login(String username, String password) {

        if (username.equals("") || password.equals("")) {
            throw new IllegalArgumentException("Empty Credentials");
        }
        int count = loginDAO.authenticate(username, password);
        if (count == 0) return false;
        else return true;

    }

    @Override
    public String registerUser(String user, String role) {
        if (!role.equalsIgnoreCase("") && !role.equals("visitor")) {
            loginDAO.addUser(user, role);
            return "User Added Sucessfully";
        } else {
            return "User Not Added";
        }

    }
}
