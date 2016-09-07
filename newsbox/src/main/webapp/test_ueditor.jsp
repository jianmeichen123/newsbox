<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="common/js/jquery-1.12.3.js"></script>
		<script type="text/javascript" src="common/js/json2.js"></script>
		<script type="text/javascript" src="common/js/Utils.js"></script>

		<link rel="stylesheet" type="text/css" href="common/ueditor/themes/default/css/ueditor.css" />
		
	    <script type="text/javascript" charset="utf-8" src="common/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="common/ueditor/ueditor.all.min.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="common/ueditor/lang/zh-cn/zh-cn.js"></script>
		
		<jsp:include page="common/common.jsp"></jsp:include>
	</head>
<body>

	<div>标题：</div>
	<div id="ue_parent_div" style="background: green;width: 800px;height: 500px;">
		<script type="text/plain" id="editor"></script>
	</div>
	

	
	<button id="btn_test">提交内容到服务器</button>
	
	<script type="text/javascript">
		var ue_parent_div_height = $("#ue_parent_div").height();
		 
		alert(path+ "|" + basePath);
		  
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor',{
			initialFrameWidth: '100%',
			initialFrameHeight: 370,
			isShow: true
		});
		   
		$("#btn_test").click(function(){
	    	var params = {"newContent": ue.getContent()};
	    	
	    	alert(JSON.stringify(params));
	    	var url = "/starappservice/hello/qiao";
	    	 $.utils.sendData(url,JSON.stringify(params),
	    		function(data){
	    			alert(data);
	    		}
	    	); 
	    	
	    	
		});
		   

	</script>
</body>
</html>