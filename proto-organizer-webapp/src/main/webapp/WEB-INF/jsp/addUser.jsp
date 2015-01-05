<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADD NEW USER</title>
</head>
<body>
	<div>
		<form:form method="post" action="addUserToDB" modelAttribute="user">
			<table id="addUserTable">
				<tr>
					<td>Last name</td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td>First name</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Barcode</td>
					<td><form:input path="barcode" /></td>
				</tr>
				<tr>
					<td>Special rights</td>
					<td>
						<form:radiobutton path="rights" value="true" label="yes" />
						<form:radiobutton path="rights" value="false" label="no" />
					</td>
				</tr>
				<!-- <tr>
					<td>User is</td>
					<td>
						<form:radiobutton path="isPerson" value="true" label="Person" />
						<form:radiobutton path="isPerson" value="false" label="Vault" />
					</td>
				</tr>  -->
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