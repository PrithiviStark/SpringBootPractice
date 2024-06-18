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
<link rel="icon" href="/images/thamizhpro_icon-removebg.ico"
	type="image/x-icon" />
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
	<form action="login" method="post">

		User Name : <input id="username" type="text" name="username" /><br>
		<spring:errors path="userName" class="error-message"></spring:errors>
		<br> Password: <input id="password" type="password"
			name="password" /><br>
		<spring:errors path="userPassword" class="error-message"></spring:errors>
		<br> <input type="submit" name="submit" value="Sign In" />
	</form>

	<br>
	<hr>
	<a href="/register">click here</a> to new registration.
	<br>
	<hr>
	<a href="/resume">click here</a> to Resume Page.
</body>
</html>