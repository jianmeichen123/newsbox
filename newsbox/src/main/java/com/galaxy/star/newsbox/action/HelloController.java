package com.galaxy.star.newsbox.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.star.newsbox.bean.Test;
import com.galaxy.star.newsbox.service.ITestService;

@Controller
@RequestMapping("/hello")
public class HelloController{
	
	private ITestService testService;
	
	@RequestMapping("qiao")
	public void sayHello(HttpServletRequest request,HttpServletResponse response,PrintWriter out){
		try{
			
//			
//			Test user = new Test();
//			user.setUserId(1);
//			user.setUserName("乔乔");         
//			testService.addTest(user);
			
			Test user1 = testService.findById(20);
			//-XX:MaxPermSize=1024M
			
			out.print(user1.getUserName() + "___");
			

			
			
		
		}catch(Exception e){
		}
		
	}

	public ITestService getTestService() {
		return testService;
	}

	@Resource(name="testService")
	public void setTestService(ITestService testService) {
		this.testService = testService;
	}


	
	

}
