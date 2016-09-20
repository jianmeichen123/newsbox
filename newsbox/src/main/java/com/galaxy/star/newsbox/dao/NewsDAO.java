package com.galaxy.star.newsbox.dao;

import java.util.List;
import java.util.Map;

import com.galaxy.star.newsbox.bean.News;

public interface NewsDAO {
	public void addNews(News news);
	public List<News> getNewsList(Map<String,Object> paramMap);
	public News getNewsById(String newId);
	public void updateNews(News news);
	public Integer getNewsListCount(Map<String,Object> paramsMap);
	public void publishNews(Map<String,Object> paramMap);
	public void deleteNews(Map<String,Object> paramMap);
}
