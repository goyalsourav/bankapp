<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Login</title>
</head>
<body>
	<h2>welcome to the manager login page</h2>
	<form action="admin/login" method="post">
		Login Id <input type="text" name="username" required autofocus /> <br>
		Password <input type="password" name="password" required autofocus />
		<br> <input type="Submit" value="Manager Login">
	</form>
	<br><hr>
	<a href="index.jsp">Home</a>
</body>
</html>