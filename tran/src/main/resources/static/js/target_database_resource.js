$(".target-database-resources-btn").click(function(){
	$(".target-database-resources-div").show();
});
$(".cancel-target-database-resources").click(function(){
	$(".target-database-resources-div").hide();
});
//test 
$(".test-target-database-resources").click(function(){
	var driverName = $("#driverNameTarget").val();
	var schemaName = $("#schemaNameTarget").val();
	var url = $("#urlTarget").val();
	var port = $("#portTarget").val();
	var userName = $("#userNameTarget").val();
	var passWord = $("#passWordTarget").val();
	$.ajax({
		url : '/api/transition/v1/targetDataBase',
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
//confirm
$(".confirm-target-database-resources").click(function() {
	var driverName = $("#driverNameTarget").val();
	var schemaName = $("#schemaNameTarget").val();
	var url = $("#urlTarget").val();
	var port = $("#portTarget").val();
	var userName = $("#userNameTarget").val();
	var passWord = $("#passWordTarget").val();
	$.ajax({
		url : '/api/transition/v1/targetDataBase',
		type : 'POST', // GET
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
				$(".target-database-resources-div").hide();
				$("#driverNameTarget").val(data.driverName);
				$("#schemaNameTarget").val();
				$("#urlTarget").val(data.url);
				$("#portTarget").val();
				$("#userNameTarget").val(data.userName);
				$("#passWordTarget").val(data.passWord);
			}
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});