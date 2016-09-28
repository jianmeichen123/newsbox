
$(function(){
	var d = document;
	var de = d.documentElement;
	
	setWindowSize();
	
	
	$(window).resize(function(){
		setWindowSize();
	});
	
	//初始化日期组件
	laydate({
	   elem: '#datepicker_start'
	});
	
	laydate({
	   elem: '#datepicker_end'
	});
	
	loadFinds();
	createLoading();
	
	function createLoading(){
		var loading_div = '<div id="loading_div" style="background: #fff;position: absolute;overflow: hidden;">'+
		'<img src="'+path+'/imgs/loading'+$.utils.getRandInt(5, 1)+'.gif"></div>';
		
		$("#div_container").append(loading_div);
		setLoadingPosition();
	}
	
	function showLoading(){
		if($("#loading_div").length>0){
			
			$("#loading_div").find("img").attr("src",path + "/imgs/loading" + $.utils.getRandInt(5, 1) + ".gif");
			
			//$.utils.getRandInt(5, 1)
			$("#loading_div").show();
		}
	}
	
	function hideLoading(){
		if($("#loading_div").length>0){
			$("#loading_div").hide();
		}
	}
	
	function setLoadingPosition(){
		var clienHeight = de.clientHeight;
		var div_top_height = $(".div_top").height();
		var clientWidth = de.clientWidth;
		var loading_img_height = 300;
		//alert($("#loading_div").length>0);
		if($("#loading_div").length>0){
			var loading_div = $("#loading_div");
			var loading_div_img = $("#loading_div").find("img");
			loading_div.css("width",clientWidth-20);
			if($("#div_data_list").height()<clienHeight){
				loading_div.css("height",clienHeight);
			}else{
				loading_div.css("height",$("#div_data_list").height());
			}
			
			loading_div.css("top",div_top_height-110);
			
			loading_div_img.css("width",loading_img_height);
			loading_div_img.css("padding-top",(clienHeight-div_top_height-80-300)/2);
			loading_div_img.css("padding-left",(clientWidth-loading_div_img.width())/2);
		}
	}
	
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clienHeight = de.clientHeight;
		var div_top_height = $(".div_top").height();
		$("#div_container").css("height",clienHeight-div_top_height);
		
		setLoadingPosition();
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
		var isPublish = $("#select_is_publish").val();
		if(isPublish!=="all"){
			params.isPublish = isPublish;
		}
		
		var url = path + "/news/getNewsList.json";
		$.utils.sendData(url,JSON.stringify(params),function(data){
			$("#pageListCount").html(data.listCount);		//共多少条记录
			
			//写回列表
			var newListData = data.newsList;
			if(newListData){
				var news_list = $("#div_data_list");
				//如果返回正确，则首先清除已经存在列表项
				news_list.html("");
				var count = 0;
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
						}else{
							flag = 1;
						}
					}else{
						if(newListImg2){
							newListImg1 = newListImg2;
						}else{
							if(newListImg3){
								newListImg1 = newListImg3;
							}
						}
						if(newListImg1){
							flag = 1;
						}
					}
			
					//生成列表项
					count = i+1;
					var div_htmls = 
					'<div class="news_list_item" style="top: '+(100*i)+'px" id="news_list_item_'+newId+'">' + 
						'<div style="width: 60px;float: left;line-height: 100px;text-align: center;">'+
						'<input class="news_list_item_checkbox" type="checkbox" value="'+newId+'">'
						+'</div>' + 
						'<div class="float_left div_list_imgs">&nbsp;';
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
							'<div style="height: 50%;width: 100%;">' + 
								'<div class="float_left div_list_time_p">' + 
									'<div class="float_left div_list_time_label">发稿时间</div>' + 
									'<div class="float_left div_list_time_value">'+createTime+'</div>' +	//发稿时间 
								'</div>' + 
								
								'<div class="float_left div_list_key_p">' + 
									'<div class="float_left div_list_key_label">关键字</div>');
						if(newKeyWord && newKeyWord.length>0){
							div_htmls+=('<div class="float_left div_list_key_value">'+newKeyWord+' </div>');
						}else{
							div_htmls+=('<div class="float_left div_list_key_value">暂无设置 </div>');
						}
							
					div_htmls+=('</div>' + 
							'</div>' + 
						'</div>' + 
						'<div class="float_left div_bianji" >');
							//'<span class="float_left" style="margin-right: 10px;" id="span_publish_'+newId+'">');
							
						if(isPublish>=1){
							//div_htmls += ("发布中");
							div_htmls += ('<img class="float_left" style="margin-top: 38px;margin-right: 30px;" id="fabu_'+newId+'" src="'+path+'/imgs/fabu1.png">');
						}else{
							div_htmls += ('<img class="float_left" style="margin-top: 38px;margin-right: 30px;" id="fabu_'+newId+'" src="'+path+'/imgs/quxiaofabu1.png">');
						}
						
						div_htmls += (
							//'</span>'+
							'<img class="float_left edit_news" style="margin-top: 38px;margin-right: 30px;" id="edit_new_'+newId+'" src="'+path+'/imgs/bianji.png">' +
							'<img class="float_left new_del" style="margin-top: 38px;margin-right: 30px;" id="del_new_'+newId+'" src="'+path+'/imgs/lajixiang.png">'
						);
						
						
//							'<span class="new_del" id="btn_new_del_'+newId+'">删除</span>' +
						div_htmls += ('' +
						'</div>' + 
						
					'</div>');
					news_list.append(div_htmls);
				}
				//设置分页组件的位置
				if(count<4){
					count = 4;
				}
				$(".div_page").css("top",100*count + 20);
				$(".div_page").css("left",70);
				
				$("#div_data_list").css("height",100*count + 100);
				setLoadingPosition();
				
				//列表内编辑按钮鼠标悬停事件
				$(".div_bianji .edit_news").mouseover(function(){
					$(this).attr("src",path + "/imgs/bianji1.png");
				});
				//列表内编辑按钮鼠标离开事件
				$(".div_bianji .edit_news").mouseleave(function(){
					$(this).attr("src",path + "/imgs/bianji.png");
				});
				//列表内编辑按钮鼠标点击跳转到编辑页面
				$(".div_bianji .edit_news").click(function(){
					var btnId = $(this).attr("id");
					var newId = btnId.substring(9,btnId.length);
					saveFinds(function(){
		
						if(newId){
							var ifm_news = $('#ifm', window.parent.document);
							ifm_news.attr("src",path + "/news/toEditNewPage" + "?newId=" + newId);
						}
					});
				});
				
				//鼠标经过列表项更改颜色
				$(".news_list_item").mouseover(function(){
					$(this).css("background","#f9f9f9");
					$(this).find(".div_bianji").css("visibility","visible");
				});
				//列表内编辑按钮鼠标离开事件
				$(".news_list_item").mouseleave(function(){
					$(this).css("background","white");
					$(this).find(".div_bianji").css("visibility","hidden");
				});
				
				
				$(".news_list_item").click(function(){
					$(this).find(":checkbox").prop("checked",!$(this).find(":checkbox").is(":checked"));
				});
				
				$(".news_list_item_checkbox").click(function(){
					$(this).prop("checked",!$(this).is(":checked"));
				});
				
				//删除
				$(".new_del").click(function(){
					var btnId = $(this).attr("id");
					var newId = btnId.substring(8,btnId.length);
					if(newId){
						var url = path + "/news/deleteNews.json";
						var params = {"newId":newId};
						$.utils.sendData(url,JSON.stringify(params),function(data){
							if(data && data.error==0){
								alert("删除成功！");
								//$("#ifm_news").attr("src",path + "/news/toListPage" + createFindParams());
								loadPageList();
							}
						});
					}
				});
				
				hideLoading();
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
		
		showLoading();
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
		showLoading();
		$("#pageNo").html(curPage);			//将当前页回写
		loadPageList(curPage,pageSize);
	});
	
	/**
	 * 查询
	 */
	$("#btn_find").click(function(){
		showLoading();
		saveFinds(loadPageList());
	});
	
	/**
	 * 取消查询
	 */
	$("#btn_cancel_find").click(function(){
		$("#pageNo").html(1);
		$("#newCaption").val("");
		$("#newKeyWord").val("");
		$("#datepicker_start").val("");
		$("#datepicker_end").val("");
		$("#select_is_publish").val("all");
		showLoading();
		saveFinds(loadPageList());
		
	});
	
	/**
	 * 测试按钮
	 */
	$("#btn_test").click(function(){ 
		alert($.utils.getRandInt(5, 1));
//		var url = path + "/mobile/getNewsList.json";
//		var params = {"pageSize": 10,"pageNo": 1};
//		$.utils.sendData(url,JSON.stringify(params),function(data){
//			alert(JSON.stringify(data));
//		});
	});
	
	/**
	 * 新建
	 */
	$("#btn_add_news").click(function(){
		$('#ifm', window.parent.document).attr("src",path + "/news/toEditNewPage");
	});
	
	/**
	 * 保存查询条件
	 */
	function saveFinds(fn){
		var url = path + "/news/saveAndGetFind.json";
		var params = getFindParams();
		params.type = "save";
		$.utils.sendData(url,JSON.stringify(params),function(data){
			if(data.error!==0){
				alert("保存查询条件失败！");
			}
			fn();
		});
	}
	
	/**
	 * 加载原来保存的查询条件
	 */
	function loadFinds(){
		var url = path + "/news/saveAndGetFind.json";
		var params = {};
		params.type = "get";
		$.utils.sendData(url,JSON.stringify(params),function(data){
			//alert(JSON.stringify(data));
			if(data.error==0){
				var finds = data.finds;
				if(finds){
					if(finds.pageNo){
						$("#pageNo").html(finds.pageNo);
					}
					if(finds.newCaption){
						$("#newCaption").val(finds.newCaption);
					}
					if(finds.newKeyWord){
						$("#newKeyWord").val(finds.newKeyWord);
					}
					if(finds.createTimeStart){
						$("#createTimeStart").val(finds.createTimeStart);
					}
					if(finds.datepicker_end){
						$("#datepicker_end").val(finds.datepicker_end);
					}
					if(finds.isPublish){
						$("#select_is_publish").find("option[value='"+finds.isPublish+"']").attr("selected",true);
					}
				}	
			}else{
				$("#pageNo").html(1);
			}
			loadPageList();
		});
	}
	
	/**
	 * 创建查询参数
	 */
	function getFindParams(){
		var find_params = {};
		var pageNo = $("#pageNo");	//当前页
		var newCaption = $("#newCaption");
		var newKeyWord = $("#newKeyWord");
		var createTimeStart = $("#datepicker_start");
		var datepicker_end = $("#datepicker_end");
		var select_is_publish = $("#select_is_publish");
		find_params.pageNo = pageNo.html();
		find_params.newCaption = newCaption.val();
		find_params.newKeyWord = newKeyWord.val();
		find_params.createTimeStart = createTimeStart.val();
		find_params.datepicker_end = datepicker_end.val();
		find_params.isPublish = select_is_publish.val();
		
		return find_params; 
	}
	
//	/**
//	 * 删除
//	 */
//	$("#btn_del_news").click(function(){
//		var checkboxs = $("#news_list :checked",window.frames[0].document);
//		if(checkboxs.size()<=0){
//			alert("请选择需要删除的新闻！");
//		}else{
//			$(checkboxs).each(function(){
//				var newId = $(this).val();
//				var url = path + "/news/deleteNews.json";
//				var params = {"newId":newId};
//				$.utils.sendData(url,JSON.stringify(params),function(data){
//					if(data && data.error==0){
//						alert("删除成功！");
//						$("#ifm_news").attr("src",path + "/news/toListPage" + createFindParams());
//					}
//				});
//			});
//		}
//	});
//	
	/**
	 * 发布
	 */
	$("#btn_publish").click(function(){
		var checkboxs = $("#div_data_list :checked");
		if(checkboxs.size()<=0){
			alert("请选择需要发布的新闻！");
		}else{
			var newIds = [];
			$(checkboxs).each(function(){
				newIds.push($(this).val());
			});

			var url = path + "/news/publishNews.json";
			var params = {"newIds":newIds};
			params.type = "publish";
			$.utils.sendData(url,JSON.stringify(params),function(data){
				if(data && data.error==0){
					alert("发布成功！");
					for(var i=0;i<newIds.length;i++){
						var img_publish = $("#fabu_"+newIds[i]);
						img_publish.attr("src",path + "/imgs/fabu1.png");

					}
				}
			});
		}
	});
	
	/**
	 * 取消发布
	 */
	$("#btn_disable_publish").click(function(){
		var checkboxs = $("#div_data_list :checked");
		if(checkboxs.size()<=0){
			alert("请选择需要取消发布的新闻！");
		}else{
			var newIds = [];
			$(checkboxs).each(function(){
				newIds.push($(this).val());
			});

			var url = path + "/news/publishNews.json";
			var params = {"newIds":newIds};
			params.type = "no_publish";
			$.utils.sendData(url,JSON.stringify(params),function(data){
				if(data && data.error==0){
					alert("取消发布成功！");
					for(var i=0;i<newIds.length;i++){
						var img_publish = $("#fabu_"+newIds[i]);
						img_publish.attr("src",path + "/imgs/quxiaofabu1.png");
					}
				}
			});
		}
	});


});