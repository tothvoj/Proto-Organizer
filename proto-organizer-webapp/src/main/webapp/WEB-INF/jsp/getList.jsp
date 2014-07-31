<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/main.css'/>" />
<title>Ko�ice Devices GlobalLogic</title>

</head>

<body>

<a href="logout">LOGOUT</a>

	<center>

		<div id="nav">
			<div id="nav_wrapper">
				<ul>
		
					<li><a href="#">EDIT</a></li>
					<li><a href="#" onclick='javascript:window.print()'>PRINT</a>
					</li>
					<li><a href="downloadExcel">EXPORT</a></li>

					<li><div id="tfheader">
							<form id="tfnewsearch" method="post" action="getList">
								<input type="text" class="tftextinput" name="q" size="13"
									maxlength="120"> <input type="submit" value=" "
									class="tfbutton">
							</form>
							<div class="tfclear"></div>
						</div></li>
				</ul>
			</div>
			<!-- Nav wrapper end -->
		</div>
		<!-- Nav end -->

		<div id="main_table">
			<table border="1">
				<tr>
					<td class="heading">DEVICE
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=1"></a> <a
								class="arrow-down" href="getList?sort=2"></a>
						</div>
					</td>
					<td class="heading">PLATFORM
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=3"></a> <a
								class="arrow-down" href="getList?sort=4"></a>
						</div>
					</td>
					<td class="heading">IMEI/SN
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=5"></a> <a
								class="arrow-down" href="getList?sort=6"></a>
						</div>
					</td>
					<td class="heading">STATUS
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=7"></a> <a
								class="arrow-down" href="getList?sort=8"></a>
						</div>
					</td>
					<td class="heading">PROJECT
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=9"></a> <a
								class="arrow-down" href="getList?sort=10"></a>
						</div>
					</td>
					<td class="heading">OWNER
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=11"></a> <a
								class="arrow-down" href="getList?sort=12"></a>
						</div>
					</td>
					<td class="heading">LAST MODIFIED
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=13"></a> <a
								class="arrow-down" href="getList?sort=14"></a>
						</div>
					</td>
					<td class="heading">DATE
						<div id="arrows">
							<a class="arrow-up" href="getList?sort=15"></a> <a
								class="arrow-down" href="getList?sort=16"></a>
						</div>
					</td>					
				</tr>
				<c:forEach var="device" items="${devicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platform}</td>
						<td>${device.imei}</td>
						<td>${device.status}</td>
						<td>${device.project}</td>
						<td>${device.owner}</td>
						<td>${device.last_modified}</td>
						<td>${device.date}</td>						
					</tr>
				</c:forEach>

			</table>

		</div>



	</center>

	

	<center>
	
	<br />
	<br />
	<a class="button" onclick="showRemovedDevices()">REMOVED DEVICES</a>
	<br />
	<br />
		<div id="removed_devices_table">
			<table border="1">

				<c:forEach var="device" items="${removedDevicesList}">
					<tr>
						<td>${device.device}</td>
						<td>${device.platform}</td>
						<td>${device.imei}</td>
						<td>${device.status}</td>
						<td>${device.project}</td>
						<td>${device.owner}</td>
						<td>${device.last_modified}</td>
						<td>${device.date}</td>
						<td></td>
					</tr>
				</c:forEach>

			</table>

		</div>

	</center>


	<script type="text/javascript">
		function openDialog(form) {
			var result = window.showModalDialog(
					"/proto-organizer-webapp/addDevice", form,
					"dialogWidth:400px; dialogHeight:400px; center:yes");
		}
	</script>
	
	<script type="text/javascript">
		function showRemovedDevices() {
			document.getElementById('removed_devices_table').style.display = "block";
		}
	</script>


	<form name="sample" action="#">

		<input type="hidden" value="Send to Dialog"
			onclick="openDialog(this.form)" />
	</form>
</body>
</html>
