jQuery(function() {
	
	$("td[data-type='level']").each(function() {
		var text = $(this).html();
		if (text == "WARN") {
			$(this).parents("tr").addClass("warning");
		}
		
		if (text == "ERROR") {
			$(this).parents("tr").addClass("error");
		}
	});
});
