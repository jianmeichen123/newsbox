package com.galaxy.star.newsbox.service.news;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.star.newsbox.bean.NewsBean;
import com.galaxy.star.newsbox.common.exception.BaseServiceException;
import com.galaxy.star.newsbox.dao.NewsDAO;

@Service(value="newsService")
public class NewsServiceImpl implements INewsService{
	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsDAO newDao;
	
	@Override
	public void addNews(NewsBean news) {
		try{
			newDao.addNews(news);
		}catch(Exception e){
			throw new BaseServiceException(e, logger);
		}
	}

	/**
	 * 取得新闻列表
	 */
	public List<NewsBean> getNewsList(Map<String,Object> paramMap){
		try{
			List<NewsBean> list = newDao.getNewsList(paramMap);
			
			return list;
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}
	
	@Override
	public List<NewsBean> getMobileNewsList(Map<String, Object> paramMap) {
		try{
			List<NewsBean> list = newDao.getMobileNewsList(paramMap);
			
			return list;
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}

	@Override
	public Integer getNewsListCount(Map<String, Object> paramsMap) {
		Integer count = 0;
		try{
			count = newDao.getNewsListCount(paramsMap);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
		return count;
	}
	
	@Override
	public Integer getMobileNewsListCount(Map<String, Object> paramsMap) {
		Integer count = 0;
		try{
			count = newDao.getMobileNewsListCount(paramsMap);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
		return count;
	}

	@Override
	public NewsBean getNewsById(String newId) {
		try{
			return newDao.getNewsById(newId);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}

	@Override
	public void updateNews(NewsBean news) {
		try{
			newDao.updateNews(news);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}

	@Override
	public void publishNews(Map<String, Object> paramMap) {
		try{
			newDao.publishNews(paramMap);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}

	@Override
	public void deleteNews(Map<String, Object> paramMap) {
		try{
			newDao.deleteNews(paramMap);
		}catch(Exception e){
			throw new BaseServiceException(e,logger);
		}
	}
	
	
	
	
	
	
	
	
	
}
