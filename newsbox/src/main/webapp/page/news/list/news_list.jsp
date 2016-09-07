<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－新闻发布</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/page/news/list/news_list.css" />
		
		<script src="${path }/common/laydate/laydate.js"></script>
		<script type="text/javascript" src="${path }/page/news/list/news_list.js"></script>
	</head>
<body style="">

	<div id="div_find">
		<table style="height: 100%;">
			<tr>
				<td><span>关键字: </span><input type="text" class="txt_base" style="width: 100px;" placeholder="关键字">&nbsp;</td>
				<td><span>标题: </span><input type="text" class="txt_base" style="width: 100px;" placeholder="资讯标题">&nbsp;</td>
				<td>
					<span>创建日期: </span><input type="text" class="txt_base" style="width: 80px;" id="datepicker_start"> 至 
					<input type="text" class="txt_base" style="width: 80px;" id="datepicker_end">
				</td>
				<td><div class="btn_noimg_base" style="width: 50px;padding-left: 20px;margin-left: 5px;">查询</div></td>
			</tr>
		</table>
	</div>


	
</body>
</html>