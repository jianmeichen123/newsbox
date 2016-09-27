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
	<div class="div_top">
		<div class="float_left div_buttons" style="">
			<div style="height: 100%;margin-top: 7px;">
				<div class="btn_base" id="btn_add_news" style="background: #34acdb url(${path }/imgs/tianjia.png) no-repeat 15px 8px;">新建</div>
				<div class="btn_base" id="btn_publish" style="background: #34acdb url(${path }/imgs/fabu.png) no-repeat 15px 6px;">发布</div>
				<div class="btn_base" id="btn_disable_publish" style="width: 100px;background: #34acdb url(${path }/imgs/quxiaofabu.png) no-repeat 15px 6px;">取消发布</div>
				
				<div class="btn_base" id="btn_test" style="width: 100px;background: #34acdb url(${path }/imgs/quxiaofabu.png) no-repeat 15px 6px;">测试</div>
				<%-- <p class="float_left btn_base" id="btn_add_news"><a><img src="${path }/imgs/tianjia.png"><span>新建</span></a></p>
				<p class="float_left btn_base" id="btn_publish" style="margin-left: 10px;"><a><img src="${path }/imgs/fabu.png"><span>发布</span></a></p>
				<p class="float_left btn_base" id="btn_disable_publish" style="margin-left: 10px;width: 100px;"><a><img src="${path }/imgs/quxiaofabu.png"><span>取消发布</span></a></p> --%>
			</div>
		</div>
		<div class="float_left div_finds">
			<table style="height: 100%;">
				<tr>
					<td><span>关键字: </span><input type="text" class="txt_base" id="newKeyWord" value="${page.newKeyWord }" style="width: 100px;" placeholder="关键字">&nbsp;</td>
					<td><span>标题: </span><input type="text" class="txt_base" value="${page.newCaption }" id="newCaption" style="width: 100px;" placeholder="资讯标题">&nbsp;</td>
					<td>
						<span>创建日期: </span><input type="text" class="txt_base" value="${page.createTimeStart }" style="width: 80px;" id="datepicker_start"> 至 
						<input type="text" class="txt_base" value="${page.datepicker_end }" style="width: 80px;" id="datepicker_end">
					</td>
					<td>
						是否发布:
						<select class="txt_base" style="height: 26px;" id="select_is_publish">
							<option value="all">全部</option>
							<option value="1">已发布</option>
							<option value="0">未发布</option>
						</select>
					</td>
					<td>
						<p class="float_left btn_base_noimg" id="btn_find">查询</p>
						<p class="btn_base_noimg"  class="float_left btn_base" id="btn_cancel_find">重置</p>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 列表区-->
	<div id="div_container">
		<div id="div_data_list">
		</div>
		
		<!-- 分页 -->
		<div class="div_page" style="">
				共<span class="span_page_text" id="pageListCount">0</span>条，
				每页<span class="span_page_text" id="pageSize">10</span>条记录 &nbsp;&nbsp;
				当前页：<span class="span_page_text" id="pageNo">${page.pageNo }</span>&nbsp;&nbsp;
				<span class="btn_page" id="page_prev">上一页</span> &nbsp;&nbsp;
				<span class="btn_page" id="page_next">下一页</span>
		</div>
	</div>

	
	
	<%-- <div id="div_list">
		<iframe id="ifm_news" style="width: 100%;height: 100%;"  src="${path }/news/toListPage"
		frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
	</div> --%>




<%-- 	<div id="div_top">
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
			<div id="btn_test" class="btn_base btn_tianjia" style="width: 120px;"><img alt="" src="${path }/imgs/tianjia.png">
				<span style="padding-left: 10px;">测试按钮</span>
			</div>
		</div>
	</div>
	<!-- 列表区-->
	<div id="div_list">
		<iframe id="ifm_news" style="width: 100%;height: 100%;"  src="${path }/news/toListPage"
		frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
	</div> --%>
	 

	
</body>
</html>