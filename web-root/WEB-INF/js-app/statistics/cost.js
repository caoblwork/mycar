function initGasInfoPanel() {
	var carId = $("#carId").val();
	
	$.ajax({
		url: "statistics/ajax/gas-info",
		type: "post",
		data: { carId: carId },
		dataType: "html",
		success: function(html) {
			setTimeout(function() {
				$("#gas-info-panel").html(html);
			}, 300);
		},
		beforeSend: function() {
			$("#gas-info-panel").html("<img src='images/loading.gif' />");
		}
	});
}

function initCostDetailsPanel() {
	var carId     = $("#carId").val();
	var daterange = $("input[type='hidden'][name='daterange']").val();
	
	$.ajax({
		url: "statistics/ajax/cost-pie",
		type: "post",
		data: {
			carId: carId,
			daterange: daterange
		},
		dataType: "json",
		success: function(json) {
			 $('#cost-pie-container').highcharts(json);
		}
	});
}

jQuery(function() {
	
	initGasInfoPanel();
	initCostDetailsPanel();
	
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
			initCostDetailsPanel();
		}
	);

	$("#carId").change(function() {
		initGasInfoPanel();
	});

	$('#tab a').click(function (e) {
		e.preventDefault();
		var index = $(this).parent().index(); // index
		$.ajax({
			url: "habit/statistics/cost/last-tab",
			type: "POST",
			data: {index: index}
		});
		$(this).tab('show');
	});
	
	$("#tab a:eq(0)").click(function() {
		$("input[type='hidden'][name='daterange']").val("1970-01-01|2099-12-31");
		$("#daterange").val("1970/01/01 - 2099/12/31").parent().hide();
	});
	
	$("#tab a:not(:eq(0))").click(function() {
		$("#daterange").val("1970/01/01 - 2099/12/31").parent().show();
	});
	
	var index = $("input[name='lastTabIndex']").val();
	$("#tab a:eq(" + index +")").click();

});