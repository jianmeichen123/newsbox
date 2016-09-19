package com.galaxy.star.newsbox.dao;

import java.util.List;

import com.galaxy.star.newsbox.bean.FileBean;
import com.galaxy.star.newsbox.bean.PageBean;

public interface FileDAO {
	public void addFile(FileBean file);
	public List<FileBean> getFileByPage(PageBean pageBean);
}
