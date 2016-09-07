$(function(){
	var d = document;
	var de = d.documentElement;
	
	setWindowSize();
	
	 var ue = UE.getEditor('editor',{
		initialFrameWidth: '100%',
		initialFrameHeight: 800,
		isShow: true
	 }); 
	
	$(window).resize(function(){
		setWindowSize();
	});
	
	/**
	 * 动态设置页面各部分的宽度及高
	 */
	function setWindowSize(){
		var clientHeight = de.clientHeight;
		$("#div_container").css("height",clientHeight);
	}
	
	/**
	 * 是否显示轮播标题及图片
	 */
	$("#isWheel").change(function(){
		if($(this).is(":checked")){
			$(".flg_wheel").css("visibility","visible");
		}else{
			$(".flg_wheel").css("visibility","hidden");
		}
	});
	
	/**
	 * 生成编辑图片对话框及imgAreaSelect对象
	 */
	var img_select = null;		//图片选择器对象
	$("#dialog").dialog({
		autoOpen: false,
		width: 1100,
		height: 700,
		show: {
			duration: 300		//显示对话框的动画时间
		},
		hide: {
			duration: 300		//关闭对话框的动画时间
		},
		open: function(event,ui){
			//在dialog打开的时候将图片选择器对象生成
			img_select = $("#my_img").imgAreaSelect({
				instance: true,			//实例化以后才需要
				aspectRatio: '16:9',	//设置区域的比例
				handles: true,			//设置区域的四角显示
				fadeSpeed: 200,			//动画效果时间
				onSelectChange: preview	//选择完成后回调函数
			});
		},
		beforeClose: function(event,ui){
			if(img_select!=null){
				img_select.cancelSelection();		//关闭对话框之前需要先将已经图片选择器的选择框清除
			}
		}
    });
	
	/**
	 * imgAreaSelect选择后的回调函数
	 */
	function preview(img,selection){
		if(!selection.width || !selection.height) return;
		//设置坐标及尺寸
		$("#sel_x1").val(selection.x1);
		$("#sel_width").val(selection.width);
		$("#sel_y1").val(selection.y1);
		$("#sel_height").val(selection.height);
		$("#sel_x2").val(selection.x2);
		$("#sel_y2").val(selection.y2);
		
		//需要进行截取的原图
	    var my_img_width = $("#my_img").width();
	    var my_img_height = $("#my_img").height();
	    
	    //承载缩略图的div大小
	    var preview_width = $("#preview").width();
	    var preview_height = $("#preview").height();
	    
		var scaleX = preview_width / selection.width;
	    var scaleY = preview_height / selection.height;
	    
	    $('#preview img').css({
	        width: Math.round(scaleX*my_img_width),
	        height: Math.round(scaleY*my_img_height),
	        marginLeft: -Math.round(scaleX * selection.x1),
	        marginTop: -Math.round(scaleY * selection.y1)
	    });
	}
	
	/**
	 * 保存草稿
	 */
	$("#save").click(function(){
		$("#dialog").dialog("open");	//打开对话框
	});
	
	/**
	 * 文件上传组件change事件方法
	 */
	function file_change(file_obj){
		var $file = $(file_obj);
		var fileObj = $file[0];
		var windowURL = window.URL || window.webkitURL;
		var dataURL;
		var $img = $("#my_img");
		var shrink_img = $("#shrink_img");
		 
		if(fileObj && fileObj.files && fileObj.files[0]){
		dataURL = windowURL.createObjectURL(fileObj.files[0]);
			$img.attr('src',dataURL);
			shrink_img.attr('src',dataURL);
		}else{
			dataURL = $file.val();
			var imgObj = document.getElementById("my_img");
			var shrink_imgObj = document.getElementById("shrink_img");
			// 两个坑:
			// 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
			// 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
			imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		 
			shrink_imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			shrink_imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
		}
	}
	
	/**
	 * 为文件上传组件绑定change事件
	 */
	$("#file_img").change(function(){
		file_change(this);
	});
	
	/**
	 * 保存并上传文件，将原稿文件及选中的位置信息一并上传，上传后处理）
	 */
	$("#btn_fileupload").click(function(){
		ajaxFileUpload();
	});
	
    function ajaxFileUpload() {
    	var params = {};
    	params.x1 = $("#sel_x1").val();
    	params.y1 = $("#sel_y1").val();
    	params.x2 = $("#sel_x2").val();
    	params.y2 = $("#sel_y2").val();
    	
    	params.width = $("#sel_width").val();
    	params.height = $("#sel_height").val();
    	
    	params.imgWidth = $("#my_img").width();
    	params.imgHeight = $("#my_img").height();
    	
    	$.ajaxFileUpload({
    		url: path + '/news/uploadFile.json', //用于文件上传的服务器端请求地址
    		secureuri: false, //是否需要安全协议，一般设置为false
    		fileElementId: 'file_img', //文件上传域的ID
    		dataType: 'json', //返回值类型 一般设置为json
    		data: params,
    		success: function (data, status){  //服务器成功响应处理函数
    			
	    		alert(data + "|" + status);
	    		
	    		//在上传成功后，file组件会自动解邦change事件，此时需要重新绑定一次即可
	    		$("#file_img").change(function(){
	    			file_change(this);
	    		});  		
	    		
	    		

    		},
    		error: function (data, status, e){	//服务器响应失败处理函数
    			alert("tdj:" + e);
    		}
    	});
    }
	
});