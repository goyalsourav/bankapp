<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account List</title>
</head>
<body>
	<h3>Account Details</h3>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Account Type</th>
				<th>Balance</th>
				<th>Name</th>
				<th>Mobile</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="account" items="${ACCOUNTLIST}">
				<tr>
					<td>${account.id}</td>
					<td>${account.accounttype}</td>
					<td>${account.balance}</td>
					<td>${account.name}</td>
					<td>${account.mobile}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>