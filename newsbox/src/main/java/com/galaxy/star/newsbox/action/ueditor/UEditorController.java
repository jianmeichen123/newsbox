package com.galaxy.star.newsbox.action.ueditor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.galaxy.star.newsbox.bean.FileBean;
import com.galaxy.star.newsbox.common.CUtils;
import com.galaxy.star.newsbox.common.Const;
import com.galaxy.star.newsbox.common.DateTools;
import com.galaxy.star.newsbox.service.file.IFileService;

/**
 * 
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UEditorController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IFileService fileService;

	@RequestMapping("/dispatch")
	public void config(HttpServletRequest request, HttpServletResponse response, String action) {
		response.setContentType("application/json");
		String rootPath = request.getSession().getServletContext().getRealPath("/");

		try {
			String exec = new ActionEnter(request, rootPath).exec();
			
			System.out.println(exec);
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传图片-专用UEditor上传图片及视频文件等专用
	 * @param file
	 * @param request
	 * @param model
	 * @return   10.9.11.161
	 */
	@RequestMapping(value = "/uploadImgFile")  
	public Object uploadFileForUE(HttpSession session,@RequestParam(value = "upfile", required = false) MultipartFile[] fileList,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("state", "FAILSE");
		result.put("url","");
		result.put("title", "");
		result.put("original", "");
		
		String fileType = request.getParameter("requestType");
		fileType = fileType==null?"":fileType.trim();
		String fileDir = null;
		String srcPath = null;
		if("image".equals(fileType.trim())){
			srcPath = Const.getUEImagePath(request);
			fileDir = Const.UEDITOR_IMGS.replace(File.separator, "/");
		}else if("video".equals(fileType.trim())){
			srcPath = Const.getUEVideoPath(request);
			fileDir = Const.UEDITOR_VIDEOS.replace(File.separator, "/");
		}else{
			srcPath = Const.getUEOtherPath(request);
			fileDir = Const.UEDITOR_OTHERS.replace(File.separator, "/");
		}
		
		//上传的源图片文件存放的目录
		File uploadDirectory = new File(srcPath); 
		if(!uploadDirectory.exists()){
			uploadDirectory.mkdirs();
		}
		
		if(fileList!=null && fileList.length>0){
			for(int i=0;i<fileList.length;i++){
				if(fileList[i]!=null && !fileList[i].isEmpty()){
					String fileName = fileList[i].getOriginalFilename();  
			        File targetFile = new File(srcPath, fileName);  
			        try{  
			        	String urlPath = Const.HTML_SERVER + "/" + fileDir + "/";
			        	fileList[i].transferTo(targetFile); 
			    		result.put("state", "SUCCESS");
			    		result.put("url",urlPath + fileName);
			    		result.put("title", fileName);
			    		result.put("original", fileName);
			    		
			    		FileBean fileBean = new FileBean();
			    		fileBean.setFileId(CUtils.init().getUUID());
			    		fileBean.setFileName(fileName);
			    		fileBean.setFileType("image");
			    		fileBean.setFilePath(urlPath);
			    		fileBean.setFileSize(fileList[i].getSize());
			    		fileBean.setFileSource("ue");
			    		fileBean.setFileUrl(urlPath + fileName);
			    		fileBean.setCreateUser("liang");
			    		fileBean.setCreateTime(DateTools.get().getCurrentDateTime());
			    		fileService.addFile(fileBean);
			        	
			        }catch (Exception e) {  
			           	logger.error("文件上传或保存失败！",e);
			        }  
				}
			}
		}
		return result;  
	}
	
	@RequestMapping("listimage")
	public Object listImage(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		
		System.out.println("tdjgamtam");
		
		
		return map;
	}
	
	
	
	
	
	
	

}