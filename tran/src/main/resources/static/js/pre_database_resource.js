// open pre-database-resources div
$(".pre-database-resources-btn").click(function() {
	$(".pre-database-resources-div").show();
});
// cancel pre-database-resource div
$(".cancel-pre-database-resources").click(function() {
	$(".pre-database-resources-div").hide();
});
// test pre-database-resources
$(".test-pre-database-resources").click(function() {
	var driverName = $("#driverName").val();
	var schemaName = $("#schemaName").val();
	var url = $("#url").val();
	var port = $("#port").val();
	var userName = $("#userName").val();
	var passWord = $("#passWord").val();
	$.ajax({
		url : '/api/transition/v1/preDataBase',
		type : 'GET', // GET
		async : true, // 或false,是否异步
		data : {
			"driverName" : driverName,
			"schemaName" : schemaName,
			"url" : url,
			"port" : port,
			"userName" : userName,
			"passWord" : passWord
		},
		dataType : 'json',
		success : function(data) {
			alert("database connect success.");
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});
// confirm pre-database-source
$(".confirm-pre-database-resources").click(function() {
	var driverName = $("#driverName").val();
	var schemaName = $("#schemaName").val();
	var url = $("#url").val();
	var port = $("#port").val();
	var userName = $("#userName").val();
	var passWord = $("#passWord").val();
	$.ajax({
		url : '/api/transition/v1/preDataBase',
		type : 'POST', // POST
		async : true, // 或false,是否异步
		data : {
			"driverName" : driverName,
			"schemaName" : schemaName,
			"url" : url,
			"port" : port,
			"userName" : userName,
			"passWord" : passWord
		},
		dataType : 'json',
		success : function(data) {
			if (data != null) {
				$(".pre-database-resources-div").hide();
				$("#driverName").val(data.driverName);
				$("#schemaName").val();
				$("#url").val(data.url);
				$("#port").val();
				$("#userName").val(data.userName);
				$("#passWord").val(data.passWord);
			}
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});
