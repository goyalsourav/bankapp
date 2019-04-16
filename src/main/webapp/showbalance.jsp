<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance</title>
</head>
<body>
	<form action="admin/balance" method="get">
	    Enter the account id <input type="text" name="id" required /><br>
		<input type="Submit" value="check">

	</form>
	<h2>The Balance is:</h2>${ACCOUNT.balance}
</body>
</html>