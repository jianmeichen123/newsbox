
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
		var clientWidth = de.clientWidth;
		
		var div_top_height = 76;
		var div_left_width = 200;

		$("#div_content").css("height",clienHeight-div_top_height);				//设置功能区及左侧菜单区的高度
		$("#div_content .div_right").css("width",clientWidth);	//设置功能区的宽度
	}
	
	/**
	 * 点击菜单项的操作（改变样式并进入相关页面）
	 */
	$("#div_top .menu ul li").click(function(){
		//alert($(this).attr("value"));
		$(this).css("color","#34acdb");
		$(this).siblings().css("color","#cdced1");
		
		if($(this).attr("value")=='gaojianku'){
			$("#ifm").attr("src",path + "/page/news/news_main.jsp");
		}
	});
	

	
	
	
	
});



