<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<script src="<c:url value="/resources/jquery.js" />"></script>
	<script src="<c:url value="/resources/jquery-ui.js" />"></script>
	
	<link rel="stylesheet" type="text/css"
		href="<c:url value='/resources/jquery-ui.css'/>" />
	<link rel="stylesheet" type="text/css"
		href="<c:url value='/resources/main.css'/>" />
	<title>Košice Devices GlobalLogic</title>
</head>
<body>
	<center>
		<div id="dialog" title="" style="display: none;"></div>
		<div id="header">

			<div id="settings">
				<a href="logout">LOGOUT</a>
				<a href="changePassword" class="dialog">CHANGE PASSWORD</a>
				<a href="userProfile">${username}</a>
				 
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
					<li><a href="#" class="bold">REMOVE</a>

						<ul>
							<li><a href="deleteDevice" class="dialog">REMOVE DEVICE</a></li>
							<li><a href="deleteUser" class="dialog">REMOVE USER</a></li>
							<li><a href="deleteProject" class="dialog">REMOVE PROJECT</a></li>
							<li><a href="deletePlatform" class="dialog">REMOVE PLATFORM</a></li>
							<li><a href="deleteVault" class="dialog">REMOVE VAULT</a></li>
						</ul></li>
					<li><a href="#" class="bold toggleEdit">EDIT</a></li>
					<li><a href="#" onclick='javascript:window.print()'>PRINT</a>
					</li>
					<li><a href="downloadExcel">EXPORT</a></li>

					<li>
						<div id="tfheader">
							<form id="tfnewsearch" method="post" action="getListAdmin">
								<input type="text" class="tftextinput" name="q" size="13"
									maxlength="120" placeholder="Search here..." required value="${q}">
								<a id="clearSearch" href="getListAdmin">x</a>
								<input type="submit" value=" "
									class="tfbutton">
							</form>
							<div class="tfclear"></div>
						</div>
					</li>
				</ul>
			</div>
			<!-- Nav wrapper end -->
		</div>
		<!-- Nav end -->

<form:form method="post" action="updateDevicesView" modelAttribute="devicesViewWrapper">

		<div id="main_table">
			<table border="0">
			<thead>
				<tr >
					<td class="heading">DEVICE
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=1"></a> 
							<a class="arrow-down" href="getListAdmin?sort=2"></a>
						</div>
					</td>
					<td class="heading">PLATFORM
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=3"></a> 
							<a class="arrow-down" href="getListAdmin?sort=4"></a>
						</div>
					</td>
					<td class="heading">IMEI/SN
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=5"></a> 
							<a class="arrow-down" href="getListAdmin?sort=6"></a>
						</div>
					</td>
					<td class="heading">PROJECT
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=9"></a> 
							<a class="arrow-down" href="getListAdmin?sort=10"></a>
						</div>
					</td>
					<td class="heading">ORIGIN
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=17"></a> 
							<a class="arrow-down" href="getListAdmin?sort=18"></a>
						</div>
					</td>
					<td class="heading">OWNER
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=11"></a> 
							<a class="arrow-down" href="getListAdmin?sort=12"></a>
						</div>
					</td>
					<td class="heading">LAST EDIT
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=13"></a> 
							<a class="arrow-down" href="getListAdmin?sort=14"></a>
						</div>
					</td>
					<td class="heading">DATE
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=15"></a> 
							<a class="arrow-down" href="getListAdmin?sort=16"></a>
						</div>
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
							<input type="hidden" value="${device.ownerId}" />
 							<span class="hideDisabled" >${device.getOwnerFullName()}</span>
						</td>
						<td>
							<input type="hidden" value="${device.lastModifiedBy}" />
 							<span class="hideDisabled" >${device.getModifierFullName()}</span>
						</td>
						<td class="hideDisabled">
							<fmt:formatDate pattern="yy MMM dd" value="${device.date}" />
							<br/>
							<span style="color:gray;"><fmt:formatDate pattern="HH:mm" value="${device.date}" /></span>
						</td>
						<td><a class="newButton dialog hideEnabled" href="selectUser?id=${device.id}">MOVE
								TO</a></td>
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
	<script>
		$(document).ready(function() {
			$(".dialog").click(function(e) {
				e.preventDefault();
				$("#dialog").load($(this).attr("href"), 
					function()
					{
						var theDialog = $("#dialog").dialog({
							width : 'auto',
							height : 'auto',
							modal: true,
							resizable: false,
							draggable: true,
							modal : true,
							open: function() {
								$(".ui-dialog-title").text($("#dialog").find("title").text());
						     },
							close : function() {
								$("#thedialog").attr('src', "about:blank");
							}
						});
						
						setTimeout(function(){ theDialog.dialog('open') }, 100);
					});
				
				return false;
			});
			
			$(".toggleEdit").click(function(e)
				{
					e.preventDefault();
					if( $(".hideEnabled:first").css("display") == 'none')
						{
							$(".hideEnabled").css("display", "block");
							$(".showEnabled").css("display", "none");
							$(".hideDisabled").css("color", "white");
						}
					else
						{
							$(".hideEnabled").css("display", "none");
							$(".showEnabled").css("display", "block");
							$(".hideDisabled").css("color", "gray");
						}
				});
		});
		function showRemovedDevices() {
			if($("#removed_devices_table").css("display") == "none") {
				$("#main_table").toggle(600, function() {
						$("#removed_devices_table").toggle(600);
					});
				$("#toggleRemovedDevices").text("ACTIVE DEVICES");
				}
			else {
				$("#removed_devices_table").toggle(600, function() {
					$("#main_table").toggle(600);
				});
				$("#toggleRemovedDevices").text("REMOVED DEVICES");
			}
		}
	</script>
</body>
</html>
