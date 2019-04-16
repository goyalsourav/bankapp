<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
	<h2>welcome to the user login page</h2>
	<form action="admin/userlogin" method="post">
		Login Id <input type="text" name="username" required autofocus /> <br>
		Password <input type="password" name="password" required autofocus />
		<br> <input type="Submit" value=" User Login">
	</form>
	<br><hr>
	we respect your privacy
	<hr>
</body>
</html>