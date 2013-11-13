jQuery(function() {
	
	$(".help-inline").each(function() {
		var msg = $(this).html();
		if (msg != '') {
			$(this).addClass("error").parents(".control-group").addClass("error");
		}
	});
	
	var message = $("#message").text();
	if (message != '') {
		$("<div class='notifications top-right'></div>").css("z-index", 10000).appendTo("body").notify({message: {text: message}, type: 'success'}).show();
	}

	$("a[rel='captcha']").click(function(e) {
		$(this).find("img").attr("src", "images/captcha.jpeg?timestamp=" + new Date().getTime());
		return false;
	});
});