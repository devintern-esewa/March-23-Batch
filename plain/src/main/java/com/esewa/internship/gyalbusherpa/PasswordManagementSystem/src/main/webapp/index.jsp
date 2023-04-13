<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <%@include file="allcss.jsp" %>
</head>
<body>
<div align="center">
    <h2><a href="success.jsp"
           class="btn btn-sm btn-danger">Click here to see my hard work</a></h2>
    <h1>Welcome to Password Vault</h1>
    <form action="PasswordServlet" method="post">
        <table style="width: 80%">
            <tr>
                <td>Account Name</td>
                <td><input type="text" name="accountName"/></td>
            </tr>
            <tr>
                <td>User Name</td>
                <td><input type="text" name="userName"/></td>
            </tr>

            <tr>
                <td>Email</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Image Link: </td>
                <td>
                    <input type = "file" name = "file">
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>

</html>

