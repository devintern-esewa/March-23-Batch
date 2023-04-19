package com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.dao;

import com.esewa.internship.gyalbusherpa.PasswordManagementSystem.src.main.java.com.example.password.model.Password;

import java.sql.*;
import java.util.ArrayList;

public class PasswordDao {

    private Connection conn;

    public PasswordDao(Connection conn) {
        super();
        this.conn = conn;
    }


    public void insertData(Password password) throws SQLException {

        PreparedStatement st = conn.prepareStatement("insert into password(accountname,usename,email,password) values" +
                "(?,?,?,?)");

        st.setString(1, password.getAccountName());
        st.setString(2, password.getUserName());
        st.setString(3, password.getEmail());
        st.setString(4, password.getPassword());

        st.executeUpdate();
        conn.close();
    }

    public ArrayList<Password> getAllPassword() {
        ArrayList<Password> list = new ArrayList<>();

        try {
            String sql = "select * from password";
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Password d = new Password();
                d.setId(rs.getInt(1));
                d.setAccountName(rs.getString(2));
                d.setUserName(rs.getString(3));
                d.setEmail(rs.getString(4));
                d.setPassword(rs.getString(5));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deletePassword(int id) throws SQLException {

        String sql = "delete from password where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    public void editPassword(Password d) {
        try {
            String sql = "update password set accountname=?,usename=?,email=?,password=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getAccountName());
            ps.setString(2, d.getUserName());
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getPassword());
            ps.setInt(5, d.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Password getPasswordById(int id) {
        Password d = null;
        try {
            String sql = "select * from password where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                d = new Password();
                d.setId(rs.getInt(1));
                d.setAccountName(rs.getString(2));
                d.setUserName(rs.getString(3));
                d.setEmail(rs.getString(4));
                d.setPassword(rs.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

}
