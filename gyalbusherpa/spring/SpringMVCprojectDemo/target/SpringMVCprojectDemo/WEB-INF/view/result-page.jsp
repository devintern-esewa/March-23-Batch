<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/19/2023
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1 align="center">Love Calculator</h1>
    <hr>
    <h2>The Love calculator predicts</h2>

    ${userInfo.userName} and ${userInfo.crushName} are: ${userInfo.result}



    <br>

    <a href="/SpringMVCprojectDemo/sendEmail">Send your result to your email</a><br>
    <a href="playAgain">Play again?</a>

</body>
</html>
