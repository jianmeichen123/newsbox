

$(function(){
	var d = document;
	var de = d.documentElement;

	setWindowSize();
	
	
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
	 * 加载列表
	 */
	var params = {};
	params.pageNo = 1;
	params.pageSize = 10;
	var url = path + "/news/getNewsList.json";
	$.utils.sendData(url,JSON.stringify(params),function(data){
		
		var newListData = data.newsList;
		if(newListData){
			var news_list = $("#news_list");
			//alert(JSON.stringify(newListData));
			for(var i=0;i<newListData.length;i++){
				var newId = newListData[i].newId;
				var newCaption = newListData[i].newCaption;
				var createTime = newListData[i].createTime;
				var newKeyWord = newListData[i].newKeyWord;
				var newListImg1 = newListData[i].newListImg1;
				var newListImg2 = newListData[i].newListImg2;
				var newListImg3 = newListData[i].newListImg3;
				var newSource = newListData[i].newSource;
				
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
				'<div class="news_list_item" id="'+newId+'">' + 
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
				'</div>');
				news_list.append(div_htmls);
			}
			
			/**
			 * 为列表item绑定点击事件
			 */
			$(".news_list_item").click(function(){
				var newId = $(this).attr("id");
				var ifm_news = $('#ifm_news', window.parent.document);
				var ifm = $('#ifm', window.parent.document);
				//ifm.attr("src",path + "/page/news/add/news_add.jsp");
				alert(ifm.attr("src"));
			});

		}
	});
	

	

});

