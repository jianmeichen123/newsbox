package com.galaxy.star.newsbox.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class EncodingFilter implements Filter{
	private String encoding = "utf-8";
	private boolean forceEncoding = true;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		if(this.forceEncoding){
			request.setCharacterEncoding(this.encoding);
			response.setContentType("text/html;charset=" + this.encoding);
			response.setCharacterEncoding(this.encoding);
			
			
		}
		
		/**
		 * 页面缓存设定
		 * 确保浏览器不缓存页面数据
		 */
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", -10);
		response.setHeader("Pragma", "no-cache");
		
		arg2.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		WebApplicationContext webapp = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		//Util.get().setApplicationContext(webapp);
		Const.context = webapp;
		
		String R_forceEncoding = config.getInitParameter("forceEncoding");
		String R_encoding = config.getInitParameter("encoding");
		if(null != R_forceEncoding && !"".equals(R_forceEncoding)){
			this.forceEncoding = Boolean.valueOf(R_forceEncoding);
		}
		
		if(null != R_encoding && !"".equals(R_encoding)){
			this.encoding = R_encoding;
		}
		
		
	}


//
//	@Override
//	protected void doFilterInternal(
//			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//			request.setCharacterEncoding(this.encoding);
//				response.setCharacterEncoding(this.encoding);
//				response.setContentType("text/html;charset=" + this.encoding);
//				request.setCharacterEncoding("utf-8");
//
//		filterChain.doFilter(request, response);
//	}
	
}
