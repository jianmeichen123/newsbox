package com.galaxy.star.newsbox.action.news;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.star.newsbox.bean.ImageRect;
import com.galaxy.star.newsbox.bean.NewsBean;
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
	
	/**
	 * 跳转到新增新闻页
	 * @return
	 */
	@RequestMapping("toAddNewPage")
	public String toAddNewPage(){
		return "news/add_news/news_add";
	}
	
	/**
	 * 跳转到新闻列表页
	 */
	@RequestMapping(value = "toListPage")  
	public String toListPage(HttpServletRequest request,HttpServletResponse response,Model model){
		
		String pageNo = CUtils.init().Obj2string(request.getParameter("pageNo"), "1");
		String newCaption = CUtils.init().Obj2string(request.getParameter("newCaption"), "");
		String newKeyWord = CUtils.init().Obj2string(request.getParameter("newKeyWord"), "");
		String createTimeStart = CUtils.init().Obj2string(request.getParameter("createTimeStart"), "");
		String datepicker_end = CUtils.init().Obj2string(request.getParameter("datepicker_end"), "");
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pageNo", pageNo);
		paramMap.put("newCaption", newCaption);
		paramMap.put("newKeyWord", newKeyWord);
		paramMap.put("createTimeStart", createTimeStart);
		paramMap.put("datepicker_end", datepicker_end);
		
		model.addAttribute("page", paramMap);
	
		return "news/list/news_list";
	}
	
	/**
	 * 发布或取消新闻
	 */
	@RequestMapping(value = "publishNews")  
	public Object publishNews(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("msg","发布失败");
		
		try{
			Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
			if(paramMap!=null && paramMap.containsKey("newIds") && paramMap.containsKey("type")){
				String newidJsonString = CUtils.init().Obj2string(paramMap.get("newIds"));
				String type = CUtils.init().Obj2string(paramMap.get("type"));
				List<String> newList = new ArrayList<String>();
				
				JSONArray arr = null;
				if(CUtils.init().strIsNotNull(newidJsonString)){
					arr = new JSONArray(newidJsonString);
					if(arr!=null && arr.length()>0){
						for(int i=0;i<arr.length();i++){
							newList.add(arr.getString(i));
						}
					}
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("newIds", newList);
				//判断是发布新闻还是取消发布新闻
				if("publish".equals(type)){
					map.put("isPublish", 1);
				}else{
					map.put("isPublish", 0);
				}
				map.put("publishTime", DateTools.get().getCurrentDateTime());
				newsService.publishNews(map);
				
//				if("publish".equals(type)){
//					if(arr!=null && arr.length()>0){
//						for(int i=0;i<arr.length();i++){
//							NewsBean newsBean = newsService.getNewsById(arr.getString(i));
//							createHtml5(request,newsBean);
//						}
//					}
//				}
				
				resultMap.put("error", 0);
			}
		}catch(Exception e){
			logger.error("发布或取消发布失败",e);
		}
		return resultMap;
	}
	
//	/**
//	 * 取消发布新闻
//	 */
//	@RequestMapping(value = "cancelPublishNews")  
//	public Object cancelPublishNews(HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> resultMap = new HashMap<String,Object>();
//		resultMap.put("error", 1);
//		resultMap.put("msg","取消发布失败");
//		
//		try{
//			Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
//			if(paramMap!=null && paramMap.containsKey("newId")){
//				paramMap.put("isPublish", 0);
//				newsService.publishNews(paramMap);
//				resultMap.put("error", 0);
//			}
//		}catch(Exception e){
//			logger.error("取消发布失败",e);
//		}
//		return resultMap;
//	}
	
	/**
	 * 删除新闻
	 */
	@RequestMapping(value = "deleteNews")  
	public Object deleteNews(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("msg","删除失败");
		
		try{
			Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
			if(paramMap!=null && paramMap.containsKey("newId")){
				paramMap.put("isDel", 1);
				newsService.deleteNews(paramMap);
				resultMap.put("error", 0);
			}
		}catch(Exception e){
			logger.error("删除失败",e);
		}
		return resultMap;
	}
	
	/**
	 * 用于保存并获取查询条件
	 */
	@RequestMapping("saveAndGetFind")
	public Object saveAndGetFind(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("finds", "");
		
		if(paramMap!=null && paramMap.containsKey("type")){
			String type = CUtils.init().Obj2string(paramMap.get("type"));
			HttpSession session = request.getSession();
			if(CUtils.init().strIsNotNull(type)){
				if("save".equals(type)){
					session.setAttribute("finds", CUtils.init().object2jsonString(paramMap));
					resultMap.put("error", 0);
				}else if("get".equals(type)){
					String findString = CUtils.init().Obj2string(session.getAttribute("finds"));
					if(CUtils.init().strIsNotNull(findString)){
						resultMap.put("error", 0);
						resultMap.put("finds", CUtils.init().json2map(findString));
					}
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * 跳转到编辑页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toEditNewPage")  
	public String toModify(HttpServletRequest request,HttpServletResponse response,Model model){
		try{
			String newId = CUtils.init().Obj2string(request.getParameter("newId"));
			//newId = "ac5ec686545c4f0da2803747faea4a6b";
			if(CUtils.init().strIsNotNull(newId)){
				NewsBean news = newsService.getNewsById(newId);
				model.addAttribute("news", news);
			}
		}catch(Exception e){
			logger.error("取新闻出错",e);
		}
		
		return "news/add/news_add";
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
		
		Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
		
		try{
			Integer listCount = newsService.getNewsListCount(paramMap);								//总记录数
			Integer pageSize = CUtils.init().Obj2Int(paramMap.get("pageSize"), Const.PAGE_SIZE);	//每页总记录数
			paramMap.put("pageSize", pageSize);
			Integer pageNo = CUtils.init().Obj2Int(paramMap.get("pageNo"),1);						//当前页码
			
			int totalPage = (int)Math.ceil(listCount*1.0/pageSize);
			if(pageNo>totalPage){
				pageNo = totalPage;
			}
			if(pageNo<=0) pageNo = 1;
			
			int start = (pageNo-1)*pageSize;
			paramMap.put("pageNo", start);
			
			
			List<NewsBean> newsList = newsService.getNewsList(paramMap);		//列表数据
			
			//System.out.println(newsList.size());
			
			resultMap.put("error", 0);
			if(newsList!=null){
				resultMap.put("newsList", newsList);
				resultMap.put("listCount", listCount);
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
	public Map<String,Object> saveNews(HttpServletRequest request, HttpServletResponse response,@RequestBody NewsBean news) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("error", 1);
		result.put("msg","保存资讯失败！");

		try{
			news.setCreateTime(DateTools.get().getCurrentDateTime());	//创建时间
			news.setCreateUser("angli");		//登录用户
			
			//将富文本生成对应的html5页面
			createHtml5(request,news);
//			if(html5Url!=null && !"".equals(html5Url.trim())){
//				news.setNewUrl(html5Url);
//			}
			
			if(CUtils.init().strIsNotNull(news.getNewId())){
				//更新
				newsService.updateNews(news);
			}else{
				//新增
				news.setNewId(CUtils.init().getUUID());		//设置ID
				news.setIsPublish(0);						//是否发布 0：不发布
				news.setIsDel(0);							//删除标志
				newsService.addNews(news);
			}
			
			result.put("error", 0);
			result.put("msg","保存资讯成功！");
			
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
					String yuanFileName = fileList[i].getOriginalFilename(); 
					String fileName = "new_list_" + CUtils.init().getUUID() + yuanFileName.substring(yuanFileName.lastIndexOf("."), yuanFileName.length());
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
			        		result.put("img_src", Const.getHtmlServer(request) + "/" + Const.IMG_SRC_DIR_NAME + "/" + fileName);
			        	}else{
			        		result.put("img_src", Const.getHtmlServer(request) + "/" + Const.IMG_DEAL_DIR_NAME + "/" + Const.CUT_FILE_PRE + fileName);
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
	private void createHtml5(HttpServletRequest request,NewsBean newsBean){
		//String htmlUrl = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE html>\r\n")
		.append("<html>\n")
		.append("<head>\n")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\n")
		.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n")												//用于字体自适应
		.append("<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;\" name=\"viewport\" />\n") 		//取消缩放
		.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + Const.getCssPath(request) + "\" />\n")				//引入公共样式
		//.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+Const.getHtmlServer(request)+"/common/js/jquery-1.12.3.js\"></script>\n")	//引入jquery
		.append("</head>\n")
		.append("<body>\n")
		//拼入标题
		
//		<h1 class="h1_new_caption" style="margin-bottom: 0;">辣手摧花优步后，原来滴滴去做婚恋市场了</h1>
//<span class="p_editor_create_time">2016-10-09 16:08:47&nbsp;&nbsp;<span>
		.append("<h1 class=\"h1_new_caption\">"+newsBean.getNewCaption()+"</h1>\n");

		String createTime = newsBean.getCreateTime();
		String changeTime = "";
		if(createTime!=null){
			changeTime = createTime.substring(0,createTime.indexOf(" ")+1).replace("-", "/");
		}
		
		sb.append("<p class=\"p_editor_create_time\">");
		
		if(CUtils.init().strIsNotNull(newsBean.getNewSource())){
			sb.append("<span style=\"margin-right: 40px;\">来源：" + newsBean.getNewSource() + "</span>");			//newsBean.getNewAthors() 
		}
		
		sb.append("<span>"
				+ changeTime
				+ "</span></p>\n\n");
			
		
		
		
		
//		//来源
//		if(CUtils.init().strIsNotNull(newsBean.getNewAthors())){
//			sb.append("<a href=\""+newsBean.getNewAthors()+"\" target=\"_blank\">"+
//				newsBean.getNewSource()+"</a>");
//		}
//			
//		//责任编辑
//		if(CUtils.init().strIsNotNull(newsBean.getNewEditor())){
//			sb.append("责任编辑："+newsBean.getNewEditor());
//		}
//		sb.append("</span>\n");
				
		sb.append(newsBean.getNewContent())
		.append("\n")
		//.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+Const.getHtmlServer(request)+"/common/js/app_common.js\"></script>\n")		//引入公共脚本
		.append("</body>\n")
		.append("</html>\n");
		
		String htmlFilePath = Const.getFilePath(request) + File.separator + Const.HTML5_DIR_NAME;
		File htmlFileDir = new File(htmlFilePath);
		if(!htmlFileDir.exists()){
			htmlFileDir.mkdirs();
		}
		
		//判断htmlFileName是否存在，如果不存在说明是新增
		if(CUtils.init().strIsNull(newsBean.getNewHtmlFileName())){
			newsBean.setNewHtmlFileName("html_" + CUtils.init().getUUID() + ".html");
		}
		
		File htmlFile = new File(htmlFilePath + File.separator + newsBean.getNewHtmlFileName());
		try{
			/*BufferedWriter writer = new BufferedWriter 
					(new OutputStreamWriter (new FileOutputStream (htmlFile,true),"UTF-8"));
			writer.write(sb.toString());
			writer.flush();
			writer.close();*/
			FileWriter fw = new FileWriter(htmlFile);
			fw.write(sb.toString());
			fw.flush();fw.close();
			
			//htmlUrl = Const.getHtmlServer(request) + "/" + Const.HTML5_DIR_NAME + "/" + newsBean.getNewHtmlFileName();
		}catch(Exception e){
		}
		
		//return htmlUrl;
	}

	

}
