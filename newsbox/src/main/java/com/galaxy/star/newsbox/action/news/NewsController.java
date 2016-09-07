package com.galaxy.star.newsbox.action.news;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.star.newsbox.bean.ImageRect;
import com.galaxy.star.newsbox.common.CUtils;
import com.galaxy.star.newsbox.common.Const;
import com.galaxy.star.newsbox.common.ImageUtils2;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@RequestMapping("toAddNewPage")
	public String toAddNewPage(){
		return "news/add_news/news_add";
	}
	
	/**
	 * 上传图片
	 * @param file
	 * @param request
	 * @param model
	 * @return   10.9.11.161
	 */
	@RequestMapping(value = "uploadFile")  
	public Map<String,Object> uploadFile(HttpSession session,@RequestParam(value = "tdjgamtam", required = false) MultipartFile[] fileList,HttpServletRequest request, HttpServletResponse response) {
		String path = Const.IMG_SRC_DIR;
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("error", 1);
		result.put("msg","上传失败");
		
		File uploadDirectory = new File(path); 
		if(!uploadDirectory.exists()){
			uploadDirectory.mkdirs();
		}
		
		if(fileList!=null && fileList.length>0){
			for(int i=0;i<fileList.length;i++){
				if(fileList[i]!=null && !fileList[i].isEmpty()){
					String fileName = fileList[i].getOriginalFilename();  
			        File targetFile = new File(path, fileName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }else{
			        	targetFile.delete();
			        }
			        try{  
			        	fileList[i].transferTo(targetFile); 
			        	cutImg(request,fileName);
			        	
			        	result.put("error", 0);
						result.put("msg","上传成功");
			            //properties.replace("app."+appName+".version", appVersion);
			        }catch (Exception e) {  
			            e.printStackTrace();  
			        }  
			        //model.addAttribute("fileUrl", path + File.separator + fileName);  
				}
			}
			
		}
		return result;  

	}
	
	//裁剪图片
    private void cutImg(HttpServletRequest request,String fileName) {
        //得到坐标
    	int x1 = CUtils.init().Obj2Int(request.getParameter("x1"),-1);
		int y1 = CUtils.init().Obj2Int(request.getParameter("y1"),-1);
		int x2 = CUtils.init().Obj2Int(request.getParameter("x2"),-1);
		int y2 = CUtils.init().Obj2Int(request.getParameter("y2"),-1);
		
		//取得源图片所在img的宽度，用于解决选择组件与真实图片的选择误 差
		int imgScaleWidth = CUtils.init().Obj2Int(request.getParameter("imgWidth"),-1);
		int imgScaleHeight = CUtils.init().Obj2Int(request.getParameter("imgHeight"),-1);
		ImageRect srcRect = new ImageRect();
		srcRect.setX1(x1);
		srcRect.setY1(y1);
		srcRect.setX2(x2);
		srcRect.setY2(y2);
		srcRect.setWidth(imgScaleWidth);
		srcRect.setHeight(imgScaleHeight);
		
		
        String path = Const.IMG_SRC_DIR + File.separator + fileName;					//原图片路径
        String cutFileName = Const.IMG_DEAL_DIR + File.separator + "cut_" + fileName;	//剪切后图片路径
        
        ImageUtils2 imgUtils = new ImageUtils2();
        imgUtils.cutImage(path,cutFileName, srcRect);
    }
	

}
