package com.galaxy.star.newsbox.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController{
	
	/**
	 * 进入主页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/")
	public String toMain(HttpServletRequest request,HttpServletResponse response){
		
		return "main/main_page";
	}




	
	

}
