<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ko¹ice Devices GlobalLogic</title>
<style>
body {
	font-size: 20px;
	color: teal;
	font-family: Calibri;
}

td {
	font-size: 15px;
	color: black;
	width: 100px;
	height: 22px;
	text-align: center;
}

.heading {
	font-size: 18px;
	color: white;
	font: bold;
	background-color: orange;
	border: thick;
}
</style>
</head>
<body>
	<center>

		<form:form method="post" action="deleteUsersFromDB"
			commandName="helper">

			<table border="1">
				<tr>
					<td class="heading">NAME</td>
					<td class="heading">EMAIL</td>
					<td class="heading">BARCODE</td>
					<td class="heading">SPECIAL RIGHTS</td>
					<td class="heading"></td>
				</tr>
				<c:forEach var="user" items="${usersList}">
					<tr>
						<td>${user.name}</td>
						<td>${user.email}</td>
						<td>${user.barcode}</td>
						<td>${user.rights}</td>
						<td><form:checkbox path="ids" value="${user.id}" /></td>
					</tr>
				</c:forEach>
				
			</table>
			<br />
			<input type="submit" value="Delete" />

		</form:form>

	</center>
</body>
</html>
