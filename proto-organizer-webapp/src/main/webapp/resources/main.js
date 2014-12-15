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
					$(".showEnabled.ui-icon").css("display", "inline-block");
					$(".hideDisabled").css("color", "gray");
				}
		});
	var oTable = $('#main_table').dataTable(
			{
				"paging": false,
				"ordering": true,
				"info" : false
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

	$().toastmessage({sticky : true, stayTime: 5000});
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
		