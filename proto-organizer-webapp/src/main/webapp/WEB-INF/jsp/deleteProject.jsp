<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MANAGE PROJECTS</title>
</head>
<body>
	<center>

		
		<form:form method="post" action="deactivateProjectsFromDB" commandName="helper" >
		
<%-- 		<form:checkboxes element="li" path="ids" items="${projectList}" itemValue="id"/> --%>
		
		<ul>
			<li style="text-align:left;">deactivate</li>
		<c:forEach var="project" items="${projectList}" varStatus="status">
			<li>
				<input type="checkbox" name="ids" value="${project.id}" ${project.isActive ? "" : "checked" } style="min-width:100px;'" />
				<label style='${project.isActive ? "" : "color:gray;" }'>${project.projectName}</label>
			</li>
		</c:forEach>
		</ul>
		<br />
		<input type="submit" value="Deactivate" />		
		<input type="button" class="cancel-button" value="Cancel" onClick="javascript:$('#dialog').dialog('close');" />
		
		</form:form>
	</center>
</body>
</html>