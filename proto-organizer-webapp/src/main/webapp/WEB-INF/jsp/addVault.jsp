<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADD NEW VAULT</title>
</head>
<body>
	<div>
		<form:form method="post" action="addVaultToDB" modelAttribute="user">
			<table id="addUserTable">
				<tr>
					<td>Name</td>
					<td>
						<form:input path="lastName" />
						<input type="hidden" value="false" name="isPerson" />
					</td>
				</tr>
				<tr>
					<td>Barcode</td>
					<td><form:input path="barcode" /></td>
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