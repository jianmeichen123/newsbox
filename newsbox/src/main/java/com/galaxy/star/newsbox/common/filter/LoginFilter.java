package com.galaxy.star.newsbox.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.galaxy.star.newsbox.common.CUtils;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * 登录过滤器，过滤掉非法的请求
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		//HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		
		String url = httpRequest.getServletPath();
		String ext = "";
		String isAllowExt = "css|js|png|jpeg|html|jpg|gif";
		if(url.indexOf(".")>0){
			ext = url.substring(url.lastIndexOf(".")+1, url.length());
		}
		
		//如果系统请求登录页、验证请求或一些静态资源，则放行请求
		if(url.contains("toLogin") || url.contains("login/validate.json") || url.contains("mobile") || (CUtils.init().strIsNotNull(ext) && isAllowExt.indexOf(ext)>=0)){
			chain.doFilter(request, response);
		}else{
			String userName = CUtils.init().Obj2string(session.getAttribute("userName"));
			if(CUtils.init().strIsNull(userName)){
				//跳转
				//httpResponse.sendRedirect("toLogin");
				request.getRequestDispatcher("toLogin").forward(request, response);
			}else{
				chain.doFilter(httpRequest, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}
	
	
	
	
	
	
	

}
