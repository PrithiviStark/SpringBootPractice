<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<style type="text/css">

.error-message {
    color: red;
    font-size: 14px;
    text-decoration: underline;
   	font-weight: bold;
}
</style>
</head>
<body>
<h1>Welcome to Registration, </h1>
<spring:form action="registration"  method="post" modelAttribute="userDetails">
        User Name : <spring:input id="username" type="text" path="userName" name="username" /><br>
		<spring:errors path="userName" class="error-message"></spring:errors><br>
		
        Password: <spring:input id="password" type="text" path="userPassword" name="password" /><br>
        <spring:errors path="userPassword" class="error-message"></spring:errors><br>
        
        Roles: <spring:input id="roles" type="text" path="roles" name="roles" /><br>
        <spring:errors path="roles" class="error-message"></spring:errors><br>
        
		<spring:input type="submit" name="submit" path="" value="Register" />
	</spring:form>
<br>
<hr>
<a href="/index">click here</a> to index.
<br>
<hr>
<a href="/logout">click here</a> to logout.


</body>
</html>