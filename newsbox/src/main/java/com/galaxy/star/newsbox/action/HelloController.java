package com.galaxy.star.newsbox.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.star.newsbox.common.Const;
import com.galaxy.star.newsbox.service.ITestService;

@Controller
@RequestMapping("/hello")
public class HelloController{
	
	private ITestService testService;
	
	@RequestMapping("qiao")
	public Object sayHello(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			
			
			
			result.put("state", "SUCCESS");
    		result.put("url",Const.getHtmlServer(request) + "/" + Const.UEDITOR_IMGS + "/" + "文件名");
    		result.put("title", "文件名");
    		result.put("original", "文件名");
			
		
		}catch(Exception e){
		}
		return result;
	}

	public ITestService getTestService() {
		return testService;
	}

	@Resource(name="testService")
	public void setTestService(ITestService testService) {
		this.testService = testService;
	}


	
	

}
