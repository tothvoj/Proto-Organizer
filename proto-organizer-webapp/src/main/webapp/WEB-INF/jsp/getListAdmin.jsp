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
		<div id="nav">
			<div id="nav_wrapper">
				<ul>
					<li><a href="#" class="bold">ADD</a>
						<ul>
							<li><a href="addDevice" class="dialog">NEW DEVICE</a></li>
							<li><a href="addUser" class="dialog">NEW USER</a></li>
							<li><a href="addProject" class="dialog">NEW PROJECT</a></li>
							<li><a href="addPlatform" class="dialog">NEW PLATFORM</a></li>
							<li><a href="addVault" class="dialog">NEW VAULT</a></li>
						</ul></li>
					<li><a href="#" class="bold">MANAGE</a>

						<ul>
							<li><a href="deleteDevice" class="dialog">MANAGE DEVICES</a></li>
							<li><a href="deleteUser" class="dialog">MANAGE USERS</a></li>
							<li><a href="deleteProject" class="dialog">MANAGE PROJECTS</a></li>
							<li><a href="deletePlatform" class="dialog">MANAGE PLATFORMS</a></li>
							<li><a href="deleteVault" class="dialog">MANAGE VAULTS</a></li>
						</ul></li>
					<li><a href="#" class="bold toggleEdit">EDIT</a></li>
					<li><a href="#" onclick='javascript:window.print()'>PRINT</a>
					</li>
					<li><a href="downloadExcel">EXPORT</a></li>

					<li>
						<div id="tfheader">
							<input type="search" class="tftextinput" maxlength="120" placeholder="Search here...">
						</div>
					</li>
				</ul>
			</div>
			<!-- Nav wrapper end -->
		</div>
		<!-- Nav end -->

<form:form method="post" action="updateDevicesView" modelAttribute="devicesViewWrapper">

		<div >
			<table id="main_table" border="0">
			<thead>
				<tr >
					<td class="heading">DEVICE
<!-- 						<div id="arrows">
								<a class="arrow-up" href="getListAdmin?sort=1"></a>
	 							<a class="arrow-down" href="getListAdmin?sort=2"></a>
							</div> -->
					</td>
					<td class="heading">PLATFORM
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=3"></a> 
							<a class="arrow-down" href="getListAdmin?sort=4"></a>
						</div> -->
					</td>
					<td class="heading">IMEI/SN
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=5"></a> 
							<a class="arrow-down" href="getListAdmin?sort=6"></a>
						</div> -->
					</td>
					<td class="heading" style="min-width: 100px;">PROJECT
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=9"></a> 
							<a class="arrow-down" href="getListAdmin?sort=10"></a>
						</div> -->
					</td>
					<td class="heading" style="min-width: 85px;">ORIGIN
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=17"></a> 
							<a class="arrow-down" href="getListAdmin?sort=18"></a>
						</div> -->
					</td>
					<td class="heading">OWNER
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=11"></a> 
							<a class="arrow-down" href="getListAdmin?sort=12"></a>
						</div> -->
					</td>
					<td class="heading">LAST EDIT
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=13"></a> 
							<a class="arrow-down" href="getListAdmin?sort=14"></a>
						</div> -->
					</td>
					<td class="heading">DATE
<!--						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=15"></a> 
							<a class="arrow-down" href="getListAdmin?sort=16"></a>
						</div> -->
					</td>
					<td class="heading">REASSIGN</td>
				</tr>
				</thead>
				<c:forEach var="device" items="${devicesViewWrapper.devicesList}" varStatus="status">
					<tr>
						<td>
							<input name="devicesList[${status.index}].id" value="${device.id}" type="hidden"/>
							<a href="deviceHistory?id=${device.id}" class="dialog"><span class="hideEnabled">${device.device}</span></a>
							<input class="showEnabled" name="devicesList[${status.index}].device" value="${device.device}"/>
						</td>
						<td>
							<input type="hidden" value="${device.platformId}" />
							<span class="hideEnabled" >${device.platformName} ${device.platformVersion}</span>
							<form:select path="devicesList[${status.index}].platformId" class="showEnabled" items="${devicesViewWrapper.platforms}" multiple="false" itemValue="id" itemLabel="fullName"/>
						</td>
						<td>
							<span class="hideEnabled">${device.imei}</span>
							<input class="showEnabled" name="devicesList[${status.index}].imei" value="${device.imei}"/>
						</td>
						<!-- <td>
							<c:choose>
						      	<c:when test="${device.status == 'in'}">
						      		<img src="resources/images/Vault-24.png" />
						      	</c:when>
								<c:when test="${device.status == 'out'}">
						      		<img src="resources/images/user1.png" />
						      	</c:when>
						      	<c:otherwise>
							      	-
						      	</c:otherwise>
							</c:choose>
						</td> -->
						<td>
							<input type="hidden" value="${device.projectId}" />
 							<span class="hideEnabled" >${device.projectName}</span>
  							<form:select path="devicesList[${status.index}].projectId" class="showEnabled" items="${devicesViewWrapper.projects}" multiple="false" itemValue="id" itemLabel="projectName"/>
						</td>
						<td>
							<span class="hideEnabled">${device.origin}</span>
							<input class="showEnabled" name="devicesList[${status.index}].origin" value="${device.origin}"/>
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
 								<label class="flaticon-house158 hideEnabled" for="home${status.index}" title="taking device home"></label>
 								
 								<input type='checkbox' name='thing${status.index}' id="thing${status.index}" class="flaticon offsite" 
 									deviceid="${device.id}" ${device.status == "taken-offsite" ? "checked" : "" } />
 								<label class="flaticon-plane19 hideEnabled" for="thing${status.index}" title="taking device for a trip"></label>
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
								<c:if test="${device.isOwnerAPerson == false}">
						      		<a class="newButton disabled hideEnabled" >MOVE TO</a>
						      	</c:if>
						    <a class="dialog showEnabled ui-icon ui-icon-trash ui-icon-green ui-icon-scale1-5" href="deleteSingleDevice?id=${device.id}" ></a>	
						</td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<input type="submit" class="showEnabled" value="Save"/>
	</form:form>
	</center>
	<center>
		<div id="removed_devices_table">
			<table border="0">
			<thead>
				<tr >
					<td class="heading">DEVICE
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=1"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=2"></a>
						</div>
					</td>
					<td class="heading">PLATFORM
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=3"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=4"></a>
						</div>
					</td>
					<td class="heading">IMEI/SN
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=5"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=6"></a>
						</div>
					</td>
					<td class="heading">PROJECT
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=9"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=10"></a>
						</div>
					</td>
					<td class="heading">ORIGIN
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=17"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=18"></a>
						</div>
					</td>
					<td class="heading">OWNER
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=11"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=12"></a>
						</div>
					</td>
					<td class="heading">LAST EDIT
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=13"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=14"></a>
						</div>
					</td>
					<td class="heading">DATE
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?show=removed&sort=15"></a> 
							<a class="arrow-down" href="getListAdmin?show=removed&sort=16"></a>
						</div>
					</td>
				</tr>
				</thead>
				<c:forEach var="device" items="${devicesViewWrapper.removedDevicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platformName}</td>
						<td>${device.imei}</td>
						<td>${device.projectName}</td>
						<td>${device.origin}</td>
						<td>${device.getOwnerFullName()}</td>
						<td>${device.getModifierFullName()}</td>
						<td>
							<fmt:formatDate pattern="yy MMM dd" value="${device.date}" />
							<br/>
							<span style="color:gray;">
								<fmt:formatDate pattern="HH:mm" value="${device.date}" />
							</span>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</center>
	<div id="footer" >
		<a id="toggleRemovedDevices" onclick="showRemovedDevices()">REMOVED DEVICES</a>
		<span>Copyright © 2014 Košice</span>
	</div>
</body>
</html>
