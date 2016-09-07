package com.galaxy.star.newsbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.galaxy.star.newsbox.bean.Test;
import com.galaxy.star.newsbox.common.exception.BaseServiceException;
import com.galaxy.star.newsbox.dao.TestDAO;

@Service(value="testService")
public class TestServiceImpl implements ITestService{
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private TestDAO testDao;

	@Override
	public void addTest(Test test) {
		testDao.addTest(test);
		//int tt = 100/0; 
	}

	@Override
	public Test findById(Integer userId) {
		try{
			return testDao.findById(userId);
		}catch(DataAccessException e){
			throw new BaseServiceException(e,logger);
		}
	}


	
	
	
	

}
