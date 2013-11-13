function replaceAll(find, replace, str) 
    {
      while( str.indexOf(find) > -1)
      {
        str = str.replace(find, replace);
      }
      return str;
    }

function refresh($panel) {
	var pageNumber = $panel.attr("data-page-number");
	var carId      = $("#carId").val();
	var dateRange  = $("input[type='hidden'][name='daterange']").val();
	var tabType    = $panel.attr("data-type").toLowerCase();
	var pageSize   = $("input[type='hidden'][name='pageSize']").val();
	var pageSort   = $("input[type='hidden'][name='pageSort']").val();
	var pageSortDir= $("input[type='hidden'][name='pageSortDir']").val();
	
	if (tabType == 'add') return;
	if (tabType == 'setting') return;

	$.ajax({
		url: 'cost/ajax/' + tabType,
		type: 'post',
		data: {
			"page.page": pageNumber,
			"page.size": pageSize,
			"page.sort": pageSort,
			"page.sort.dir": pageSortDir,
			carId: carId,
			dateRange: dateRange
		},
		dataType: 'html',
		success: function(html) {
			setTimeout(function(){
				$panel.html(html);
				$panel.find("[rel='tooltip']").tooltip();
			}, 100);
		},
		beforeSend: function() {
			$panel.html("<img src='images/loading.gif' />");
		}
	});
}

function initAddPanel() {
	var $panel = $(".tab-pane[data-type='ADD']");
	$panel.find("input,textarea").filter(":not([name='date'])").val("");
	$panel.find("select").filter(":not([name='costType'])").each(function() {
		var value = $(this).find("option:first").val();
		$(this).val(value);
	});
	var costType = $panel.find("select[name='costType']").val();

	$("select[name='paymentType'] option").each(function() {
		if ($(this).val() == $("input[name='mostPaymentType']").val()) {
			$(this).attr("selected", "selected");
		}
	});
	$("select[name='paymentType']").val($("input[name='mostPaymentType']").val());

	$("select[name='gasType'] option").each(function() {
		if ($(this).val() == $("input[name='mostGasType']").val()) {
			$(this).attr("selected", "selected");
		}
	});
	$("select[name='gasType']").val($("input[name='mostGasType']").val());
	$panel.find("[data-type]").hide();
	$panel.find("[data-type='" + costType + "']").show();
	$panel.find(".help-inline, .help-block").html("");
	$panel.find("*").removeClass("error");
}

function initSettingPanel() {
//	alert('初始化一下');
}


jQuery(function() {

	var year = new Date().getFullYear();
	$("#daterange").daterangepicker({
			ranges: {
				'所有': ['1970/01/01', '2099/12/31'],
				'本日': ['today', 'today'],
				'本月': [Date.today().moveToFirstDayOfMonth(), Date.today().moveToLastDayOfMonth()],
				'今年': [year + '/01/01', year + '/12/31'],
				'去年': [year-1 + '/01/01', year-1 + '/12/31'],
			},
			startDate: $("#daterange").val().split(" - ")[0],
			endDate:   $("#daterange").val().split(" - ")[1]
		}, function(startDate, endDate) {
			$("input[type='hidden'][name='daterange']").val(startDate.toString("yyyy-MM-dd") + "|" + endDate.toString("yyyy-MM-dd"));
			
			var $activePanel  = $(".tab-pane").filter(".active");
			$activePanel.attr("data-page-number", 1);
			refresh($activePanel);
		}
	);

	$('#tab a').click(function (e) {
		e.preventDefault();
		if ($(this).attr("href") == "#add") {
			initAddPanel();
		}
		else if ($(this).attr("href") == "#setting") {
			initSettingPanel();
		} 
		
		// 通用
		var index = $(this).parent().index();
		$.ajax({
			url: "habit/cost/list/last-tab",
			type: "post",
			data: {index: index}
		});
		$(this).tab('show');
	});

	$("#tab a").on('shown', function(e) {
		var $activePanel  = $(".tab-pane").filter(".active");
		refresh($activePanel);
	});
	
	$("body").delegate(".pagination a[data-page-number]", "click", function(e) {
		e.preventDefault();
		var pageNumber = $(this).attr("data-page-number");
		
		var $activePanel  = $(".tab-pane").filter(".active");
		$activePanel.attr("data-page-number", pageNumber);
		refresh($activePanel);
	});
	
	$("body").delegate("table a[data-function='delete']", "click", function(e) {
		e.preventDefault();
		var id = $(this).attr("data-id");
		
		$.ajax({
			url:  "cost/ajax/delete",
			type: "post",
			data: {
				id: id
			},
			dataType: "json",
			success: function() {
				var $activePanel  = $(".tab-pane").filter(".active");
				$activePanel.attr("data-page-number", 1);
				refresh($activePanel);
			}
		});
	});
	
	$("#carId").change(function(e) {
		var $activePanel  = $(".tab-pane").filter(".active");
		$activePanel.attr("data-page-number", 1);
		refresh($activePanel);
	});
	
	$(".tab-pane[data-type='ADD'] select[name='costType']").change(function() {
		initAddPanel();
	});

	$(".tab-pane[data-type='ADD'] input[name='date']").datepicker();

	var index = $("input[name='lastTabIndex']").val();
	$("#tab a:eq(" + index +")").click();
	
	$("#reset").click(function(e) {
		e.preventDefault();
		initAddPanel();
	});
	
	$("#settingSubmit").click(function(e) {
		e.preventDefault();
		var pageSize    = $("select[name='pageSize']").val();
		var pageSort    = "date";
		var pageSortDir = $("select[name='pageSort']").val();
		
		$.ajax({
			url: "habit/statistics/cost-list/page-info",
			type: "post",
			data: {
				pageSize: pageSize,
				pageSort: pageSort,
				pageSortDir: pageSortDir
			},
			dataType: "html",
			success: function(html) {
				if (html == "SUCCESS") {
					$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '设置成功'}, type: 'bangTidy'}).show();
					$("input[type='hidden'][name='pageSize']").val(pageSize);
					$("input[type='hidden'][name='pageSort']").val(pageSort);
					$("input[type='hidden'][name='pageSortDir']").val(pageSortDir);
					
					$("div.tab-pane").each(function() {
						if ($(this).is("[data-page-number]")) {
							$(this).attr("data-page-number", "1");
						}
					});
				}
			}
		});
	});

	$("#addSubmit").click(function(e) {
		e.preventDefault();
		var costType = $(".tab-pane[data-type='ADD'] select[name='costType']").val();
		var url  = null;
		var date = $("input[name='date']").val();
		var sum  = $("input[name='sum']").val();
		var paymentType = $("select[name='paymentType']").val();
		var comment = $("textarea").val();
		var carId = $("select[name='carId']").val();
		
		if (costType == "GAS") {
			var gasType = $("select[name='gasType']").val();
			var price = $("input[name='price']").val();
			var cubage = $("input[name='cubage']").val();
			var fillingType = $("select[name='fillingType']").val();

			$.ajax({
				url: "gas-cost/add",
				type: "post",
				data: {
					date: date,
					sum: sum,
					paymentType: paymentType,
					comment: comment,
					carId: carId,
					gasType: gasType,
					price: price,
					cubage: cubage,
					fillingType: fillingType
				},
				dataType: "json",
				success: function(json) {
					if (json.success == false) {
						var msgSum = json.fieldErrors['sum'];
						var msgComment = json.fieldErrors['comment'];
						var msgPrice = json.fieldErrors['price'];
						var msgCubage = json.fieldErrors['cubage'];
						$("input[name='sum']").next(".help-inline").html(msgSum);
						$("textarea[name='comment']").next(".help-inline").html(msgComment);
						$("input[name='price']").next(".help-inline").html(msgPrice);
						$("input[name='cubage']").next(".help-inline").html(msgCubage);
						$(".help-inline").each(function() {
							var text = $(this).html();
							if (text != "") {
								$(this).addClass("error").parents(".control-group").addClass("error");
							}
						});
					} else {
						$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '新建成功'}, type: 'bangTidy'}).show();
						initAddPanel();
					}
				}
			});
		}
		
		if (costType == "PARKING") {
			$.ajax({
				url: "parking-cost/add",
				type: "post",
				data: {
					date: date,
					sum: sum,
					paymentType: paymentType,
					comment: comment,
					carId: carId
				},
				dataType: "json",
				success: function(json) {
					if (json.success == false) {
						var msgSum = json.fieldErrors['sum'];
						var msgComment = json.fieldErrors['comment'];
						$("input[name='sum']").next(".help-inline").html(msgSum);
						$("textarea[name='comment']").next(".help-inline").html(msgComment);
						$(".help-inline").each(function() {
							var text = $(this).html();
							if (text != "") {
								$(this).addClass("error").parents(".control-group").addClass("error");
							}
						});
					} else {
						$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '新建成功'}, type: 'bangTidy'}).show();
						initAddPanel();
					}
				}
			});
		}

		if (costType == "WASHING") {
			$.ajax({
				url: "washing-cost/add",
				type: "post",
				data: {
					date: date,
					sum: sum,
					paymentType: paymentType,
					comment: comment,
					carId: carId
				},
				dataType: "json",
				success: function(json) {
					if (json.success == false) {
						var msgSum = json.fieldErrors['sum'];
						var msgComment = json.fieldErrors['comment'];
						$("input[name='sum']").next(".help-inline").html(msgSum);
						$("textarea[name='comment']").next(".help-inline").html(msgComment);
						$(".help-inline").each(function() {
							var text = $(this).html();
							if (text != "") {
								$(this).addClass("error").parents(".control-group").addClass("error");
							}
						});
					} else {
						$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '新建成功'}, type: 'bangTidy'}).show();
						initAddPanel();
					}
				}
			});
		}

		if (costType == "TOLL") {
			var mileage = $("input[name='mileage']").val();
			$.ajax({
				url: "toll-cost/add",
				type: "post",
				data: {
					date: date,
					sum: sum,
					paymentType: paymentType,
					comment: comment,
					carId: carId,
					mileage: mileage
				},
				dataType: "json",
				success: function(json) {
					if (json.success == false) {
						var msgSum = json.fieldErrors['sum'];
						var msgComment = json.fieldErrors['comment'];
						var msgMileage = json.fieldErrors['mileage'];
						$("input[name='sum']").next(".help-inline").html(msgSum);
						$("textarea[name='comment']").next(".help-inline").html(msgComment);
						$("input[name='mileage']").next(".help-inline").html(msgMileage);
						$(".help-inline").each(function() {
							var text = $(this).html();
							if (text != "") {
								$(this).addClass("error").parents(".control-group").addClass("error");
							}
						});
					} else {
						$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '新建成功'}, type: 'bangTidy'}).show();
						initAddPanel();
					}
				}
			});
		}
		
		if (costType == "FINE") {
			var point = $("select[name='point']").val();
			$.ajax({
				url: "fine-cost/add",
				type: "post",
				data: {
					date: date,
					sum: sum,
					paymentType: paymentType,
					comment: comment,
					carId: carId,
					point: point
				},
				dataType: "json",
				success: function(json) {
					if (json.success == false) {
						var msgSum = json.fieldErrors['sum'];
						var msgComment = json.fieldErrors['comment'];
						var msgMileage = json.fieldErrors['mileage'];
						$("input[name='sum']").next(".help-inline").html(msgSum);
						$("textarea[name='comment']").next(".help-inline").html(msgComment);
						$(".help-inline").each(function() {
							var text = $(this).html();
							if (text != "") {
								$(this).addClass("error").parents(".control-group").addClass("error");
							}
						});
					} else {
						$("<div class='notifications bottom-right'></div>").appendTo("body").notify({message: {text: '新建成功'}, type: 'bangTidy'}).show();
						initAddPanel();
					}
				}
			});
		}
	});
	
	$("#download-excel").click(function() {
		var carId = $("#carId").val();
		var datestr = $("[type='hidden'][name='daterange']").val();
		datestr = replaceAll("-", "", datestr);
		datestr = replaceAll("|", "/", datestr);
		var url = $("base").attr("href") + "cost/car/" + carId + "/" + datestr + "/COST.xls";
		window.open(url, "_blank");
		return false;
	});

});