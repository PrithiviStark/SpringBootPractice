<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resume sender</title>

<link rel="icon" href="/images/thamizhpro_icon-removebg.ico"
	type="image/x-icon" />
</head>
<body>
	<h1>Send Email with Attachment</h1>
	<form method="POST" action="/mailresume" enctype="multipart/form-data">

		<label for="name">HR Name:</label> <input type="text" id="name"
			name="name" required><br> <br> <label
			for="toAddress">HR mail:</label> <input type="text" id="toAddress"
			name="toAddress" required><br> <br> <label
			for="company">Company Name:</label> <input type="text" id="company"
			name="company" required><br> <br> <label
			for="source">Source of Job:</label> <input type="text" id="source"
			name="source" required><br> <br> <label for="file">Attachments:</label>
		<input type="file" id="file" name="file" required multiple="multiple"><br>
		<br> <input type="submit" value="Send Email">
	</form>

	<br>
	<hr>
	<a href="/index">click here</a> to index.
	<br>
	<hr>
	<a href="/logout">click here</a> to logout.
</body>
</html>