<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/19/2023
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        function validateUserName() {
            const userName = document.getElementById('yn').value;
            if (userName.length < 1) {
                alert("your name should have at-least one character");
                return false;
            } else {
                return true;
            }
        }

    </script>
</head>
<body>
<h1 align="center">Love calculator</h1>
<br>

<form:form action="process-homepage" method="get" modelAttribute="userInfo" onsubmit="return validateUserName()">
    <div align="center">
        <p>
            <label for="yn"> Your name: </label>
            <form:input id="yn" path="userName"/>
        </p>

        <p>
            <label for="cn"> Crush name: </label>
            <form:input id="cn" path="crushName"/>
        </p>

        <input type="submit" value="calculate">
    </div>
</form:form>
</body>
</html>
