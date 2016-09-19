



$.utils = {};

$(function(){

	
	
	$.utils.sendData = function(url,paramObj,callBack){
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
	


	
	
	
	

});