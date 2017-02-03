$(".move_to_right_btn").click(function(){
	var pre_data_column=$(".pre-data-table .selected");
	if(pre_data_column.length!=1){
		alert("please choose origin database column.");
		return false;
	}
	var target_data_column=$(".target-data-table .selected");
	if(target_data_column.length!=1){
		alert("please choose target database column.");
		return false;
	}
	var predata=$(pre_data_column[0]).text();
	var targetdata=$(target_data_column[0]).text();
	
	$(".pre-target-data-table td").removeClass("selected").addClass("unselected");
	$(".pre-target-data-table").append("<tr><td class='pretargetcolumnname selected'>"+predata+"="+targetdata+"</td></tr>");
	$(pre_data_column[0]).remove();
	$(target_data_column[0]).remove();
});
$(".pretargetcolumnname").live("click",function(){
	$(".pre-target-data-table td").removeClass("selected").addClass("unselected");
	$(this).addClass("selected");
});
$(".move_to_left_btn").click(function(){
	var pre_target_data_column=$(".pre-target-data-table .selected");
	if(pre_target_data_column.length!=1){
		alert("please choose matched origin-target column.");
		return false;
	}
	var pretargetdata=$(pre_target_data_column[0]).text();
	var pretargetdataarray=pretargetdata.split("=");
	console.log(pretargetdataarray);
	if(pretargetdataarray.length!=2){
		alert("origin-target data is error.");
		return false;
	}
	$(".pre-data-table td").removeClass("selected").addClass("unselected");
	$(".pre-data-table").append("<tr><td class='precolumnname selected'>" + pretargetdataarray[0] + "</td></tr>");
	
	$(".target-data-table td").removeClass("selected").addClass("unselected");
	$(".target-data-table").append("<tr><td class='targetcolumnname selected'>" + pretargetdataarray[1] + "</td></tr>");
	//remove
	$(pre_target_data_column[0]).remove();
});