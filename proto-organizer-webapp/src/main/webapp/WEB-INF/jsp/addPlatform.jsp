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
					<td>Platform name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Master platform</td>
					<td>
						<form:select path="masterPlatform" items="${masterPlatforms}" itemLabel="name" itemValue="id" />
					</td>
				</tr>
				<tr>
					<td>Platform version</td>
					<td><form:input path="version" /></td>
				</tr>
				<tr>
					<td>Comment</td>
					<td><form:input path="comment" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Save" />
						<input type="button" class="cancel-button" value="Cancel" onClick="javascript:$('#dialog').dialog('close');" />
					</td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>