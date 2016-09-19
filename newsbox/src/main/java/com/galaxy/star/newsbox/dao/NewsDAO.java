package com.galaxy.star.newsbox.dao;

import java.util.List;

import com.galaxy.star.newsbox.bean.News;
import com.galaxy.star.newsbox.bean.PageBean;

public interface NewsDAO {
	public void addNews(News news);
	public List<News> getNewsList(PageBean pageBean);
}
