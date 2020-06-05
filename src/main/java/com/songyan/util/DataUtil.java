package com.songyan.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author songyan
 * @date 2020年5月8日 下午7:10:31
 * @desc 数据操作工具类
 */
public class DataUtil {

	/**
	 * 字符串转整型
	 * @param numbreStr
	 * @param defaultValue
	 * @return 出错时使用默认值
	 */
	public static int parseInt(String numbreStr, int defaultValue) {
		int result = defaultValue;
		try {
			result = Integer.parseInt(numbreStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 对象转String
	 * @param cpuRate
	 * @return
	 */
	public static String parseToString(Object obj,Object defaultValue) {
		if(obj!=null){
			try {
				return JSONObject.toJSONString(obj);	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSONObject.toJSONString(defaultValue);
	}
	
	/**
	 * 实体类转String
	 * @param cpuRate
	 * @return
	 */
	public static String parseToString(Object obj) {
		if(obj!=null){
			try {
				return JSONObject.toJSONString(obj);	
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		return null;
	}
	
	/**
	 * String转实体类
	 * @param node
	 * @param attributeName
	 * @param clazz
	 * @return
	 */
	public static <T> T parseToObject(String value,Class<T> clazz) {
		try {
			return JSONObject.parseObject(value, clazz);
		} catch (Exception e) {
			return null;
		}
	}
}
