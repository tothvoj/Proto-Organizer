<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/main.css'/>" />
<title>Login</title>
</head>
<body>
	<center>
		<div id="header"></div>
		<div id="login-error">${error}</div>
		<form action="<c:url value="/j_spring_security_check"></c:url>" method="post" role="form">
			<p>
				<!-- <label for="j_username">Email</label> -->
				<input id="j_username" name="j_username" type="text" placeholder="Email" />
			</p>
			<p>
				<!-- <label for="j_password">Password</label> --> 
				<input id="j_password" name="j_password" type="password" placeholder="Password" />
			</p>
			<p>
				<input type="submit" name="login" value="Login" />
			</p>
		</form>
	</center>
	<div id="footer" >
		<span>Copyright © 2014 Košice</span>
	</div>
</body>
</html>