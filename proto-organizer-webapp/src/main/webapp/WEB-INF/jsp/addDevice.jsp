<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD NEW DEVICE</title>
</head>
<body>

	<div>
		<form:form method="post" action="addDeviceToDB" modelAttribute="device">
			<table>
				<tr>
					<td>Device</td>
					<td><form:input path="device" /></td>
				</tr>
				<tr>
					<td>Platform</td>
					<td>
						<form:select path="platformId" items="${map.platformsList}" itemLabel="fullName" itemValue="id" />
					</td>
				</tr>
				<tr>
					<td>IMEI/SN</td>
					<td><form:input path="imei" /></td>
				</tr>
				<tr>
					<td>Project</td>
					<td>
						<form:select path="projectId" items="${map.projectsList}" itemValue="id"/>
					</td>
				</tr>
				<tr>
					<td>Owner</td>
					<td>
						<form:select path="ownerId" items="${map.usersList}" itemValue="id" />
					</td>
				</tr>
				<tr>
					<td>Originally purchased by</td>
					<td><form:input path="origin" /></td>
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