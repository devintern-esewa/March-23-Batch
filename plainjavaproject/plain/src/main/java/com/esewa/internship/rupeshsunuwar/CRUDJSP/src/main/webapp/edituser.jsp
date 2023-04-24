<%--
  Created by IntelliJ IDEA.
  User: dipes
  Date: 4/9/2023
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.database.Db_Conn"%>
<jsp:useBean id="u" class="com.model.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>
<%
    int i=Db_Conn.update(u);
    response.sendRedirect("viewusers.jsp");
%>