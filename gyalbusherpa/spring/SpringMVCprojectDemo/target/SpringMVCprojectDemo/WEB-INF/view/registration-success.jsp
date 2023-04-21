<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/19/2023
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Your registration is successful</h1>
    <h2>The details entered by you are: </h2>
    User : ${register.name} <br>
    User name : ${register.userName} <br>
    Password : ${register.password} <br>
    Hobbies :

    <c:forEach var="temp" items="${register.hobbies}">
        ${temp}
    </c:forEach> <br>

    Gender : ${register.gender} <br>

    Age : ${register.age} <br>

    Email: ${register.communicationDTO.email} <br>
    Phone: ${register.communicationDTO.phone} <br>
</div>
</body>
</html>
