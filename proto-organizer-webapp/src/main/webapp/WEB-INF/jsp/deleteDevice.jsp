<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>MANAGE DEVICES</title>
</head>
<body>
	<center>

		<form:form method="post" action="deleteDevicesFromDB" commandName="helper" >

			<table border="1">
				<tr>
					<td class="heading">DEVICE</td>
					<td class="heading">PLATFORM</td>
					<td class="heading">IMEI/SN</td>
					<td class="heading">PROJECT</td>
					<td class="heading">OWNER</td>
					<td class="heading">LAST MODIFIED</td>
					<td class="heading">DATE</td>
					<td class="heading"></td>
				</tr>
				<c:forEach var="device" items="${devicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platformName} ${device.platformVersion}</td>
						<td>${device.imei}</td>
						<td>${device.projectName}</td>
						<td>${device.getOwnerFullName()}</td>
						<td>${device.getModifierFullName()}</td>
						<td>
							<fmt:formatDate pattern="yy MMM dd" value="${device.date}" />
							<br/>
							<span style="color:gray;"><fmt:formatDate pattern="HH:mm" value="${device.date}" /></span>
						</td>
						<td><form:checkbox path="ids" value="${device.id}" /></td>
					</tr>
				</c:forEach>
				
			</table>
			
			<br />
			<input id="deleteDevicesButton" type="button" value="Delete" />
			<input type="button" class="cancel-button" value="Cancel" onClick="javascript:$('#dialog').dialog('close');" />
		</form:form>
	</center>
</body>
</html>
