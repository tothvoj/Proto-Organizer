<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CHANGE OWNER</title>
</head>
<body>
	<center>
		<form:form method="post" action="moveTo?deviceID=${helper.selectedDeviceID}" commandName="helper">

			
				User :
					<form:select path="userID" id="userSelect">
					<c:if test="${vaultsList != null}">
						<form:option value="0" label="--- Vaults ---" class="optionHeader"/>
						<form:option value="0" label=" " />
						<c:forEach var="userInHelper" items="${vaultsList}">
							<form:option value="${userInHelper.id}" label="${userInHelper.getFullName()}" />
						</c:forEach>
						<form:option value="0" label=" " />
					</c:if>
					<form:option value="0" label="--- Users ---" class="optionHeader"/>
					<form:option value="0" label=" " />
				<c:forEach var="userInHelper" items="${usersList}">
					<form:option value="${userInHelper.id}" label="${userInHelper.getFullName()}" />
				</c:forEach>
			</form:select>

			<br />
			<br />
			<input id="changeOwnerSubmit" type="submit" value="Move To" />
		</form:form>
	</center>
	<script type="text/javascript" >
		$("#userSelect").change(function() {
			if($("#userSelect").val() == 0)
				{
					$("#changeOwnerSubmit").prop("disabled", true);
				}
			else
				{
					$("#changeOwnerSubmit").prop("disabled", false);
				}
	});
	</script>
</body>
</html>
