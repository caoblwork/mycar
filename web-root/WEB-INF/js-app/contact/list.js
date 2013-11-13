$(function() {
	
	var $addModal    = $("#addModal");
	var defaultEditMode = $("input[type='hidden'][name='defaultEditMode']").val();

	$("button[data-type='add']").click(function() {
		$addModal.find("input").val("");
		$addModal.find("textarea").val("");
		$addModal.find("*").removeClass("error");
		$addModal.find(".help-block").text('');
		$addModal.modal();
	});
	
	$("a[data-type='delete']").click(function() {
		var id = $(this).attr("data-id");
		var $tr = $(this).parents("tr");
		
		$.ajax({
			url: "contact/delete",
			type: "post",
			data: { id: id },
			dataType: "html",
			success: function(html) {
				if (html == 'SUCCESS') {
					$tr.remove();
				}
			}
		});
		
		return false;
	});
	
	$("button[data-type='switch']").click(function() {
		$('body .editable').editable('toggleDisabled');
		$.ajax({
			url: 'habit/contact/list/edit-mode',
			type: 'post'
		});
	});
	
	$("a[data-edit-type='editName']").editable({
		inputclass: 'input-medium',
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		},
		disabled: defaultEditMode == 'true'
	});

	$("a[data-edit-type='editPhoneNumbers']").editable({
		inputclass: 'input-medium',
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		},
		disabled: defaultEditMode == 'true'
	});

	$("a[data-edit-type='editAddress']").editable({
		inputclass: 'input-medium',
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	$("a[data-edit-type='editTitle']").editable({
		inputclass: 'input-medium',
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	$addModal.submit(function() {return false;});
	
	$addModal.find("button[name='submit']").click(function() {
		var name = $addModal.find("input[name='name']").val();
		var phoneNumbers = $addModal.find("input[name='phoneNumbers']").val();
		var address = $addModal.find("input[name='address']").val();
		var title = $addModal.find("input[name='title']").val();

		$.ajax({
			url: "contact/add",
			type: "post",
			data: {
				name: name,
				phoneNumbers: phoneNumbers,
				address: address,
				title: title
			},
			dataType: "json",
			success: function(json) {
				if (json.success == false) {
					var msg_name  = json.fieldErrors['name'];
					var msg_phone = json.fieldErrors['phoneNumbers'];
					var msg_address = json.fieldErrors['address'];
					var msg_title = json.fieldErrors['title'];
					
					$addModal.find("input[name='name']").next(".help-block").text(msg_name);
					$addModal.find("input[name='address']").next(".help-block").text(msg_address);
					$addModal.find("input[name='title']").next(".help-block").text(msg_title);
					$addModal.find("input[name='phoneNumbers']").next(".help-block").text(msg_phone);
					
					$addModal.find(".help-block").each(function() {
						var txt = $(this).text();
						if (txt != '') {
							$(this).addClass("error").parents(".control-group").addClass("error");
						}
					});
				}
				else {
					window.location.reload();
				}
			} 
		});
	});
});