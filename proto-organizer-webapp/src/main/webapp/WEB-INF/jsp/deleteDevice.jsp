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

		<form:form method="post" action="deleteDevicesFromDB" commandName="helper" >

			<table border="1">
				<tr>
					<td class="heading">DEVICE</td>
					<td class="heading">PLATFORM</td>
					<td class="heading">IMEI/SN</td>
					<td class="heading">STATUS</td>
					<td class="heading">PROJECT</td>
					<td class="heading">OWNER</td>
					<td class="heading">LAST MODIFIED</td>
					<td class="heading">DATE</td>
					<td class="heading"></td>
				</tr>
				<c:forEach var="device" items="${devicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platform}</td>
						<td>${device.imei}</td>
						<td>${device.status}</td>
						<td>${device.project}</td>
						<td>${device.owner}</td>
						<td>${device.last_modified}</td>
						<td>${device.date}</td>
						<td><form:checkbox path="ids" value="${device.id}" /></td>
					</tr>
				</c:forEach>
				
			</table>
			
			<br />
			<input type="submit" value="Delete" />

		</form:form>

	</center>
</body>
</html>
