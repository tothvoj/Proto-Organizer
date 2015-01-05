<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MANAGE PLATFORMS</title>
</head>
<body>
	<center>

		
		<form:form method="post" action="deactivatePlatformsFromDB" commandName="helper" >
		
<%-- 		<form:checkboxes element="li" path="ids" items="${platformsList}" itemLabel="fullName" itemValue="id"/> --%>
		
		<ul>
			<li style="text-align:left;">deactivate</li>
		<c:forEach var="platform" items="${platformsList}" varStatus="status">
			<li>
				<input type="checkbox" name="ids" value="${platform.id}" ${platform.isActive ? "" : "checked" } style="min-width:100px;'" />
				<label style='${platform.isActive ? "" : "color:gray;" }'>${platform.name}</label>
			</li>
		</c:forEach>
		</ul>
		
		<br />
		<input type="submit" value="Deactivate" />		
		
		</form:form>
	</center>
</body>
</html>