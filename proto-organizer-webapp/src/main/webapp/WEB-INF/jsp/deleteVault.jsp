<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>MANAGE VAULTS</title>
</head>
<body>
	<center>

		<form:form method="post" action="deactivateUsersFromDB"
			commandName="helper">

			<table border="1">
				<tr>
					<td class="heading">NAME</td>
					<td class="heading">BARCODE</td>
					<td class="heading"></td>
				</tr>
				<c:forEach var="user" items="${usersList}">
					<tr>
						<td style='${user.isActive ? "" : "color:gray;" }'>${user.lastName}</td>
						<td style='${user.isActive ? "" : "color:gray;" }'>${user.barcode}</td>
						<td style='${user.isActive ? "" : "color:gray;" }'><input type="checkbox" name="ids" value="${user.id}" ${user.isActive ? "" : "checked" } /></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<input type="submit" value="Deactivate" />
			<input type="button" class="cancel-button" value="Cancel" onClick="javascript:$('#dialog').dialog('close');" />

		</form:form>

	</center>
</body>
</html>
