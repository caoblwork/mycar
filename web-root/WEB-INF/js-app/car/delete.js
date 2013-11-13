jQuery(function() {

	$(".help-inline").each(function() {
		var msg = $(this).html();
		if (msg != '') {
			$(this).addClass("error").parents(".control-group").addClass("error");
		}
	});

});