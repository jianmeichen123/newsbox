package com.galaxy.star.newsbox.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class CUtils {
	private static CUtils utils;
	
	public static synchronized CUtils init(){
		if(utils==null){
			utils = new CUtils();
		}
		return utils;
	}
	
	
	/**
	 * android端使用application/json的方式传输参数，此处进行特殊处理进行接收
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public String getRequestBody(HttpServletRequest request){
		String paramStrng = "";
		try{
			BufferedReader reader = request.getReader();
			String input = null;
			StringBuffer requestBody = new StringBuffer();
			while((input = reader.readLine()) != null) {
				requestBody.append(input);
			}
			reader.close();
			reader = null;
			paramStrng = requestBody.toString();
		}catch(Exception e){
			paramStrng = "";
		}
		return paramStrng;
	}
	
	
	/**
	 * android端使用application/json的方式传输参数，此处进行特殊处理进行接收
	 * @param req
	 * @return
	 * @throws IOException
	 */
	public Map<String,Object> getRequestMap(HttpServletRequest request){
		String jsonString = getRequestBody(request);
		if(CUtils.init().strIsNotNull(jsonString)){
			return json2map(jsonString);
		}else{
			return null;
		}
	}
	
	/**
	 * 将json转换为map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> json2map(Object obj){
		Map<String,Object> map = null;
		if(obj!=null){
			ObjectMapper mapper = new ObjectMapper();
			try{
				map = mapper.readValue(this.Obj2string(obj), Map.class);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 将map转换为json字符串
	 */
	public String object2jsonString(Object obj){
		String result = null;
		try{
			if(strIsNotNull(obj)){
				Gson gson = new Gson(); 
				result = gson.toJson(obj); 
			}
		}catch(Exception e){
		}
		return result;
	}
	
	/**
	 * 将对象转换为字符串
	 * @param obj
	 * @return
	 */
	public String Obj2string(Object obj,String... defValue){
		String resultValue = null;
		if(defValue!=null && defValue.length>0){
			resultValue = defValue[0];
		}
		try{
			resultValue = obj.toString().trim();
		}catch(Exception e){
		}
		return resultValue;
	}
	
	/**
	 * 将对象转换为整数
	 * @param obj
	 * @return
	 */
	public Integer Obj2Int(Object obj,Integer... defValue){
		Integer resInt = null;
		if(defValue!=null && defValue.length>0){
			resInt = defValue[0];
		}
		
		try{
			resInt = Integer.valueOf(Obj2string(obj));
		}catch(Exception e){
		}
		return resInt;
	}
	
	/**
	 * 取得UUID
	 */
	public String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public boolean strIsNull(Object obj){
		if(obj==null || "".equals(obj.toString().trim())){
			return true;
		}
		return false;
	}
	
	public boolean strIsNotNull(Object obj){
		return !strIsNull(obj);
	}

}
