<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－新闻发布</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/page/news/news_main.css" />
		
		<script src="${path }/common/laydate/laydate.js"></script>
		<script type="text/javascript" src="${path }/page/news/news_main.js"></script>
	</head>
<body style="">
	<div id="div_top">
		<div class="div_btns">
			<div id="btn_add_news" class="btn_base btn_tianjia"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">新建</span>
			</div>
			<div id="btn_del_news" class="btn_base btn_tianjia"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">删除</span>
			</div>
			<div class="btn_base btn_tianjia" id="btn_publish"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">发布</span>
			</div>
			<div class="btn_base btn_tianjia" id="btn_disable_publish" style="width: 120px;"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">取消发布</span>
			</div>
			<div id="btn_back_list" class="btn_base btn_tianjia" style="width: 120px;"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">返回列表</span>
			</div>
		</div>
	</div>
	<!-- 列表区-->
	<div id="div_list">
		<iframe id="ifm_news" style="width: 100%;height: 90%;" src="${path }/news/toListPage"
		frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
	</div>
	 

	
</body>
</html>