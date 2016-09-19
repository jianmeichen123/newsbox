package com.galaxy.star.newsbox.service.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.star.newsbox.bean.News;
import com.galaxy.star.newsbox.bean.PageBean;
import com.galaxy.star.newsbox.common.exception.BaseServiceException;
import com.galaxy.star.newsbox.dao.NewsDAO;

@Service(value="newsService")
public class NewsServiceImpl implements INewsService{
	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsDAO newDao;
	
	@Override
	public void addNews(News news) {
		try{
			newDao.addNews(news);
		}catch(Exception e){
			throw new BaseServiceException(e, logger);
		}
	}

	/**
	 * 取得新闻列表
	 */
	public List<News> getNewsList(int pageNo,int PageSize){
		try{
			PageBean pageBean = new PageBean(0,10);
			List<News> list = newDao.getNewsList(pageBean);
			
			return list;
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}
}
