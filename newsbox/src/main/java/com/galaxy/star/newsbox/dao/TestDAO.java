package com.galaxy.star.newsbox.dao;

import org.springframework.dao.DataAccessException;

import com.galaxy.star.newsbox.bean.Test;

public interface TestDAO {

	public Test findById(int id) throws DataAccessException;
	public void addTest(Test test);
}