package com.galaxy.star.newsbox.service.file;

import java.util.List;

import com.galaxy.star.newsbox.bean.FileBean;
import com.galaxy.star.newsbox.bean.PageBean;

public interface IFileService {
	public void addFile(FileBean file);
	public List<FileBean> getFileByPage(PageBean pageBean);
}
