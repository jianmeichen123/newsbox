package com.galaxy.star.newsbox.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.star.newsbox.common.CUtils;

@Controller
public class MainController{
	
	/**
	 * 判断是否进入首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/")
	public String toMain(HttpServletRequest request,HttpServletResponse response){
		
		String userName = CUtils.init().Obj2string(request.getSession().getAttribute("userName"));
		if(userName==null || "".equals(userName)){
			return "login/login";
		}else{
			return "main/main_page";
		}
	}

	/**
	 * 进入主页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toMain")
	public String toMainPage(HttpServletRequest request,HttpServletResponse response){
		return "main/main_page";
	}

	
	/**
	 * 进入登录页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toLogin")
	public String toLoginPage(HttpServletRequest request,HttpServletResponse response){
		return "login/login";
	}
	
	

}
