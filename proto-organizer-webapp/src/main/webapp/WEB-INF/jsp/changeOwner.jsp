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

		<form:form method="post" action="moveTo?deviceID=${helper.selectedDeviceID}" commandName="helper">

			
				User :
					<form:select path="userID">
				<c:forEach var="userInHelper" items="${usersList}">
					<form:option value="${userInHelper.id}" label="${userInHelper.getFullName()}" />
				</c:forEach>
			</form:select>

			<br />
			<br />
			<input type="submit" value="Move To" />

		</form:form>

	</center>
</body>
</html>
