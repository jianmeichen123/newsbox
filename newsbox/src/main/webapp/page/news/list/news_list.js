

$(function(){
	var d = document;
	var de = d.documentElement;

	setWindowSize();
	
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
	
	laydate({
	   elem: '#datepicker_start'
	});
	
	laydate({
	   elem: '#datepicker_end'
	});

	
});