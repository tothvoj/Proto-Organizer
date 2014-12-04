<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CHANGE OWNER</title>
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
