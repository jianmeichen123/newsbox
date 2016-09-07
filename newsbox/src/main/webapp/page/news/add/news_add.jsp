<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>繁星－新建新闻</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<jsp:include page="/common/common.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="${path }/common/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${path }/common/ueditor/themes/default/css/ueditor.css" />
		
		<!-- 富文本 -->
	    <script type="text/javascript" charset="utf-8" src="${path }/common/ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="${path }/common/ueditor/ueditor.all.min.js"> </script>
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

	<div id="div_container">
		<div id="div_sub_container">
			<div id="news_info">
				<table style="width: 100%;">
					<tr>
						<td>标题</td>
						<td><input type="text" id="newCaption" class="txt_base" style="width: 400px;" placeholder="资讯标题"></td>
						<td>关键字</td>
						<td><input type="text" id="newKeyWord" class="txt_base" style="width: 400px;" placeholder="关键字"></td>
					</tr>
					<tr>
						<td>资讯类型</td>
						<td><input type="text" id="newType" class="txt_base" style="width: 400px;" placeholder="资讯类型"></td>
						<td>责任编辑</td>
						<td><input type="text" id="newEditor" class="txt_base" style="width: 400px;" placeholder="责任编辑"></td>
					</tr>
					<tr>
						<td>资讯来源</td>
						<td><input type="text" id="newSource" class="txt_base" style="width: 400px;" placeholder="资讯来源"></td>
						<td>作者</td>
						<td><input type="text" id="newAthors" class="txt_base" style="width: 400px;" placeholder="作者"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" id="isWheel">是否设置为轮播</td>
						<td class="flg_wheel">轮播标题</td>
						<td class="flg_wheel"><input type="text" id="wheelTitle" class="txt_base" style="width: 400px;" placeholder="轮播标题"></td>
					</tr>
					<tr>
						<td colspan="2">
							<table style="width: 100%;">
								<tr><td>列表缩略图</td></tr>
								<tr>
									<td style="width: 100%;height: 100px;">
										<div class="div_img_container"></div>
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
													<div class="div_img_container"></div>
												</td>
												<td style="width: 70%;padding-left: 10px;vertical-align: bottom;">
													<input id="save" type="button" value="保存草稿">&nbsp;
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