package com.galaxy.star.newsbox.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;

public class Const {
	public static WebApplicationContext context;
	
	public static final String FILE_PATH = "Z:\\www";
	public static final String IMG_SRC_DIR_NAME = "img_src_dir";		//用于存放上传的原图片文件夹
	public static final String IMG_DEAL_DIR_NAME = "img_deal_dir";		//用于存放裁剪处理以的图片的文件夹
	public static final String HTML5_DIR_NAME = "html5";				//存放由富文本生成html5页面的文件夹
	public static final String HTML_SERVER = "http://tdj2.galaxy.com";	//图片上传成功后与富文本生成后对应html服务器
	public static final String CUT_FILE_PRE = "cut_";					//图片经过裁剪处理后生成的图片文件前面的前缀
	
	public static String getImgSrcPath(HttpServletRequest request){
		String path = null;
		if(FILE_PATH==null || "".equals(FILE_PATH.trim())){
			path = request.getSession().getServletContext().getRealPath("");
		}else{
			path = FILE_PATH;
		}
		return path + File.separator + IMG_SRC_DIR_NAME;
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
