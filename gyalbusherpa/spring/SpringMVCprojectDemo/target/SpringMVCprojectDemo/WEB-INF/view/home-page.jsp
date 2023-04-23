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
    <style type="text/css">

        .error {
            color: red;
            position: fixed;
            text-align: left;
            margin-left: 30px;
        }

    </style>
    <script type="text/javascript">

        function validateUserName() {
            const userName = document.getElementById('yn').value;
            if (userName.length < 3) {
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
            <form:errors path="userName" cssClass="error"/>
        </p>

        <p>
            <label for="cn"> Crush name: </label>
            <form:input id="cn" path="crushName"/>
            <form:errors path="crushName" cssClass="error"/>
        </p>
        
        <p>
            <form:checkbox path="termAndCondition" id="check"/>
            <label>I agree that this is for fun</label>
            <form:errors path="termAndCondition" cssClass="error"/>
        </p>

        <input type="submit" value="calculate">
    </div>
</form:form>
</body>
</html>
