
/**
 * 生成的手机端程序访问的html需要的公共脚本

$(function(){
	var d = document;
	var de = d.documentElement;

	setImgsWidth();

	$(window).resize(function(){
		setImgsWidth();
	});

	function setImgsWidth(){
		var clientWidth= de.clientWidth;
		var imgs = $("img");

		imgs .each(function(){
			var imgWidth = $(this).width();
			
			if(imgWidth >=clientWidth){
				$(this).css("width",clientWidth-40);
			}	
		});
	}
});
 */