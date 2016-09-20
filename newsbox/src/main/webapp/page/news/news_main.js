
$(function(){
	var d = document;
	var de = d.documentElement;
	setWindowSize();
	
	$(window).resize(function(){
		setWindowSize();
	});
	
	/**
	 * 新建
	 */
	$("#btn_add_news").click(function(){
		//$('#ifm', window.parent.document).attr("src",path + "/page/news/add/news_add.jsp");
		//首先记录下查询的相关内容
		saveFindParams();
		
		$("#ifm_news").attr("src",path + "/news/toEditNewPage");
	});
	
	function saveFindParams(){
		var pageNo = $("#pageNo",window.frames[0].document);	//当前页
		var newCaption = $("#newCaption",window.frames[0].document);
		var newKeyWord = $("#newKeyWord",window.frames[0].document);
		var createTimeStart = $("#datepicker_start",window.frames[0].document);
		var datepicker_end = $("#datepicker_end",window.frames[0].document);
		find_params.pageNo = pageNo.html();
		find_params.newCaption = newCaption.val();
		find_params.newKeyWord = newKeyWord.val();
		find_params.createTimeStart = createTimeStart.val();
		find_params.datepicker_end = datepicker_end.val();
		
//		find_params = "?pageNo=" + pageNo.html() + "&newCaption=" + newCaption.val() + "&newKeyWord=" + newKeyWord.val();
//			"&createTimeStart=" + createTimeStart.val() + "&datepicker_end=" + datepicker_end.val();
		
		return find_params; 
	}
	
	function createFindParams(){
		var find_params_str = "?first_params=1";
		if(find_params.pageNo){
			find_params_str += ("&pageNo=" + find_params.pageNo);
		}
		
		if(find_params.newCaption){
			find_params_str += ("&newCaption=" + find_params.newCaption);
		}
		
		if(find_params.newKeyWord){
			find_params_str += ("&newKeyWord=" + find_params.newKeyWord);
		}
		
		if(find_params.createTimeStart){
			find_params_str += ("&createTimeStart=" + find_params.createTimeStart);
		}
		
		if(find_params.datepicker_end){
			find_params_str += ("&datepicker_end=" + find_params.datepicker_end);
		}
		return find_params_str;
	}
	
	/**
	 * 删除
	 */
	$("#btn_del_news").click(function(){
		var checkboxs = $("#news_list :checked",window.frames[0].document);
		if(checkboxs.size()<=0){
			alert("请选择需要删除的新闻！");
		}else{
			$(checkboxs).each(function(){
				var newId = $(this).val();
				var url = path + "/news/deleteNews.json";
				var params = {"newId":newId};
				$.utils.sendData(url,JSON.stringify(params),function(data){
					if(data && data.error==0){
						alert("删除成功！");
						$("#ifm_news").attr("src",path + "/news/toListPage" + createFindParams());
					}
				});
			});
		}
	});
	
	/**
	 * 发布
	 */
	$("#btn_publish").click(function(){
		var checkboxs = $("#news_list :checked",window.frames[0].document);
		if(checkboxs.size()<=0){
			alert("请选择需要发布的新闻！");
		}else{
			$(checkboxs).each(function(){
				var newId = $(this).val();
				var url = path + "/news/publishNews.json";
				var params = {"newId":newId};
				$.utils.sendData(url,JSON.stringify(params),function(data){
					if(data && data.error==0){
						alert("发布成功！");
						var span_publish = $("#span_publish_"+newId,window.frames[0].document);
						span_publish.html("已发布");
					}
				});
			});
		}
	});
	
	/**
	 * 取消发布
	 */
	$("#btn_disable_publish").click(function(){
		var checkboxs = $("#news_list :checked",window.frames[0].document);
		if(checkboxs.size()<=0){
			alert("请选择需要取消发布的新闻！");
		}else{
			$(checkboxs).each(function(){
				var newId = $(this).val();
				var url = path + "/news/cancelPublishNews.json";
				var params = {"newId":newId};
				$.utils.sendData(url,JSON.stringify(params),function(data){
					if(data && data.error==0){
						alert("取消发布成功！");
						var span_publish = $("#span_publish_"+newId,window.frames[0].document);
						span_publish.html("未发布");
					}
				});
			});
		}
	});
	
	/**
	 * 返回列表
	 */
	$("#btn_back_list").click(function(){
		//alert(createFindParams());
		$("#ifm_news").attr("src",path + "/news/toListPage" + createFindParams());
	});
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clienHeight = de.clientHeight;
		$("#div_list").css("height",clienHeight);
	}

});