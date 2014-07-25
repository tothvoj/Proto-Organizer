<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD NEW PROJECT</title>
</head>
<body>

	<div>
		<form:form method="post" action="addProjectToDB"
			modelAttribute="project">
			<table>
				<tr>
					<td>Project :</td>
					<td><form:input path="project_name" /></td>
				</tr>
				<tr>
					<td> </td>
					<td><input type="submit" value="Save" /></td>
				</tr>

			</table>
		</form:form>
	</div>

</body>
</html>