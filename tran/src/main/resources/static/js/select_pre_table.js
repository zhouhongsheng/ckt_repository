$(".select-pre-table-btn").click(
		function() {
			$.ajax({
				url : '/api/pretran/query-pre-table-list',
				type : 'GET', // GET
				async : true, // 或false,是否异步
				data : {
					"driverName" : $("#driverName").val(),
					"url" : $("#url").val(),
					"userName" : $("#userName").val(),
					"passWord" : $("#passWord").val(),
					"dataBaseType" : $("#dataBaseType").val()
				},
				dataType : 'json',
				success : function(data) {
					$(".select-pre-table-select").html("");
					$.each(data, function(i, n) {
						$(".select-pre-table-select").append(
								"<option>" + n + "</option>");
					});
				},
				error : function(data) {
					alert(data.responseText);
				}
			});
		});
$(".select-pre-table-select").change(function() {
	$.ajax({
		url : '/api/pretran/query-pre-columnnames',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data : JSON.stringify({
			"tableName" : $(this).val(),
			"dataBase":{
				"driverName" : $("#driverName").val(),
				"url" : $("#url").val(),
				"userName" : $("#userName").val(),
				"passWord" : $("#passWord").val(),
				"dataBaseType" : $("#dataBaseType").val()
			}
		}),
		headers:{'Content-Type':'application/json'},
		success : function(data) {
			$(".pre-data-table").html("");
			$.each(data, function(i, n) {
				$(".pre-data-table").append("<tr><td class='precolumnname'>" + n + "</td></tr>");
			});
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});
$(".precolumnname").live("click",function(){
	$(".pre-data-table td").removeClass("selected").addClass("unselected");
	$(this).removeClass("unselected").addClass("selected");
});
