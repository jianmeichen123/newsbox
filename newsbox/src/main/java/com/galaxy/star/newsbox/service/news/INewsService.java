package com.galaxy.star.newsbox.service.news;

import java.util.List;

import com.galaxy.star.newsbox.bean.News;

public interface INewsService {
	public void addNews(News news);
	public List<News> getNewsList(int pageNo,int PageSize);
}
