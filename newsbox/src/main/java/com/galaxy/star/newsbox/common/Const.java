package com.galaxy.star.newsbox.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;

public class Const {
	public static WebApplicationContext context;
	
	public static final Integer PAGE_SIZE = 10;							//新闻列表页，每页默认记录数
	
	//local dev qa等
	//public static final String FILE_PATH = "";			//测试图片文件保存的地址
	//public static final String HTML_SERVER = "http://fxmobnew.galaxyinternet.com/newsbox";			//测试图片上传成功后与富文本生成后对应html服务器
	
	//线上   
//	public static final String FILE_PATH = "/data/wwwroot/fxapp.galaxyinternet.com";	//线上   图片文件保存的地址
//	public static final String HTML_SERVER = "https://fxapp.galaxyinternet.com";		//线上   图片上传成功后与富文本生成后对应html服务器

	public static final String FILE_PATH = "/data/websites/fxapp_file/file";	//线上   图片文件保存的地址
	public static final String HTML_SERVER = "http://fxmobnew.galaxyinternet.com/file";		//线上   图片上传成功后与富文本生成后对应html服务器
	
	public static final String IMG_SRC_DIR_NAME = "img_src_dir";		//用于存放上传的原图片文件夹
	public static final String IMG_DEAL_DIR_NAME = "img_deal_dir";		//用于存放裁剪处理以的图片的文件夹
	public static final String IMG_CATCH_DIR_NAME = "img_catch_dir";	//用于存放自动抓取的图片的文件夹
	public static final String HTML5_DIR_NAME = "html5";				//存放由富文本生成html5页面的文件夹
	public static final String UEDITOR_IMGS = "ueditor" + File.separator + "imgs";		//Ueditor上传的图片
	public static final String UEDITOR_VIDEOS = "ueditor" + File.separator + "videos";	//Ueditor上传的视频
	public static final String UEDITOR_OTHERS = "ueditor" + File.separator + "other";	//Ueditor上传的视频
	public static final String CUT_FILE_PRE = "cut_";									//图片经过裁剪处理后生成的图片文件前面的前缀
	
	/**
	 * 根据给定的html名称拼写出mobile能够访问的html地址
	 * @param request
	 * @return
	 */
	public static String getNewsHtml5Url(HttpServletRequest request,String newHtmlFileName){
		return Const.getHtmlServer(request) + "/" + Const.HTML5_DIR_NAME + "/" + newHtmlFileName;
	}
	
	public static String getHtmlServer(HttpServletRequest request){
		if(CUtils.init().strIsNotNull(HTML_SERVER)){
			return HTML_SERVER;
		}else{
			String url = request.getScheme() + "://"; 	//请求协议 http 或 https    
			url += request.getHeader("host");  			// 请求服务器    
			url += request.getContextPath();     		// 工程名
			
			return url;
		}
	}
	
	public static String getCssPath(HttpServletRequest request){
		//return Const.getHtmlServer(request) + "/common/css/app_common.css";
		return "../common/css/app_common.css";
	}
	
	public static String getFilePath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path;
	}
	
	public static String getImgSrcPath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + IMG_SRC_DIR_NAME;
	}
	
	public static String getUEImagePath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + UEDITOR_IMGS;
	}
	
	public static String getUECatchImagePath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + IMG_CATCH_DIR_NAME;
	}
	
	public static String getUEOtherPath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + UEDITOR_OTHERS;
	}
	
	public static String getUEVideoPath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + UEDITOR_VIDEOS;
	}
	
	public static String getImgDealPath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + IMG_DEAL_DIR_NAME;
	}

}
