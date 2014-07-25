<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-2"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/main.css'/>" />
<title>Ko¹ice Devices GlobalLogic</title>

</head>

<body>

	<center>

		<div id="nav">
			<div id="nav_wrapper">
				<ul>

					<li><a href="#">ADD</a>

						<ul>
							<li><a href="addDevice">NEW DEVICE</a></li>
							<li><a href="addProject">NEW PROJECT</a></li>
							<li><a href="addUser">NEW USER</a></li>
						</ul></li>
					<li><a href="#">REMOVE</a>

						<ul>
							<li><a href="deleteDevice">REMOVE DEVICE</a></li>
							<li><a href="deleteProject">REMOVE PROJECT</a></li>
							<li><a href="deleteUser">REMOVE USER</a></li>
						</ul></li>
					<li><a href="#">EDIT</a></li>
					<li><a href="#" onclick='javascript:window.print()'>PRINT</a>
					</li>
					<li><a href="downloadExcel">EXPORT</a></li>

					<li><div id="tfheader">
						<form id="tfnewsearch" method="post" action="getList">
							<input type="text" class="tftextinput" name="q" size="13"
								maxlength="120">
							<input type="submit" value="" class="tfbutton">
						</form>
						<div class="tfclear"></div>
					</div>
					</li>
				</ul>
			</div>
			<!-- Nav wrapper end -->
		</div>
		<!-- Nav end -->

		<div id="main_table">
			<table border="1">
				<tr>
					<td class="heading">DEVICE</td>
					<td class="heading">PLATFORM</td>
					<td class="heading">IMEI/SN</td>
					<td class="heading">STATUS</td>
					<td class="heading">PROJECT</td>
					<td class="heading">OWNER</td>
					<td class="heading">LAST MODIFIED</td>
					<td class="heading">DATE</td>
					<td class="heading">CHANGE OWNER</td>
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
						<td><a class="button" href="edit?id=${device.id}">MOVE TO</a></td>
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


	<form name="sample" action="#">

		<input type="hidden" value="Send to Dialog"
			onclick="openDialog(this.form)" />
	</form>
</body>
</html>
