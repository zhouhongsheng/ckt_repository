//query transitions
$('.query_transitions_btn').click(function(){
	$.ajax({
		url : '/api/transition/v1/transitions',
		type : 'GET', // POST
		async : false, // 或false,是否异步
		data : "zhouyou",
		headers:{'Content-Type':'application/json'},
		dataType : 'json',
		success : function(data) {
			$(".tran_select").html("<option value='0'>-choose-</option>");
			$.each(data,function(i,n){
				$(".tran_select").append("<option value='"+n.id+"'>"+n.tranName+"</option>");
			});
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});
//transition cancel button
$(".tran-cancel-btn").click(function(){
	$(".transition_div").hide();
});
//transition confirm button
$(".tran-confirm-btn").click(function(){
	var transitionName=$(".tran-name-input").val();
	if(transitionName==null||transitionName==""||transitionName==undefined){
		alert("please input transition name.");
		return false;
	}
	var tranArray = new Array();
	var pre_target_data_column = $(".pre-target-data-table tr");
	$.each(pre_target_data_column, function(i, n) {
		tranArray[i]=$(n).text();
	});
	if(tranArray.length<=0){
		alert("please choose origin-target database.");
		return false;
	}
	$.ajax({
		url : '/api/transition/v1/transition',
		type : 'PUT', // PUT
		async : true, // 或false,是否异步
		data : JSON.stringify({
			"tranName":$(".tran-name-input").val(),
			"userId":"9527",
			"operateType":$(".target-database-resources-select").val(),
			"preColumnName":$('.select-pre-table-select').val(),
			"targetColumnName":$('.select-target-table-select').val(),
			"preDataSourceDto" : {
				"type":1,
				"dataBaseDto":{
					"driverName" : $("#driverName").val(),
					"url" : $("#url").val(),
					"userName" : $("#userName").val(),
					"passWord" : $("#passWord").val(),
					"dataBaseType" : $("#dataBaseType").val()
				}
			},
			"targetDataSourceDto" : {
				"type":1,
				"dataBaseDto":{
					"driverName" : $("#driverNameTarget").val(),
					"url" : $("#urlTarget").val(),
					"userName" : $("#userNameTarget").val(),
					"passWord" : $("#passWordTarget").val(),
					"dataBaseType" : $("#dataBaseTypeTarget").val()
				}
			},
			"tranArray" : tranArray
		}),
		headers:{'Content-Type':'application/json'},
		success:function(data){
			$(".transition_div").hide();
			$('.query_transitions_btn').click();
			$(".tran_select").val(data);
		},
		error:function(data){
			alert(data.responseText);
		}
	});
});
//execute
$(".execute-tran-btn").click(function() {
	var tranArray = new Array();
	var pre_target_data_column = $(".pre-target-data-table tr");
	$.each(pre_target_data_column, function(i, n) {
		tranArray[i]=$(n).text();
	});
	if(tranArray.length<=0){
		alert("please choose origin-target database.");
		return false;
	}
	//是否选择转换
	var transitionId=$(".tran_select").val();
	if(!(transitionId!=null&&transitionId!=""&&transitionId!=undefined&&transitionId!=0)){
		alert("please create a transition .");
		$(".transition_div").show();
		return false;
	}
	$.ajax({
		url : '/api/transition/v1/transition',	
		type : 'POST', // POST
		async : true, // 或false,是否异步
		data : JSON.stringify({
			"preDataSource" : {
				"driverName" : $("#driverName").val(),
				"url" : $("#url").val(),
				"userName" : $("#userName").val(),
				"passWord" : $("#passWord").val(),
				"dataBaseType" : $("#dataBaseType").val()
			},
			"targetDataSource" : {
				"driverName" : $("#driverNameTarget").val(),
				"url" : $("#urlTarget").val(),
				"userName" : $("#userNameTarget").val(),
				"passWord" : $("#passWordTarget").val(),
				"dataBaseType" : $("#dataBaseTypeTarget").val()
			},
			"tranArray" : tranArray,
			"preTableName":$('.select-pre-table-select').val(),
			"targetTableName":$('.select-target-table-select').val(),
			"handleType":$('.target-database-resources-select').val()
		}),
		headers:{'Content-Type':'application/json'},
		success : function(data) {
			alert("execute transition success .");
		},
		error : function(data) {
			alert(data.responseText);
		}
	});
});