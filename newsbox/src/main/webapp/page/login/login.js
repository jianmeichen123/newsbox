
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
		$(".div_main").css("height",clienHeight);		//设置功能区及左侧菜单区的高度
		$(".div_login_img").css("margin-top",(clienHeight-459)/2);
	}
	
	/**
	 * 登录
	 */
	$("#btn_add_news").click(function(){
		var userName = $("#userName").val();
		var userPasswd = $("#userPasswd").val();
		
		if($.utils.trim(userName).length<=0){
			alert("请输入用户名！");
			return;
		}else if($.utils.trim(userPasswd).length<=0){
			alert("密码为空或都为空格！");
			return;
		}
		
		var params = {};
		params.userName = userName;
		params.userPasswd = userPasswd;

		var url = path + "/login/validate.json";
		$.utils.sendData(url, JSON.stringify(params), function(data){
			if(data.error==0){
				window.location = path + "/toMain";
			}else{
				alert("登录失败，请联系管理员！");
			}
		});
		
		
	});
	
});



