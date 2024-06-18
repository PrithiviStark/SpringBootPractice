<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Player List</title>

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

	<h1>Index Page</h1>
	<hr>
	<hr>
	<spring:form name="playerform" action="saveplayer"
		modelAttribute="playerinfo" autocomplete="off">
		<%-- <spring:errors path="*" class="error-message"></spring:errors><br> --%>
		<spring:input id="nameid" path="playerName" placeholder="Enter Name" />
		<br />
		<spring:errors path="playerName" class="error-message"></spring:errors>
		<br>
		<spring:input id="urlid" path="playerPhotoUrl" placeholder="Enter URL" />
		<br />
		<spring:errors path="playerPhotoUrl" class="error-message"></spring:errors>
		<br>
		<spring:input id="submitid" path="" type="submit" value="submit" />
	</spring:form>
	<hr>

	<a href="/resume">click here</a> to Resume Page.
	<br>
	<hr>
	<a href="/register">click here</a> to new registration.
	<hr>
	<div>
		<ul>
			<c:forEach items="${playerlist}" var="player">
				<li><span>${player.playerId}</span>.${player.playerName} - <c:out
						value="${player.playerPhotoUrl}" /><span>- Added Date :
						${player.createdDate}</span></li>

			</c:forEach>
		</ul>
	</div>

</body>
</html>