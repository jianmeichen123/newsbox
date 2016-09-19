package com.galaxy.star.newsbox.service.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.star.newsbox.bean.FileBean;
import com.galaxy.star.newsbox.bean.PageBean;
import com.galaxy.star.newsbox.common.exception.BaseServiceException;
import com.galaxy.star.newsbox.dao.FileDAO;

@Service(value="fileService")
public class FileServiceImpl implements IFileService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
	private FileDAO fileDao;
	
	@Override
	public void addFile(FileBean file) {
		try{
			fileDao.addFile(file);
		}catch(Exception e){
			throw new BaseServiceException(e, logger);
		}
	}
	
	@Override
	public List<FileBean> getFileByPage(PageBean pageBean){
		List<FileBean> list = null;
		try{
			list = fileDao.getFileByPage(pageBean);
		}catch(Exception e){
			throw new BaseServiceException(e, logger);
		}
		return list;
	}

}
