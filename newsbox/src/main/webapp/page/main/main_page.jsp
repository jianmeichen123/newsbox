<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－新闻发布</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/page/main/main_page.css" />
		<script type="text/javascript" src="${path }/page/main/main_page.js"></script>
	</head>
<body style="">
	<div id="div_top">
		<div class="logo"><img src="${path}/imgs/logo.png"/></div>
		<div class="menu">
			<ul>
				<li value="gaojianku">稿件库</li>
				<li value="tusong">推送</li>
				<li value="tongji">统计</li>
			</ul>
		</div>
		 <div class="info">
		 	<span>用户名：李昂</span>
		 </div>
	</div>

	<div id="div_content" style="">
<!-- 		<div class="div_left">
			<ul>
				<li>我的稿件库</li>
			</ul>
		</div> -->

		<div class="div_right">
			<iframe id="ifm" src="" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
		</div>
	</div>
	
	

	<script type="text/javascript">
		
	</script>
</body>
</html>