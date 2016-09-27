

$(function(){
	var d = document;
	var de = d.documentElement;

	setWindowSize();
	loadPageList();			//打开页面时自动加载列表
	
	
	$(window).resize(function(){
		setWindowSize();
	});
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clienHeight = de.clientHeight;
		$("#div_list").css("height",clienHeight);
	}


	

	

	

	
	function saveFindParams(){
		var pageNo = $("#pageNo");	//当前页
		var newCaption = $("#newCaption");
		var newKeyWord = $("#newKeyWord");
		var createTimeStart = $("#datepicker_start");
		var datepicker_end = $("#datepicker_end");
		
		find_params = "?pageNo=" + pageNo.html() + "&newCaption=" + newCaption.val() + "&newKeyWord=" + newKeyWord.val() +
			"&createTimeStart=" + createTimeStart.val() + "&datepicker_end=" + datepicker_end.val();
		return find_params; 
	}
	



});

