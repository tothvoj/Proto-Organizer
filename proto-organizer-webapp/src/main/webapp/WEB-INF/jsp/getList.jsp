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
			<div id="settings">
				<div id="settings_wrapper">
					<ul>
						<li>
							<a href="userProfile">${username}</a>
							<input type="hidden" id="userId" value="${userId}" />
							<ul>
								<li><a href="changePassword" class="dialog">CHANGE PASSWORD</a></li>
								<li><a href="logout">LOGOUT</a><li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<form:form method="post" action="updateDevicesView" modelAttribute="devicesViewWrapper">

		<div >
			<table id="main_table" border="0">
			<thead>
				<tr>
					<td class="heading">DEVICE</td>
					<td class="heading">PLATFORM</td>
					<td class="heading">IMEI/SN</td>
					<td class="heading">PROJECT</td>
					<td class="heading">ORIGIN</td>
					<td class="heading">OWNER</td>
					<td class="heading">LAST EDIT</td>
					<td class="heading">DATE</td>
					<td class="heading">REASSIGN</td>
				</tr>
				</thead>
				<c:forEach var="device" items="${devicesViewWrapper.devicesList}" varStatus="status">
					<tr>
						<td>
							<input name="devicesList[${status.index}].id" value="${device.id}" type="hidden"/>
							<a href="deviceHistory?id=${device.id}" class="dialog"><span class="hideEnabled">${device.device}</span></a>
						</td>
						<td>
							<input type="hidden" value="${device.platformId}" />
							<span class="hideEnabled" >${device.platformName} ${device.platformVersion}</span>
						</td>
						<td>
							<span class="hideEnabled">${device.imei}</span>
						</td>
						<td>
							<input type="hidden" value="${device.projectId}" />
 							<span class="hideEnabled" >${device.projectName}</span>
						</td>
						<td>
							<span class="hideEnabled">${device.origin}</span>
						</td>
						<td>
							<c:if test="${device.ownerId != userId}" >
								<c:if test='${device.status == "taken-offsite"}' >
									<div class="flaticon">
										<span class="flaticon flaticon-plane19" title="device taken offsite/abroad"></span>
									</div>
								</c:if>
								<c:if test='${device.status == "taken-home"}' >
									<div class="flaticon">
										<span class="flaticon-house158" title="device taken home"></span>
									</div>
								</c:if>
								<input type="hidden" value="${device.ownerId}" />
								<span class="hideDisabled" >${device.getOwnerLastName()}</span>
	 							<br />
	 							<span class="hideDisabled minor-text" >${device.getOwnerFirstName()}</span>
 							</c:if>
 							<c:if test="${device.ownerId == userId}">
 								<input type='checkbox' name='home${status.index}' id="home${status.index}" class="flaticon home" 
 									deviceid="${device.id}" ${device.status == "taken-home" ? "checked" : "" } />
 								<label class="flaticon-house158" for="home${status.index}" title="taking device home"></label>
 								
 								<input type='checkbox' name='thing${status.index}' id="thing${status.index}" class="flaticon offsite" 
 									deviceid="${device.id}" ${device.status == "taken-offsite" ? "checked" : "" } />
 								<label class="flaticon-plane19" for="thing${status.index}" title="taking device for a trip"></label>
 							</c:if> 
						</td>
						<td>
							<input type="hidden" value="${device.lastModifiedBy}" />
 							<span class="hideDisabled" >${device.getModifierLastName()}</span>
 							<br />
 							<span class="hideDisabled minor-text" >${device.getModifierFirstName()}</span>
						</td>
						<td class="hideDisabled">
							<fmt:formatDate pattern="yy MMM dd" value="${device.date}" />
							<br/>
							<span class="minor-text"><fmt:formatDate pattern="HH:mm" value="${device.date}" /></span>
						</td>
						<td>
						  	<c:if test="${device.isOwnerAPerson}">
					      		<a class="newButton dialog hideEnabled" href="selectUser?id=${device.id}">MOVE TO</a>
					      	</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
	</center>
	<div id="footer" >
		<span>Copyright © 2014 Košice</span>
	</div>
</body>
</html>
