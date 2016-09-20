<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<td><span>关键字: </span><input type="text" class="txt_base" id="newKeyWord" value="${page.newKeyWord }" style="width: 100px;" placeholder="关键字">&nbsp;</td>
				<td><span>标题: </span><input type="text" class="txt_base" value="${page.newCaption }" id="newCaption" style="width: 100px;" placeholder="资讯标题">&nbsp;</td>
				<td>
					<span>创建日期: </span><input type="text" class="txt_base" value="${page.createTimeStart }" style="width: 80px;" id="datepicker_start"> 至 
					<input type="text" class="txt_base" value="${page.datepicker_end }" style="width: 80px;" id="datepicker_end">
				</td>
				<td><div class="btn_noimg_base" style="width: 50px;padding-left: 20px;margin-left: 5px;" id="btn_find">查询</div></td>
			</tr>
		</table>
	</div>
	<!-- 列表 -->
	<div id="news_list" style="width: 100%;height: 600px;">
	</div>
	<!-- 分页 -->
	<div style="height: 60px;line-height: 60px;padding-left: 20px;">
			共<span class="span_page_text" id="pageListCount">0</span>条，
			每页<span class="span_page_text" id="pageSize">2</span>条记录 &nbsp;&nbsp;
			当前页：<span class="span_page_text" id="pageNo">${page.pageNo }</span>&nbsp;&nbsp;
			<span class="btn_page" id="page_prev">上一页</span> &nbsp;&nbsp;
			<span class="btn_page" id="page_next">下一页</span>

	</div>
	
</body>
</html>