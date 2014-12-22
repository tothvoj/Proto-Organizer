$(document).ready(function() {
	//$(document).tooltip();
	
	function init() {
		$(".dialog").click(function(e) {
			e.preventDefault();
			$("#dialog").load($(this).attr("href"), 
				function()
				{
					if( $("#dialog").text().indexOf('Login') >= 0 )	{
						window.location.replace("login");
					}
					else 	{
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
					}
				});
			
			return false;
		});
		
		$(".flaticon:checkbox").change(function() {
			if( $(this).hasClass("offsite") ) {
				if(this.checked) {
					changeDeviceStatus($(this).attr("deviceid"), $("#userId").val(), "taken-offsite");
					$(this).siblings("input").removeAttr("checked");
				}
				else {
					changeDeviceStatus($(this).attr("deviceid"), $("#userId").val(), "returned");
				}
			}
			else if ( $(this).hasClass("home") ) {
				if(this.checked) {
					changeDeviceStatus($(this).attr("deviceid"), $("#userId").val(), "taken-home");
					$(this).siblings("input").removeAttr("checked");
				}
				else {
					changeDeviceStatus($(this).attr("deviceid"), $("#userId").val(), "returned");
				}
				
			}
		});
	}
	
	
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
					$(".showEnabled.ui-icon").css("display", "inline-block");
					$(".hideDisabled").css("color", "gray");
				}
		});
	var oTable = $('#main_table').on( 'order.dt',  function () { init(); } ).dataTable(
			{
				"paging": false,
				"ordering": true,
				"info" : false,
				"bAutoWidth" : false
			});
	oTable.fnSearchHighlighting();
	
	$("input.tftextinput").on( 'keyup click', function () {
		var keywords = $(this).val().split(' '), filter ='';
	       for (var i=0; i<keywords.length; i++) {
	    	   if(keywords[i] != '') {
	           		filter = (filter!='') ? filter+'|'+keywords[i] : keywords[i];
	    	   }
	       }  
		
		oTable.fnFilter(filter, null, true, false, true, true);
    });

	$().toastmessage({sticky : false, stayTime: 5000});
	init();
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

function deleteSingleDevice()
{
	$.ajax({
		  type: "POST",
		  url: "deleteSingleDeviceFromDB",
		  data: $("#deleteSingleDeviceInput").val(),
		  success: function(msg){
			  $("#dialog").dialog("close");
			  $('#main_table').load('getListAdmin #main_table');
			  $().toastmessage('showSuccessToast', "Delete successful");
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
			  $("#dialog").dialog("close");
			  $().toastmessage('showErrorToast', "Delete failed");
		  }
		});
};

function changeDeviceStatus(deviceId, userId, newStatus)
{
	$.ajax({
		  type: "POST",
		  url: "changeDeviceStatus",
		  data: "deviceId=" + deviceId + "&userId=" + userId + "&newStatus=" + newStatus,
		  success: function(msg){
			  /* $('#main_table').load('getListAdmin #main_table'); 
			  $().toastmessage('showSuccessToast', "Delete successful"); */
			  showToastMessage(msg);
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
			  /* $().toastmessage('showErrorToast', "Change failed"); */
			  showToastMessage(msg);
		  }
		});
};

function showToastMessage(msg) {
	var toastType = 'showSuccessToast';
	
	if(msg.indexOf("O:") || msg.indexOf("o:"))	{
		toastType = 'showSuccessToast';
	}
	else if(msg.indexOf("I:") || msg.indexOf("i:"))	{
		toastType = 'showNoticeToast';
	}
	else if(msg.indexOf("E:") || msg.indexOf("e:"))	{
		toastType = 'showErrorToast';
	}
	else if(msg.indexOf("W:") || msg.indexOf("w:"))	{
		toastType = 'showWarningToast';
	}
	
	$().toastmessage(toastType, msg.substring(2, msg.length));
}
		