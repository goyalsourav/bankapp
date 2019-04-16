<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<h3>Enter User details</h3>
	<form action="save" method="post">
		<!-- Account Id<input type="text" name="id" required autofocus /> <br> -->
		Account Type<input type="text" name="accounttype" required autofocus />
		<br> Balance<input type="text" name="balance" required /><br>
		User Name<input type="text" name="name" required autofocus /> <br>
		User mobile<input type="text" name="mobile" required autofocus /> <br>

		<input type="submit" value="Add account">
	</form>
</body>
</html>