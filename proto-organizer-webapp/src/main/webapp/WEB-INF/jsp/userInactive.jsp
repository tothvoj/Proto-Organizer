<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<script src="<c:url value="/resources/jquery.js" />"></script>
	<script src="<c:url value="/resources/jquery-ui.js" />"></script>
	<script src="<c:url value="/resources/chosen.jquery.js" />"></script>
	<script src="<c:url value="/resources/jquery.dataTables.min.js" />"></script>
	<script src="<c:url value="/resources/dataTables.search-highlight.js" />"></script>
	<script src="<c:url value="/resources/jquery.toastmessage.js" />"></script>
	<script src="<c:url value="/resources/main.js" />"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery-ui.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/main.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/chosen.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/jquery.toastmessage.css'/>" />
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/fonts/flaticon/flaticon.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/tooltip.css'/>" />
	
	<title>Košice Devices GlobalLogic</title>
</head>
<body>
	<center>
		<div id="dialog" title="" style="display: none;"></div>
		<div id="header">
			
		</div>

		<div >
			User is no longer active. Reapply for a permission to use this app.
			<br /><br/><br/>
			<a href="getAdminList" >&lt; Back</a>
		</div>
	</center>
	<div id="footer" >
		<span>Copyright © 2014 Košice</span>
	</div>
</body>
</html>
