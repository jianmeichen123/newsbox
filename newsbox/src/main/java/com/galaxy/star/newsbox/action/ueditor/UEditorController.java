package com.galaxy.star.newsbox.action.ueditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.galaxy.star.newsbox.bean.PageBean;
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
			    		fileBean.setFilePath(fileDir);
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
	
	/**
	 * 用于展示线上图片功能
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("listimage")
	public Object listImage(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		int start = CUtils.init().Obj2Int(request.getParameter("start"), 0);
		int size = CUtils.init().Obj2Int(request.getParameter("size"), 20);
		
		result.put("state", "FAILURE");
		result.put("list","");
		result.put("start", start);
		result.put("total", 0);
		
		if(size>0){
			PageBean pageBean = new PageBean();
			pageBean.setStart(0);
			pageBean.setPageSize(10);
			List<FileBean> list = fileService.getFileByPage(pageBean);
			
			List<Map<String,String>> urlList = new ArrayList<Map<String,String>>();
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map<String,String> urlMap = new HashMap<String,String>();
					urlMap.put("url", Const.HTML_SERVER + "/" + list.get(i).getFilePath() + "/" + list.get(i).getFileName());
					urlList.add(urlMap);
				}
				result.put("state", "SUCCESS");
				result.put("list",urlList);
				result.put("start", start);
				result.put("total", 100);
				
			}
		}
		return result;
	}
	
	/**
	 * 用于抓取图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="catchImage")
	public Object catchImage(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		String[] sources = request.getParameterValues("source[]");
		
		result.put("state", "FAILURE");
		result.put("list","");
		List<Map<String,Object>> list = null; 
		String fileName;
		
		if(sources!=null && sources.length>0){
			try{
				File imgDir = new File(Const.getUECatchImagePath(request));
				if(!imgDir.exists()){
					imgDir.mkdirs();
				}
				
				list = new ArrayList<Map<String,Object>>();
				byte[] bs = new byte[1024]; 
				int len;
				for(int i=0;i<sources.length;i++){
					URL url = new URL(sources[i]);
					URLConnection con = url.openConnection();
					con.setConnectTimeout(5*1000);
					InputStream is = con.getInputStream();
					fileName = CUtils.init().getUUID() + sources[i].substring(sources[i].lastIndexOf("."), sources[i].length());
					
					OutputStream os = new FileOutputStream(imgDir.getPath() + File.separator + fileName);  
			        while((len = is.read(bs))!=-1) {  
			        	os.write(bs, 0, len);  
			        }  
			        os.close();  
			        is.close();
					
			        Map<String,Object> map = new HashMap<String,Object>();
			        map.put("url", Const.HTML_SERVER + "/" + Const.IMG_CATCH_DIR_NAME + "/" + fileName);
			        map.put("source", sources[i]);
			        map.put("state", "SUCCESS");
			       
			        list.add(map);
				}
			}catch(Exception e){
				logger.error("抓取远程图片出错",e);
			}
		}
		
		if(list!=null){
			result.put("state", "SUCCESS");
			result.put("list",list);
		}
		
		return result;
	}

	
	
	
	
	
	

}