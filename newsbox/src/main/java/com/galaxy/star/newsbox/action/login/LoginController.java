package com.galaxy.star.newsbox.action.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.star.newsbox.common.CUtils;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("validate")
	public Object vaidate(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("error", 1);
		resultMap.put("msg", "验证失败");
		
		try{
			Map<String,Object> paramsMap = CUtils.init().json2map(CUtils.init().getRequestBody(request));
			if(paramsMap!=null && paramsMap.containsKey("userName") && paramsMap.containsKey("userPasswd")){
				String userName = CUtils.init().Obj2string(paramsMap.get("userName"));
				String userPasswd = CUtils.init().Obj2string(paramsMap.get("userPasswd"));
				
				if(CUtils.init().strIsNotNull(userName) && CUtils.init().strIsNotNull(userPasswd)){
					if("angli".equals(userName.trim()) && "xinghe@2016".equals(userPasswd)){
						request.getSession().setAttribute("userName", "angli");
						resultMap.put("error", "0");
						resultMap.put("msg", "登录成功");
					}
				}
			}
			
			
		}catch(Exception e){
			logger.error("登录失败", e);
		}
		
		return resultMap;
	}

}
