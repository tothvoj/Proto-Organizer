<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Device History</title>
</head>
<body>
	<center>
		<table>
			<tr>
				<td>Device</td>
				<td>${device.device}</td>
			</tr>
			<tr>
				<td>IMEI</td>
				<td>${device.imei}</td>
			</tr>
		</table>
	</center>
	<center>
		<table border="1">
				<tr>
					<td class="heading">DATE</td>
					<td class="heading">ACTION</td>
					<td class="heading">USER</td>
				</tr>
				<c:forEach var="deviceUsageView" items="${deviceUsage}">
					<tr>
						<td>
							<fmt:formatDate pattern="yyyy MMM dd" value="${deviceUsageView.date}" />
							&nbsp;
							<span style="color:gray;">
								<fmt:formatDate pattern="HH:mm" value="${deviceUsageView.date}" />
							</span>
						</td>
						<td>${deviceUsageView.actionName}</td>
						<td>${deviceUsageView.lastName} ${deviceUsageView.firstName}</td>
					</tr>
				</c:forEach>
				
			</table>
	</center>
</body>
</html>
