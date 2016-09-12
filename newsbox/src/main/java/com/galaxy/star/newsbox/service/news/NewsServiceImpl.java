package com.galaxy.star.newsbox.service.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.star.newsbox.bean.News;
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

}
