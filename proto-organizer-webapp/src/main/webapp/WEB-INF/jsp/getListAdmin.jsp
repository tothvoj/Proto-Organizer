<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
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
	<title>Ko¹ice Devices GlobalLogic</title>
</head>
<body>
	<center>
		<div id="dialog" title="" style="display: none;"></div>
		<div id="header">

			<div id="settings">
				<a href="changePassword">CHANGE PASSWORD</a> <a href="logout">LOGOUT</a>
			</div>
		</div>
		<div id="nav">
			<div id="nav_wrapper">
				<ul>
					<li><a href="#" class="bold">ADD</a>
						<ul>
							<li><a href="addDevice" class="dialog">NEW DEVICE</a></li>
							<li><a href="addProject" class="dialog">NEW PROJECT</a></li>
							<li><a href="addUser" class="dialog">NEW USER</a></li>
						</ul></li>
					<li><a href="#" class="bold">REMOVE</a>

						<ul>
							<li><a href="deleteDevice" class="dialog">REMOVE DEVICE</a></li>
							<li><a href="deleteProject" class="dialog">REMOVE PROJECT</a></li>
							<li><a href="deleteUser" class="dialog">REMOVE USER</a></li>
						</ul></li>
					<li><a href="#" class="bold toggleEdit">EDIT</a></li>
					<li><a href="#" onclick='javascript:window.print()'>PRINT</a>
					</li>
					<li><a href="downloadExcel">EXPORT</a></li>

					<li><div id="tfheader">
							<form id="tfnewsearch" method="post" action="getListAdmin">
								<input type="text" class="tftextinput" name="q" size="13"
									maxlength="120"> <input type="submit" value=" "
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
			<table border="1">
				<tr style="border-bottom: 2px solid orange;">
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
					<td class="heading">OWNER
						<div id="arrows">
							<a class="arrow-up" href="getListAdmin?sort=11"></a> 
							<a class="arrow-down" href="getListAdmin?sort=12"></a>
						</div>
					</td>
					<td class="heading">LAST MODIFIED
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
				<c:forEach var="device" items="${devicesViewWrapper.devicesList}" varStatus="status">
					<tr>
						<td>
							<input name="devicesList[${status.index}].id" value="${device.id}" type="hidden" />
							<span class="hideEnabled">${device.device}</span>
<%-- 							<input class="showEnabled" name="${device.device}" value="${device.device}" style="display:none;" /> --%>
							<input class="showEnabled" name="devicesList[${status.index}].device" value="${device.device}" style="display:none;" />
						</td>
						<td>
							<input type="hidden" value="${device.platformId}" />
							<span class="hideEnabled platformName" >${device.platformName}</span>
							&nbsp;
 							<span class="hideEnabled firstName" >${device.platformVersion}</span>
						</td>
						<td>${device.imei}</td>
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
 							<span class="hideEnabled lastName" >${device.projectName}</span>
<%--  							<form:select path="projects" class="showEnabled" items="${devicesViewWrapper.projects}" multiple="false"/> --%>
						</td>
						<td>
							<input type="hidden" value="${device.ownerId}" />
 							<span class="hideEnabled lastName" >${device.getOwnerFullName()}</span>
						</td>
						<td>
							<input type="hidden" value="${device.lastModifiedBy}" />
 							<span class="hideEnabled lastName" >${device.getModifierFullName()}</span>
						</td>
						<td>
							<fmt:formatDate pattern="yy MMM dd" value="${device.date}" />
							<br/>
							<span style="color:gray;"><fmt:formatDate pattern="HH:mm" value="${device.date}" /></span>
						</td>
						<td><a class="button dialog" href="selectUser?id=${device.id}">MOVE
								TO</a></td>
					</tr>
				</c:forEach>

			</table>

		</div>
		<input type="submit" class="showEnabled" value="Save" style="display:none;" />
</form:form>

	</center>



	<center>

		<br /> <br /> <a class="button" onclick="showRemovedDevices()">REMOVED
			DEVICES</a> <br /> <br />
		<div id="removed_devices_table">
			<table border="1">

				<c:forEach var="device" items="${devicesViewWrapper.removedDevicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platformId}</td>
						<td>${device.imei}</td>
						<td>${device.status}</td>
						<td>${device.projectId}</td>
						<td>${device.ownerId}</td>
						<td>${device.lastModifiedBy}</td>
						<td>${device.date}</td>
						<td></td>
					</tr>
				</c:forEach>

			</table>

		</div>

	</center>


	<script>
		$(document).ready(function() {
			$(".dialog").click(function(e) {
				e.preventDefault();
				$("#dialog").load($(this).attr("href"));
				var theDialog = $("#dialog").dialog({
					width : 'auto',
					height : 'auto',
					resizable: false,
					draggable: false,
					modal : true,
					close : function() {
						$("#thedialog").attr('src', "about:blank");
					}
				});
				
				setTimeout(function(){ theDialog.dialog('open') }, 100);;	
				return false;
			});
			
			$(".toggleEdit").click(function(e)
				{
					e.preventDefault();
					if( $(".hideEnabled:first").css("display") == 'none')
						{
							$(".hideEnabled").css("display", "block");
							$(".showEnabled").css("display", "none");
						}
					else
						{
							$(".hideEnabled").css("display", "none");
							$(".showEnabled").css("display", "block");
						}
				});
		});
	</script>

	<script type="text/javascript">
		function loadPage() {
			$("#loaderPage").load("hello.jsp");
			$("#loaderPage").dialog({
				height : 600,
				width : 600,
				modal : true
			});
			return false;
		}
	</script>

	<script type="text/javascript">
		function showRemovedDevices() {
			document.getElementById('removed_devices_table').style.display = "block";
		}
	</script>
	
</body>
</html>
