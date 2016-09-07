package com.galaxy.star.newsbox.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

public class BeanUtils {
	private static BeanUtils beanUtils = null;
	
	public static synchronized BeanUtils get(){
		if(beanUtils!=null){
			beanUtils = new BeanUtils();
		}
		return beanUtils;
	}
	
	/**
	 * 取得泛型类型
	 * @param clazz
	 * @return
	 */
	public Class<?> getGenerialClass(Class<?> clazz){
		Type type = clazz.getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Type[] p = ((ParameterizedType)type).getActualTypeArguments();
			return ((Class<?>) p[0]);
		}
		return null;
	}
	
	/**
	 * 将Bean对象转换成Map对象，将忽略掉值为null或size=0的属性
	 * 
	 * @param obj
	 *            对象
	 * @return 若给定对象为null则返回size=0的map对象
	 */
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// 转换时会将类名也转换成属性，此处去掉
			if (value != null && !name.equals("class")) {
				map.put(name, value);
			}
		}
		return map;
	}
	
	

}
