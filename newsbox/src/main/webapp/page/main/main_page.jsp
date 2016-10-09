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
<body>
	<div style="min-width: 1001px;width: 100%;height: 100%;position: absolute;">
		<div class="div_top">
			<img class="float_left" alt="" src="${path}/imgs/logo.png">
			<div class="float_left menu">
				<ul>
					<li value="gaojianku">稿库</li>
					<li value="">推送</li>
					<li value="">统计</li>
					<li value="unlogin">退出登录</li>
				</ul>
			</div>
			<div class="float-left user_info">
				<p>用户名：<span>李昂</span></p>
			</div>
		</div>
		<div class="div_center">
			<iframe id="ifm" style="width: 100%;height: 100%;border: 0;" src="" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
		</div>
	</div>
</body>
</html>