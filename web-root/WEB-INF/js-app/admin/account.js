jQuery(function() {
	
	$("table a[data-type='evit']").click(function(e) {
		e.preventDefault();
		var id = $(this).attr("data-id");
		var $tr = $(this).parents("tr");
		
		$.ajax({
			url: "admin/evit",
			type: "post",
			data: {
				userId: id
			},
			dataType: "html",
			success: function(html) {
				$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '操作成功'}, type: 'bangTidy'}).show();
				$tr.remove();
			}
		});
	});
});