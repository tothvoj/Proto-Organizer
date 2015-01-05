<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>REMOVE DEVICE</title>
</head>
<body>
	<center>

		<form:form method="post" action="deleteSingleDeviceFromDB" modelAttribute="device" commandName="device" >
			Do you really want to delete
			<br />
			${device.device}
			<br />
			device ?
			<br />
			<br />
			<input id="deleteSingleDeviceSubmit" type="button" value="Delete" onclick="deleteSingleDevice()" />
			<input type="button" class="cancel-button" value="Cancel" onClick="javascript:$('#dialog').dialog('close');" />
			<input type="hidden" value="device=${device.device}&id=${device.id}" id="deleteSingleDeviceInput" />
		</form:form>
	</center>
</body>
</html>
