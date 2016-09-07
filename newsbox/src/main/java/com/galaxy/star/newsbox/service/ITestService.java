package com.galaxy.star.newsbox.service;

import com.galaxy.star.newsbox.bean.Test;

public interface ITestService {
	Test findById(Integer userId);
	void addTest(Test test);

}
