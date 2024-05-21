<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
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
	<spring:form action="authenticate" method="post" modelAttribute="users">
	
        User Name : <spring:input id="username" type="text" path="userName" name="username" /><br>
		<spring:errors path="userName" class="error-message"></spring:errors><br>
		
        Password: <spring:input id="password" type="password" path="userPassword" name="password" /><br>
        <spring:errors path="userPassword" class="error-message"></spring:errors><br>
        
		<spring:input type="submit" path="" value="Sign In" />
	</spring:form>
</body>
</html>