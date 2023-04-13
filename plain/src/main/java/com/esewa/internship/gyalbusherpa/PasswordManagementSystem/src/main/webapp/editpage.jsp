<%@ page import="com.example.password.dao.PasswordDao" %>
<%@ page import="com.example.dB.DbConnect" %>
<%@ page
        import="com.example.password.model.Password" %><%--@elvariable id="passObj" type="com.example.password.model.Password"--%>
<%--
  Created by IntelliJ IDEA.
  User: Kal D. en
  Date: 4/9/2023
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <%@include file="allcss.jsp" %>
</head>
<body>
<div>
    <h1>Edit password</h1>

    <%
        int id = Integer.parseInt(request.getParameter("id"));
        PasswordDao dao2 = new PasswordDao(DbConnect.getConn());
        Password d = dao2.getPasswordById(id);

    %>

    <form action="EditServlet" method="post">
        <div class="mb-3">
            <label class="form-label">Account Name</label> <input type="text"
                                                                  required name="accountName" class="form-control"
                                                                  value="<%=d.getAccountName()%>">
        </div>


        <div class="mb-3">
            <label class="form-label">User Name</label> <input type="text"
                                                               required name="userName" class="form-control"
                                                               value="<%=d.getUserName()%>">
        </div>


        <div class="mb-3">
            <label class="form-label">Email</label> <input type="text"
                                                           required name="email" class="form-control"
                                                           value="<%=d.getEmail()%>">
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label> <input required
                                                              name="password" type="text" class="form-control"
                                                              value="<%=d.getPassword()%>">
        </div>
        <input type="hidden" name="id" value="<%=d.getId()%>">

        <button type="submit" class="btn btn-primary col-md-12">Update</button>
    </form>
</div>

</body>
</html>
