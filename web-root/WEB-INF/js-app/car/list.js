jQuery(function() {
	
	var $addModal    = $("#addModal");
	var defaultEditMode = $("input[name='defaultEditMode']").val();
	
	$("button[data-type='add']").click(function() {
		$addModal.find("input").val("");
		$addModal.find("*").removeClass("error");
		$addModal.find(".help-block").text('');
		$addModal.find("select[name='type']").val('M');
		$addModal.modal();
	});

	// 编辑里程
	$("a[data-edit-type='editMileage']").editable({
		inputclass: 'input-medium',
		emptytext: '<空>',
		ajaxOptions: {
			dataType: "html"
		},
		disabled: defaultEditMode == 'true'
	});

	// 编辑名称
	$("a[data-edit-type='editName']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});

	// 编辑品牌
	$("a[data-edit-type='editBrand']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	// 编辑型号
	$("a[data-edit-type='editModelType']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	// 编辑牌照号
	$("a[data-edit-type='editLicense']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	// 编辑钢架号
	$("a[data-edit-type='editNumberOfFrame']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});
	
	// 编辑钢架号
	$("a[data-edit-type='editNumberOfEngine']").editable({
		inputclass : 'input-medium',
		emptytext : '<空>',
		ajaxOptions : {
			dataType : "html"
		},
		disabled: defaultEditMode == 'true'
	});

	// 编辑类型
	$("a[data-edit-type='editType']").editable({
		source : [
		    {value: 'M', text: "M"},
		    {value: 'N', text: "N"},
		    {value: 'O', text: "O"},
		    {value: 'X', text: "X"}
		],
		disabled: defaultEditMode == 'true'
	});
	
	// 添加车辆表单禁止提交
	$addModal.submit(function() {return false;});
	
	$addModal.find("button[name='submit']").click(function() {
		var name = $addModal.find("input[name='name']").val();
		var type = $addModal.find("select[name='type']").val();
		var brand = $addModal.find("input[name='brand']").val();
		var modelType = $addModal.find("input[name='modelType']").val();
		var license = $addModal.find("input[name='license']").val();
		var numberOfFrame = $addModal.find("input[name='numberOfFrame']").val();
		var numberOfEngine = $addModal.find("input[name='numberOfEngine']").val();

		$.ajax({
			url: "car/add-car",
			type: "post",
			data: {
				name: name,
				type: type,
				brand: brand,
				modelType: modelType,
				license: license,
				numberOfFrame: numberOfFrame,
				numberOfEngine: numberOfEngine
			},
			dataType: "json",
			success: function(json) {
				if (json.success == false) {
					var msg_name      = json.fieldErrors['name'];
					var msg_brand     = json.fieldErrors['brand'];
					var msg_modelType = json.fieldErrors['modelType'];
					var msg_license   = json.fieldErrors['license'];
					var msg_numberOfFrame  = json.fieldErrors['numberOfFrame'];
					var msg_numberOfEngine = json.fieldErrors['numberOfEngine'];
					
					$addModal.find("input[name='name']").next(".help-block").text(msg_name);
					$addModal.find("input[name='brand']").next(".help-block").text(msg_brand);
					$addModal.find("input[name='modelType']").next(".help-block").text(msg_modelType);
					$addModal.find("input[name='license']").next(".help-block").text(msg_license);
					$addModal.find("input[name='numberOfFrame']").next(".help-block").text(msg_numberOfFrame);
					$addModal.find("input[name='numberOfEngine']").next(".help-block").text(msg_numberOfEngine);
					
					$addModal.find(".help-block").each(function() {
						var txt = $(this).text();
						if (txt != '') {
							$(this).addClass("error").parents(".control-group").addClass("error");
						}
					});
				} else {
					$addModal.modal("hide");
					window.location = window.location;
				}
			}
		});
	});
	
	$("button[data-type='switch']").click(function() {
		$('body .editable').editable('toggleDisabled');
		$.ajax({
			url: 'habit/car/list/edit-mode',
			type: 'post'
		});
	});

	if ($("input[type='hidden'][name='error']").val() == 'not-car-owner') {
		$addModal.modal();
	}
});