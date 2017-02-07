$(".select-target-table-btn").click(
	function() {
		$.ajax({
			url : '/api/transition/v1/targetTableNames',
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
		url : '/api/transition/v1/targetColumnNames',
		type : 'POST', // GET
		async : true, // 或false,是否异步
		data :  JSON.stringify({
			"tableName" : $(this).val(),
			"dataBase":{
				"driverName" : $("#driverNameTarget").val(),
				"url" : $("#urlTarget").val(),
				"userName" : $("#userNameTarget").val(),
				"passWord" : $("#passWordTarget").val(),
				"dataBaseType" : $("#dataBaseTypeTarget").val()
			}
		}),
		headers:{'Content-Type':'application/json'},
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
