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
		<form:form id="changeOwnerForm" method="post" action="moveTo?deviceID=${helper.selectedDeviceID}" commandName="helper" style="min-width:500px; min-height:120px; overflow: hidden;">

			
				User :
				<form:select path="userID" id="userSelect" data-placeholder="Choose new owner" class="chosen-select">
					<c:if test="${vaultsList != null}">
						<optgroup label="--- Vaults ---" >
							<c:forEach var="userInHelper" items="${vaultsList}">
								<form:option value="${userInHelper.id}" label="${userInHelper.getFullName()}" />
							</c:forEach>
						</optgroup>
					</c:if>
					<optgroup label="--- Users ---" >
						<c:forEach var="userInHelper" items="${usersList}">
							<form:option value="${userInHelper.id}" label="${userInHelper.getFullName()}" />
						</c:forEach>
					</optgroup>
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
		$("#userSelect").chosen({
		    disable_search_threshold: 5,
		    no_results_text: "No match",
		    width: "95%"
		  }).on('chosen:showing_dropdown', function(evt, params) {
			  $("#changeOwnerForm").css("min-height", "350px");
		  }).on('chosen:hiding_dropdown', function(evt, params) {
			  $("#changeOwnerForm").css("min-height", "120px");
		  });
	</script>
</body>
</html>
