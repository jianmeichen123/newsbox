package com.galaxy.star.newsbox.action.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.star.newsbox.bean.NewsBean;
import com.galaxy.star.newsbox.common.CUtils;
import com.galaxy.star.newsbox.common.Const;
import com.galaxy.star.newsbox.service.news.INewsService;

@Controller
@RequestMapping("/mobile")
public class MobildController {
	private static final Logger logger = LoggerFactory.getLogger(MobildController.class);
	@Autowired
	private INewsService newsService;
	
	@RequestMapping("getNewsList")
	public Object getNewsList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("msg", "");
		resultMap.put("newsList", "");
		
		try{
			Map<String,Object> paramMap = CUtils.init().getRequestMap(request);
			if(paramMap!=null){
				Integer listCount = newsService.getMobileNewsListCount(paramMap);						//总记录数
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
				
				
				List<NewsBean> newsList = newsService.getMobileNewsList(paramMap);		//列表数据
				List<Map<String,Object>> mobileNewsList = null;
				
				if(newsList!=null && newsList.size()>0){
					mobileNewsList = new ArrayList<Map<String,Object>>();
					for(int i=0;i<newsList.size();i++){
						Map<String,Object> map = new HashMap<String,Object>();
						NewsBean newsBean = newsList.get(i);
						map.put("newsId", newsBean.getNewId());
						map.put("newsCaption", newsBean.getNewCaption());
						
						int isShowBigImg = newsBean.getIsShowBigImg();
						map.put("isShowBigImg", isShowBigImg);		//只返回一张图	
						List<Map<String,Object>> listImgArray = new ArrayList<Map<String,Object>>();
						
						String img1 = newsBean.getNewListImg1();
						String img2 = newsBean.getNewListImg2();
						String img3 = newsBean.getNewListImg3();
						
						if(isShowBigImg>=1){	//此时显示为一张大图
							if(CUtils.init().strIsNotNull(img1)){
								Map<String,Object> imgMap = new HashMap<String,Object>();
								imgMap.put("url", img1);
								listImgArray.add(imgMap);
							}
						}else{
							if(CUtils.init().strIsNotNull(img1) && CUtils.init().strIsNotNull(img2) && CUtils.init().strIsNotNull(img3)){
								Map<String,Object> imgMap1 = new HashMap<String,Object>();
								imgMap1.put("url", img1);
								listImgArray.add(imgMap1);
								
								Map<String,Object> imgMap2 = new HashMap<String,Object>();
								imgMap2.put("url", img2);
								listImgArray.add(imgMap2);
								
								Map<String,Object> imgMap3 = new HashMap<String,Object>();
								imgMap3.put("url", img3);
								listImgArray.add(imgMap3);
							}else{
								if(CUtils.init().strIsNotNull(img1)){
									Map<String,Object> imgMap = new HashMap<String,Object>();
									imgMap.put("url", img1);
									listImgArray.add(imgMap);
								}
							}
						}
						map.put("listImgArray", listImgArray);
						
						map.put("createTime", newsBean.getCreateTime());
						map.put("createUser", newsBean.getCreateUser());
						map.put("newsSource", newsBean.getNewSource());
						map.put("newsAthors", newsBean.getNewAthors());
						map.put("newsEditor", newsBean.getNewEditor());
						map.put("newsUrl", Const.getNewsHtml5Url(request, newsBean.getNewHtmlFileName()));
						
						mobileNewsList.add(map);
					}	
					resultMap.put("error", 0);
					if(newsList!=null){
						resultMap.put("newsList", mobileNewsList);
						resultMap.put("listCount", listCount);
					}
				}
			}
		}catch(Exception e){
			logger.error("移动端－获取新闻列表出错！");
		}
		return resultMap;
	}

}
