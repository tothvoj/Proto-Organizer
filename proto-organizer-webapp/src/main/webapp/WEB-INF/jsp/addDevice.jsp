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
					<td>Device :</td>
					<td><form:input path="device" /></td>
				</tr>
				<tr>
					<td>Platform :</td>
					<td><form:input path="platform" /></td>
				</tr>
				<tr>
					<td>IMEI/SN :</td>
					<td><form:input path="imei" /></td>
				</tr>
				<tr>
					<td>Status :</td>
					<td><form:radiobuttons path="status" items="${map.statusList}" /></td>
				</tr>
				<tr>
					<td>Project :</td>
					<td><form:select path="project">
							<form:option value="NONE" label="--- Select ---" />
							<form:options items="${map.projectsList}" />
						</form:select></td>
				</tr>
				<tr>
					<td>Owner :</td>
					<td><form:select path="owner" items="${map.usersList}" /></td>
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