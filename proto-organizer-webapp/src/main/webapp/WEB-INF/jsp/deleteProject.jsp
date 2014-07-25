<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DELETE PROJECT</title>
</head>
<body>
	<center>

		
		<form:form method="post" action="deleteProjectsFromDB" commandName="helper" >
		
		<form:checkboxes element="li" path="ids" items="${projectList}" itemValue="id"/>
		
		<br />
		<input type="submit" value="Delete" />		
		
		</form:form>
	</center>
</body>
</html>