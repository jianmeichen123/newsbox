



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
	
	$.utils.getRandInt = function(max,min){
		return Math.floor(Math.random() * (max - min + 1) + min);
	};
	
	$.utils.trim = function trim(str) {
		return str.replace(/(^\s+)|(\s+$)/g, "");
	};
	
	
	
	

});