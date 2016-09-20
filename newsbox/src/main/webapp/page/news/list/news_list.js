

$(function(){
	var d = document;
	var de = d.documentElement;

	setWindowSize();
	loadPageList();			//打开页面时自动加载列表
	
	
	$(window).resize(function(){
		setWindowSize();
	});
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clienHeight = de.clientHeight;
		$("#div_list").css("height",clienHeight);
	}

	/**
	 * 加载列表函数
	 * 
	 */
	function loadPageList(pageNo,pageSize){
		var params = {};
		if(!pageNo){
			pageNo = $("#pageNo").html();
		}
		if(!pageSize){
			pageSize = $("#pageSize").html();
		}
		params.pageNo = pageNo;
		params.pageSize = pageSize;
		params.newCaption = $("#newCaption").val();
		params.newKeyWord = $("#newKeyWord").val();
		var createTimeStart = $("#datepicker_start").val();
		var datepicker_end = $("#datepicker_end").val();
		if(createTimeStart!=="" && createTimeStart!==undefined){
			params.createTimeStart = createTimeStart;
		}
		
		if(datepicker_end!=="" && datepicker_end!==undefined){
			params.datepicker_end = datepicker_end;
		}
		
		var url = path + "/news/getNewsList.json";
		$.utils.sendData(url,JSON.stringify(params),function(data){
			$("#pageListCount").html(data.listCount);		//共多少条记录
			
			//写回列表
			var newListData = data.newsList;
			if(newListData){
				var news_list = $("#news_list");
				//如果返回正确，则首先清除已经存在列表项
				news_list.html("");
				for(var i=0;i<newListData.length;i++){
					var newId = newListData[i].newId;				//id
					var newCaption = newListData[i].newCaption;		//新闻标题 
					var createTime = newListData[i].createTime;		//创建时间
					var newKeyWord = newListData[i].newKeyWord;		//关键字
					var newListImg1 = newListData[i].newListImg1;	//列表缩略图1
					var newListImg2 = newListData[i].newListImg2;	//列表缩略图2
					var newListImg3 = newListData[i].newListImg3;	//列表缩略图3
					var isPublish = newListData[i].isPublish;		//是否已发布
					
					var flag = 0;	//用于判断列表项有几张图片
					if(newListImg1){
						if(newListImg2 && newListImg3){
							flag = 3;
						}
					}else{
						if(newListImg2){
							newListImg1 = newListImg2;
						}else{
							if(newListImg3){
								newListImg1 = newListImg3;
							}
						}
					}
					if(newListImg1){
						flag = 1;
					}
			
					//生成列表项
					var div_htmls = 
					'<div class="news_list_item" id="news_list_item_'+newId+'">' + 
						'<div class="float_left div_list_imgs">';
					if(flag==0){
					}
					
					if(flag==1){
						div_htmls += '<img alt="" src="'+newListImg1+'">';
					}
					
					if(flag==3){
						div_htmls += (
							'<img alt="" src="'+newListImg1+'">' + 
							'<img alt="" src="'+newListImg2+'">' + 
							'<img alt="" src="'+newListImg3+'">');
					}
							
					div_htmls+=(
						'</div>' + 
						'<div class="float_left div_list_container">' + 
							'<div class="div_list_caption">'+newCaption+' </div>' + 		//标题 
							'<div style="height: 50%;">' + 
								'<div class="float_left div_list_time_p">' + 
									'<div class="float_left div_list_time_label">发稿时间</div>' + 
									'<div class="float_left div_list_time_value">'+createTime+'</div>' +	//发稿时间 
								'</div>' + 
								
								'<div class="float_left div_list_key_p">' + 
									'<div class="float_left div_list_key_label">关键字</div>' + 
									'<div class="float_left div_list_key_value">'+newKeyWord+' </div>' + 
								'</div>' + 
							'</div>' + 
						'</div>' + 
						'<div class="float_left div_bianji" style="">' +
							'<span class="float_left" style="margin-right: 10px;" id="span_publish_'+newId+'">');
					
						if(isPublish>=1){
							div_htmls += "发布中";
						}else{
							div_htmls += "未发布";
						}
						
						div_htmls += (
							'</span>'+
							'<img class="float_left" style="margin-top: 38px;margin-right: 30px;" src="'+path+'/imgs/bianji.png">' + 
							'<input type="checkbox" value="'+newId+'">' +
						'</div>' + 
						
					'</div>');
					news_list.append(div_htmls);
				}
				
				//列表内编辑按钮鼠标悬停事件
				$(".div_bianji img").mouseover(function(){
					$(this).attr("src",path + "/imgs/bianji1.png");
				});
				//列表内编辑按钮鼠标离开事件
				$(".div_bianji img").mouseleave(function(){
					$(this).attr("src",path + "/imgs/bianji.png");
				});
				//列表内编辑按钮鼠标点击跳转到编辑页面
				$(".div_bianji img").click(function(){
					var ifm_news = $('#ifm_news', window.parent.document);
					ifm_news.attr("src",path + "/news/toEditNewPage" + "?newId=" + newId);
				});
				
				//鼠标经过列表项更改颜色
				$(".news_list_item").mouseover(function(){
					$(this).css("background","#f9f9f9");
				});
				//列表内编辑按钮鼠标离开事件
				$(".news_list_item").mouseleave(function(){
					$(this).css("background","white");
				});
				$(".news_list_item").click(function(){
					$(this).find(":checkbox").prop("checked",!$(this).find(":checkbox").is(":checked"));
					
				});
			}
		});
	}
	

	
	/**
	 * 上一页
	 */
	$("#page_prev").click(function(){
		var pageSize = $("#pageSize").html();
		var curPage = $("#pageNo").html();
		
		if(curPage>1){
			curPage--;
		}else{
			curPage = 1;
		}
		
		$("#pageNo").html(curPage);			//将当前页回写
		loadPageList(curPage,pageSize);
	});
	
	/**
	 * 下一页
	 */
	$("#page_next").click(function(){
		var totalItem = $("#pageListCount").html();
		var pageSize = $("#pageSize").html();
		var totalPage = Math.ceil(totalItem/pageSize);
		var curPage = $("#pageNo").html();
		
		if(curPage<totalPage){
			curPage++;
		}else{
			curPage = parseInt(totalPage);
		}
		
		$("#pageNo").html(curPage);			//将当前页回写
		loadPageList(curPage,pageSize);
	});
	
	/**
	 * 查询
	 */
	$("#btn_find").click(function(){
		saveFindParams();
		loadPageList();
	});
	
	function saveFindParams(){
		var pageNo = $("#pageNo");	//当前页
		var newCaption = $("#newCaption");
		var newKeyWord = $("#newKeyWord");
		var createTimeStart = $("#datepicker_start");
		var datepicker_end = $("#datepicker_end");
		
		find_params = "?pageNo=" + pageNo.html() + "&newCaption=" + newCaption.val() + "&newKeyWord=" + newKeyWord.val() +
			"&createTimeStart=" + createTimeStart.val() + "&datepicker_end=" + datepicker_end.val();
		return find_params; 
	}
	

	laydate({
	   elem: '#datepicker_start'
	});
	
	laydate({
	   elem: '#datepicker_end'
	});

});

