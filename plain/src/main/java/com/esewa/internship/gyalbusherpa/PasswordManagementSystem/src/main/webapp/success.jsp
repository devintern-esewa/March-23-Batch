<%@ page import="com.example.password.dao.PasswordDao" %>
<%@ page import="com.example.password.model.Password" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dB.DbConnect" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.w3c.dom.ls.LSOutput" %><%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/6/2023
  Time: 10:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Success</title>
    <%@include file="allcss.jsp" %>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<head>
    <title>Password Manager I am don</title>
</head>
<body>

<h1><a href="index.jsp"
       class="btn btn-sm btn-danger">Want to add password? Click here</a></h1>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Account Name</th>
        <th scope="col">User Name</th>
        <th scope="col">Email</th>
        <th scope="col">Password</th>
        <th scope="col">Option</th>
    </tr>
    </thead>
    <tbody>
    <%
        PasswordDao dao2 = new PasswordDao(DbConnect.getConn());
        ArrayList<Password> list2 = dao2.getAllPassword();
        for (Password d : list2) {
    %>
    <tr>
        <td><%=d.getAccountName()%></td>
        <td><%=d.getUserName()%></td>
        <td><%=d.getEmail()%></td>
        <td><%=d.getPassword()%></td>

        <td><a href="editpage.jsp?id=<%=d.getId()%>"
               class="btn btn-sm btn-primary">Edit</a>
            <a href="DeleteServlet?id=<%=d.getId()%>"
               class="btn btn-sm btn-danger">Delete</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
