
$(function(){
	var d = document;
	var de = d.documentElement;
	setWindowSize();
	
	$(window).resize(function(){
		setWindowSize();
	});

	
	/**
	 * 新建
	 */
	$("#btn_add_news").click(function(){
		//$("#ifm_news").attr("src",path + "/page/news/add/news_add.jsp");
		$('#ifm', window.parent.document).attr("src",path + "/page/news/add/news_add.jsp");
	});
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clienHeight = de.clientHeight;
		$("#div_list").css("height",clienHeight);
	}

});