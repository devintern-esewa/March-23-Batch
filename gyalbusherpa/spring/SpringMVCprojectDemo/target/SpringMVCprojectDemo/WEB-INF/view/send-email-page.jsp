<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/20/2023
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Hi ${userInfo.userName}</h1>
<h2>Send Result to your email</h2>

<form:form action="process-email" method="GET" modelAttribute="emailDTO">
    <label>Enter your Email</label>
    <form:input path="userEmail"/>
    <input type="submit" value="send">
</form:form>

</body>
</html>
