



$.utils = {};

$(function(){

	
	
	$.utils.sendData = function(url,paramObj,callBack){
		alert();
		$.ajax({
			type: "POST",
			url: url,
			data: paramObj,
			contentType: "application/json;charset=utf-8",
			success: function(data){
				callBack(data);
			},
			dataType: 'json'
		});
	};
	
	
	$.utils.test = function(msg){
		alert(msg);
	};

	
	
	
	

});