<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－创建新闻</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/common/ueditor/themes/default/css/ueditor.css" />
		
		<!-- 富文本 -->
	    <script type="text/javascript" charset="utf-8" src="${path }/common/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="${path }/common/ueditor/ueditor.all.js"> </script>
	    <script type="text/javascript" charset="utf-8" src="${path }/common/ueditor/lang/zh-cn/zh-cn.js"></script>
	    
	    <!-- 上传文件 -->
	    <script type="text/javascript" charset="utf-8" src="${path }/common/jquery_file_upload/ajaxfileupload.js"></script>
	    
	    <!-- 加载jqueryui -->
	    <script type="text/javascript" src="${path }/common/jquery-ui-1.12.0/jquery-ui.js"></script>
	    <link rel="stylesheet" type="text/css" href="${path }/common/jquery-ui-1.12.0/jquery-ui.theme.css" />
	    <link rel="stylesheet" type="text/css" href="${path }/common/jquery-ui-1.12.0/jquery-ui.css" />
	    
	    <!-- 用于在线编辑图片 -->
	    <script type="text/javascript" charset="utf-8" src="${path }/common/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
  		<script type="text/javascript" charset="utf-8" src="${path }/common/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
		<link rel="stylesheet" type="text/css" href="${path }/common/jquery.imgareaselect-0.9.10/css/imgareaselect-animated.css" />
		<link rel="stylesheet" type="text/css" href="${path }/common/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css" />
		<link rel="stylesheet" type="text/css" href="${path }/common/jquery.imgareaselect-0.9.10/css/imgareaselect-deprecated.css" />
		
		<!-- 自定义脚本 -->
		<link rel="stylesheet" type="text/css" href="${path }/page/news/add/news_add.css" />
		<script type="text/javascript" src="${path }/page/news/add/news_add.js"></script>
	</head>
<body>
	<!-- 这是图片编辑对话框 -->
	<div style="visibility: hidden;width: 0px;height: 0px;">
	<div id="dialog" title="编辑图片" style="padding-left: 5px;padding-right: 5px;">
		<table style="width: 100%;height: 100%;">
			<tr style="width: 100%;height: 100%;">
				<td style="width: 80%;vertical-align: top;">
					<!-- 偷放一个文件对象 -->
					<div style="width: 0px;height: 0px;visibility: hidden;" id="div_file">
						<input id="file_img" type="file" size="1" name="tdjgamtam" style="visibility: hidden;position: absolute;z-index: -1000;">
					</div>
					<img id="my_img" alt="" src="${path }/imgs/def_img.png" style="border: 1px solid #efefef;width: 800px;">
				</td>
				<td style="width: 20%;vertical-align: top;padding-left: 10px;">
					<p>缩略图</p>
					<div id="preview" style="width: 160px; height: 90px; overflow: hidden;">
				    	<img id="shrink_img" src="${path }/imgs/def_img.png" style="width: 100%;" />
				    </div>
					<table style="">
						<thead>
							<tr>
								<td colspan="2" align="center">坐标</td>
								<td colspan="2" align="center">尺寸</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 20px;">X1:</td>
								<td><input type="text" id="sel_x1" class="input_size"></td>
								<td style="padding-left: 5px;">Width:</td>
								<td><input type="text" id="sel_width" class="input_size"></td>
							</tr>
							<tr>
								<td>Y1:</td>
								<td><input type="text" id="sel_y1" class="input_size"></td>
								<td style="padding-left: 5px;">Height:</td>
								<td><input type="text" id="sel_height" class="input_size"></td>
							</tr>
							<tr>
								<td>X2:</td>
								<td><input type="text" id="sel_x2" class="input_size"></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>Y2:</td>
								<td><input type="text" id="sel_y2" class="input_size"></td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="4" style="padding-bottom: 10px;">
									<input type="button" value="选择文件" onclick="clp();">
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<input type="button" value="保存并上传" id="btn_fileupload">
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</div>
	</div>

	<div id="div_container">
		<div id="div_sub_container">
			<div id="news_info">
				<table style="width: 100%;margin-top: 10px;">
					<tr>
						<td>标题</td>
						<td>
							<input type="hidden" id="newId" value="${news.newId }">
							<textarea id="newContent"  style="position: absolute;left: -99999px;width: 0px;height: 0px;visibility: hidden;padding:0;margin:0;">${news.newContent}</textarea>
							<input type="text" id="newCaption" class="txt_base" style="width: 400px;" value="${news.newCaption }" placeholder="资讯标题">
						</td>
						<td>关键字</td>
						<td><input type="text" id="newKeyWord" class="txt_base" style="width: 400px;" value="${news.newKeyWord }" placeholder="关键字"></td>
					</tr>
					<tr>
						<td>资讯类型</td>
						<td>
							<select id="sel_news_type" class="txt_base" style="width: 410px;height: 30px;">
								<c:choose>
									<c:when test="${news.newType == 1 }">
										<option value="1" selected="selected">要闻</option>
									</c:when>	
									<c:otherwise>
										<option value="1">要闻</option>
									</c:otherwise>								
								</c:choose>
								<c:choose>
									<c:when test="${news.newType == 3 }">
										<option value="3" selected="selected">阅微</option>
									</c:when>	
									<c:otherwise>
										<option value="3">阅微</option>
									</c:otherwise>								
								</c:choose>
								<c:choose>
									<c:when test="${news.newType == 4 }">
										<option value="4" selected="selected">观点</option>
									</c:when>	
									<c:otherwise>
										<option value="4">观点</option>
									</c:otherwise>								
								</c:choose>
								<c:choose>
									<c:when test="${news.newType == 5 }">
										<option value="5" selected="selected">访谈</option>
									</c:when>	
									<c:otherwise>
										<option value="5">访谈</option>
									</c:otherwise>								
								</c:choose>
							</select>
							<!-- <input type="text" id="newType" class="txt_base" style="width: 400px;" placeholder="资讯类型"> -->
						</td>
						<td>责任编辑</td>
						<td><input type="text" id="newEditor" class="txt_base" value="${news.newEditor }" style="width: 400px;" placeholder="责任编辑"></td>
					</tr>
					<tr>
						<td>资讯来源</td>
						<td><input type="text" id="newSource" class="txt_base" style="width: 400px;" value="${news.newSource }" placeholder="资讯来源"></td>
						<td>作者</td>
						<td><input type="text" id="newAthors" class="txt_base" style="width: 400px;" value="${news.newAthors }" placeholder="作者"></td>
					</tr>
					<tr>
						<td>显示顺序</td>
						<td><input type="text" id="showIndex" class="txt_base" style="width: 400px;" value="${news.showIndex }" placeholder="请输入显示顺序"></td>
						<td>&nbsp;</td>
						<td><input type="text" id="" class="txt_base" style="width: 400px;visibility: hidden;" placeholder="预留"></td>
					</tr>
					<tr>
						<td colspan="2">
							<c:choose>
								<c:when test="${news.isWheel == 1 }">
									<input type="checkbox" checked="checked" id="isWheel">
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="isWheel">
								</c:otherwise>
							</c:choose>
							是否设置为轮播
						</td>
							
						<td class="flg_wheel">轮播标题</td>
						<td class="flg_wheel"><input type="text" id="wheelTitle" class="txt_base" value="${news.wheelTitle }" style="width: 400px;" placeholder="轮播标题"></td>
					</tr>
					<tr>
						<td colspan="2">
							<table style="width: 100%;">
								<tr><td>列表缩略图（
									<c:choose>
										<c:when test="${news.isShowBigImg == 1 }">
											<input type="checkbox" checked="checked" id="check_show_big_img">
										</c:when>
										<c:otherwise>
											<input type="checkbox" id="check_show_big_img">
										</c:otherwise>
									</c:choose>
									
									只显示第一张且设置为大图）</td>
								</tr>
								<tr>
									<td style="width: 100%;height: 100px;">
										<div class="div_img_container">
											<div class="list_shrink_img_div" id="list_img1">
												<c:choose>
													<c:when test="${!empty news.newListImg1 }">
														<img src="${news.newListImg1 }">
													</c:when>
													<c:otherwise>
														<img src="${path }/imgs/def_img.png">
													</c:otherwise>
												</c:choose>
											</div>
											<div class="list_shrink_img_div" id="list_img2">
												<c:choose>
													<c:when test="${!empty news.newListImg2 }">
														<img src="${news.newListImg2 }">
													</c:when>
													<c:otherwise>
														<img src="${path }/imgs/def_img.png">
													</c:otherwise>
												</c:choose>
											</div>
											<div class="list_shrink_img_div" id="list_img3">
												<c:choose>
													<c:when test="${!empty news.newListImg3 }">
														<img src="${news.newListImg3 }">
													</c:when>
													<c:otherwise>
														<img src="${path }/imgs/def_img.png">
													</c:otherwise>
												</c:choose>
											
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td colspan="2">
							<table style="width: 100%;">
								<tr><td class="flg_wheel">轮播缩略图</td></tr>
								<tr>
									<td style="width: 100%;height: 100px;">
										<table style="width: 100%;height: 100%;">
											<tr>
												<td style="width: 30%;" class="flg_wheel">
													<div class="list_shrink_img_div" id="list_img5">
														<img src="${path }/imgs/def_img.png" id="wheelImg">
														<c:choose>
															<c:when test="${!empty news.wheelImg }">
																<img src="${news.wheelImg }">
															</c:when>
															<c:otherwise>
																<img src="${path }/imgs/def_img.png">
															</c:otherwise>
														</c:choose>
													</div>
												</td>
												<td style="width: 70%;padding-left: 10px;vertical-align: bottom;">
													<input id="save" type="button" value="保存">&nbsp;
													<input type="button" value="发布">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr> 
				</table>
			</div>
			<div id="ue_parent_div">
				 <script type="text/plain" id="editor"></script>
			</div>
		</div>
	</div>
	

	

	<script type="text/javascript">
		  
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例

	function clp(){
		return $("#file_img").click();
	}
		   

	</script>
</body>
</html>