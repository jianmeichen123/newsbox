package com.galaxy.star.newsbox.action.news;

import java.io.File;
import java.io.FileWriter;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.star.newsbox.bean.ImageRect;
import com.galaxy.star.newsbox.bean.News;
import com.galaxy.star.newsbox.common.CUtils;
import com.galaxy.star.newsbox.common.Const;
import com.galaxy.star.newsbox.common.DateTools;
import com.galaxy.star.newsbox.common.ImageUtils2;
import com.galaxy.star.newsbox.service.news.INewsService;
import com.galaxy.star.newsbox.service.news.NewsServiceImpl;

@Controller
@RequestMapping("/news")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private INewsService newsService;
	
	@RequestMapping("toAddNewPage")
	public String toAddNewPage(){
		return "news/add_news/news_add";
	}
	
	/**
	 * 取得新闻列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getNewsList")  
	public Map<String,Object> getNewsList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("msg","取得新闻列表失败");
		resultMap.put("newsList", "");
		
		try{
			List<News> newsList = newsService.getNewsList(0, 10);
			resultMap.put("error", 0);
			if(newsList!=null){
				resultMap.put("newsList", newsList);
			}
		}catch(Exception e){
			logger.error("取得新闻列表失败",e);
		}
		return resultMap;
	}
	
	
	
	/**
	 * 保存新闻稿
	 * 
	 */
	@RequestMapping(value = "saveNews")  
	public Map<String,Object> uploadFile(HttpServletRequest request, HttpServletResponse response,@RequestBody News news) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("error", 1);
		result.put("msg","保存资讯失败！");
		
		try{
			news.setNewId(CUtils.init().getUUID());						//设置ID
			news.setCreateTime(DateTools.get().getCurrentDateTime());	//创建时间
			news.setCreateUser("angli");		//登录用户
			news.setIsPublish(0);				//是否发布 0：不发布
			news.setNewType("1");				//设置资讯类型（1：对应dics表的要闻）
			
			//将富文本生成对应的html5页面
			String html5Url = createHtml5(news.getNewId(),news.getNewContent());
			
			if(html5Url!=null && !"".equals(html5Url.trim())){
				news.setNewUrl(html5Url);
				
				newsService.addNews(news);
				
				result.put("error", 0);
				result.put("msg","保存资讯成功！");
			}
		}catch(Exception e){
			logger.error("保存新闻失败", e);
		}
		
		return result;
	}
	
	/**
	 * 上传图片-用于上传自定义的列表图片等
	 * @param file
	 * @param request
	 * @param model
	 * @return   10.9.11.161
	 */
	@RequestMapping(value = "uploadFile")  
	public Map<String,Object> uploadFile(HttpSession session,@RequestParam(value = "tdjgamtam", required = false) MultipartFile[] fileList,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("error", 1);
		result.put("msg","上传失败");
		
		String srcPath = Const.getImgSrcPath(request);
		String dealPath = Const.getImgDealPath(request);
		
		//上传的源图片文件存放的目录
		File uploadDirectory = new File(srcPath); 
		if(!uploadDirectory.exists()){
			uploadDirectory.mkdirs();
		}
		//裁剪处理后的图片存放目录
		File dealDirectory = new File(dealPath); 
		if(!dealDirectory.exists()){
			dealDirectory.mkdirs();
		}
		
		if(fileList!=null && fileList.length>0){
			for(int i=0;i<fileList.length;i++){
				if(fileList[i]!=null && !fileList[i].isEmpty()){
					String fileName = fileList[i].getOriginalFilename();  
			        File targetFile = new File(srcPath, fileName);  
			        if(!targetFile.exists()){  
			            targetFile.mkdirs();  
			        }else{
			        	targetFile.delete();
			        }
			        try{  
			        	fileList[i].transferTo(targetFile); 
			        	boolean flag = cutImg(request,fileName,srcPath,dealPath);
			        	
			        	//如果存在裁剪的图片则返回该图片，如果没有裁剪的图片则返回原图片
			        	if(!flag){	
			        		result.put("img_src", Const.HTML_SERVER + "/" + Const.IMG_SRC_DIR_NAME + "/" + fileName);
			        	}else{
			        		result.put("img_src", Const.HTML_SERVER + "/" + Const.IMG_DEAL_DIR_NAME + "/" + Const.CUT_FILE_PRE + fileName);
			        	}
			        	
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
	
	
	/**
	 * 裁剪图片
	 * @param request
	 * @param fileName
	 */
    private boolean cutImg(HttpServletRequest request,String fileName,String imgSrcPath,String imgDealPath) {
        //得到坐标
    	int x1 = CUtils.init().Obj2Int(request.getParameter("x1"),-1);
		int y1 = CUtils.init().Obj2Int(request.getParameter("y1"),-1);
		int x2 = CUtils.init().Obj2Int(request.getParameter("x2"),-1);
		int y2 = CUtils.init().Obj2Int(request.getParameter("y2"),-1);
		
		boolean flag = false;
		
		if(x1>=0 && x2>0 && y1>=0 && y2>0){
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
			
	        String imgSrcFileName = imgSrcPath + File.separator + fileName;					//原图片路径
	        String imgDealFileName = imgDealPath + File.separator + "cut_" + fileName;		//剪切后图片路径
	        
	        ImageUtils2 imgUtils = new ImageUtils2();
	        flag = imgUtils.cutImage(imgSrcFileName,imgDealFileName, srcRect);
		}
		
		return flag;
    }
    
	/**
	 * 将富文本生成相应的html5页面
	 */
	private String createHtml5(String newsId,String newsContent){
		String htmlUrl = null;
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\r\n");
		sb.append("<html>\r\n");
		sb.append("<head>\r\n");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		sb.append(newsContent);
		sb.append("</body>\r\n");
		sb.append("</html>\r\n");
		
		String htmlFilePath = Const.FILE_PATH + File.separator + Const.HTML5_DIR_NAME;
		File htmlFileDir = new File(htmlFilePath);
		if(!htmlFileDir.exists()){
			htmlFileDir.mkdirs();
		}
		
		File htmlFile = new File(htmlFilePath + File.separator + newsId + ".html");
		try{
			FileWriter fw = new FileWriter(htmlFile);
			fw.write(sb.toString());
			fw.flush();fw.close();
			
			htmlUrl = Const.HTML_SERVER + "/" + Const.HTML5_DIR_NAME + "/" + newsId + ".html";
		}catch(Exception e){
		}
		
		return htmlUrl;
	}

	

}
