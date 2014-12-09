<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>
</head>
<body>
	<center>

		<form:form method="post" action="changePasswordInDB"
			modelAttribute="helper">
			<table>
				<tr>
					<td>Current password :</td>
					<td><form:input path="currentPassword" type="password" /></td>
				</tr>
				<tr>
					<td>New password :</td>
					<td><form:input path="newPassword" type="password" /></td>
				</tr>
				<tr>
					<td>Repeat new password :</td>
					<td><form:input path="newPasswordRepeat" type="password" /></td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="submit" value="Change" /> 
					</td>
				</tr>

			</table>
		</form:form>
	</center>
</body>
</html>