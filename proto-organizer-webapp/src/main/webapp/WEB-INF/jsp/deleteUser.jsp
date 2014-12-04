<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>REMOVE USER</title>
</head>
<body>
	<center>

		<form:form method="post" action="deleteUsersFromDB"
			commandName="helper">

			<table border="1">
				<tr>
					<td class="heading">FIRST NAME</td>
					<td class="heading">LAST NAME</td>
					<td class="heading">EMAIL</td>
					<td class="heading">BARCODE</td>
					<td class="heading">SPECIAL RIGHTS</td>
					<td class="heading"></td>
				</tr>
				<c:forEach var="user" items="${usersList}">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
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
