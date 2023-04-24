<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC form handling</title>
</head>
<body>
<h2>Student Information Form</h2>
<form:form method="POST" action="addStudent">
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td>  <form:input path="name" /></td>
        </tr>

        <tr>
            <td>
                <form:label path="id">ID</form:label>
            </td>
            <td>  <form:input path="id" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="submit" />
            </td>
        </tr>
    </table>


</form:form>
</body>
</html>