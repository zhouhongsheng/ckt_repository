$(".select-target-table-btn").click(
	function() {
		$.ajax({
			url : '/api/transition/v1/dataBase/tableNames',
			type : 'GET', // GET
			async : true, // 或false,是否异步
			data : {
				"driverName" : $("#driverNameTarget").val(),
				"url" : $("#urlTarget").val(),
				"userName" : $("#userNameTarget").val(),
				"passWord" : $("#passWordTarget").val(),
				"dataBaseType" : $("#dataBaseTypeTarget").val()
			},
			dataType : 'json',
			success : function(data) {
				$(".select-target-table-select").html("");
				$.each(data, function(i, n) {
					$(".select-target-table-select").append(
							"<option>" + n + "</option>");
				});
			},
			error : function(data) {
				alert(data.responseText);
			}
		});
	});
$(".select-target-table-select").change(function() {
	$.ajax({
		url : '/api/transition/v1/dataBase/columnNames',
		type : 'GET', // GET
		async : true, // 或false,是否异步
		data : {
			"tableName" : $(this).val(),
			"driverName" : $("#driverNameTarget").val(),
			"url" : $("#urlTarget").val(),
			"userName" : $("#userNameTarget").val(),
			"passWord" : $("#passWordTarget").val(),
			"dataBaseType" : $("#dataBaseTypeTarget").val()
		},
		success : function(data) {
			$(".target-data-table").html("");
			$.each(data, function(i, n) {
				$(".target-data-table").append("<tr><td class='targetcolumnname'>" + n + "</td></tr>");
			});
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});
$(".targetcolumnname").live("click",function(){
	$(".target-data-table td").removeClass("selected").addClass("unselected");
	$(this).removeClass("unselected").addClass("selected");
});
