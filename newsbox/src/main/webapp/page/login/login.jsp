<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－新闻发布</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/page/login/login.css" />
		<!-- 验证 -->
		<script type="text/javascript" src="${path }/common/jquery_validate/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${path }/page/login/login.js"></script>
	</head>
<body>
	
	<div class="div_main">
		<div class="div_login_img" style="background: url(${path}/imgs/denglu.png) no-repeat">
			<div class="div_input">
				<input type="text" class="round_corner login_input_text_top" id="userName" placeholder="请输入账号" required>
				<input type="password" class="round_corner login_input_text_bottom" id="userPasswd" placeholder="请输入密码">
			</div>
			<div class="div_checkbox">
				<input type="checkbox" style="background-color: red;border: solid 1px red;" id="checkboxOneInput" title="记住密码">
				<label for="checkboxOneInput">记住密码</label>
			</div>
		
			<div class="btn_base_noimg" id="btn_add_news">登录</div>
		</div>
	</div>




</body>
</html>