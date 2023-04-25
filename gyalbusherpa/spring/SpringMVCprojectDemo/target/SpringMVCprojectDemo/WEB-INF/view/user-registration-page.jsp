<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/19/2023
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">Please register here</h1>
<div align="center">
    <form:form action="registration-success" method="GET" modelAttribute="register">
        <label>User: </label>
        <form:input path="name"/> <br>

        <label>User name: </label>
        <form:input path="userName"/> <br>

        <label>Password: </label>
        <form:password path="password"/> <br>

        <label>Country Name: </label>
        <form:select path="countryName">
            <form:option value="Nep" label="Nepal"/>
            <form:option value="Ind" label="India"/>
            <form:option value="US" label="United State"/>
            <form:option value="UK" label="United Kingdom"/>
        </form:select> <br>

        <label>Hobbies: </label>
        Basketball : <form:checkbox path="hobbies" value="Basketball"/>
        Football : <form:checkbox path="hobbies" value="Football"/>
        Table tennis : <form:checkbox path="hobbies" value="Table Tennis"/>
        Cricket : <form:checkbox path="hobbies" value="Cricket"/>
        Snooker : <form:checkbox path="hobbies" value="Snooker"/> <br>

        <label>Gender: </label>
        Male: <form:radiobutton path="gender" value="male"/>
        Female: <form:radiobutton path="gender" value="female"/> <br>

        <input type="submit" value="register"/>

    </form:form>
</div>
</body>
</html>
