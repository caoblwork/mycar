jQuery(function() {
	
	$("#gender").editable({
		source: [
		    {value: 'MALE', text: '男'},
		    {value: 'FEMALE', text: '女'}
		],
		ajaxOptions: {
			dataType: "html"
		}
	});
	
	$("#nickname").editable({
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		}
	});

});