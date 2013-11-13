jQuery(function() {
	
	$(".help-inline").each(function() {
		var msg = $(this).html();
		if (msg != '') {
			$(this).addClass("error").parents(".control-group").addClass("error");
		}
	});

	var globalErrorMessages = $("#gobalErrorMessage").text();
	
	if (globalErrorMessages != "") {
		$("<div class='notifications top-right'></div>").appendTo("body").notify({message: {text: globalErrorMessages}, type: 'error'}).show();
	}
});