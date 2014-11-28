<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD NEW PLATFORM</title>
</head>
<body>

	<div>
		<form:form method="post" action="addPlatformToDB" modelAttribute="platform">
			<table>
				<tr>
					<td>Platform name :</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Master platform :</td>
					<td>
						<form:select path="masterPlatform" items="${map.masterPlatforms}" />
					</td>
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