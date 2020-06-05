package com.songyan.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** 
* @author songyan
* @date 2020年3月22日 
* @desc 数据校验类
*/
public final class Validate {
	/**
	 * 处理数据中的空值
	 * */
	public static String NullValueToEmpty(Object obj){
		return obj == null ? "" : obj.toString();
	}
	
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().toUpperCase().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 */
	public static boolean isNullOrEmpty(String strs){
		return strs == null || "".equals(strs.trim()) ? true : false ;
	}
	/**
	 * 自动保留两位小数点
	 * @param obj
	 * @return
	 */
	public static String addPoint(String obj){
		DecimalFormat df= new DecimalFormat("#######0.00");
		return isNullOrEmpty(obj) ? "" : df.format(Double.parseDouble(obj.toString()));
    }
	/**
	 * 验证空<br>
	 * 若是字符串则不是空，也不是空字符串
	 * @param object要判断的值
	 * @return 若是空，返回true；反之，返回false
	 */
	public static boolean isNull(Object object) {
		return object == null || "null".equals(object.toString().trim())
				|| "".equals(object.toString().trim())
				|| "NaN".equals(object.toString().trim());
	}

	/**
	 * 验证空<br>
	 * 若是字符串则不是空，也不是空字符串
	 * @param object 要判断的值
	 * @return 不是空，返回true；反之，返回false
	 */
	public static boolean noNull(Object object) {
		return object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim())
				&& !"NaN".equals(object.toString().trim());
	}

	/**
	 * 验证空并返回默认值<br>
	 * 第一个参数若是空，则返回第二个默认值参数
	 * @param object 要判断的值
	 * @param defaultObject 默认值
	 * @return 若是空，返回defaultValue；反之，返回object
	 */
	public static Object isNullToDefault(Object object, Object defaultObject) {
		if (object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim())
				&& !"NaN".equals(object.toString().trim()))
			return object;
		else
			return defaultObject;
	}
	
	public static String getTime(Date d){
		if(isNull(d)){
			return "";
		}
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format1.format(d);
	}
	
	/**
	 * 验证空并返回默认值<br>
	 * 第一个参数若是空，则返回第二个默认值参数
	 * @param object要判断的值
	 * @param defaultObject默认值
	 * @return 若是空，返回defaultValue；反之，返回object
	 */
	public static String isNullToDefaultString(Object object, String defaultObject) {
		if (object != null && !"".equals(object.toString().trim())
				&& !"null".equals(object.toString().trim())
				&& !"NaN".equals(object.toString().trim()))
			return object.toString().trim();
		else
			return defaultObject;
	}
	
	/**
	 * 用于处理单表增/改/删(多条删除)后 返回结果 
	 * 2017-6-30 09:27:35 wdm
	 * @param i 成功insert/update条数  i>0成功；其余失败
	 * @return  map={\"success\":\"true\",\"msg\":\"信息操作成功!\"}
	 */
	public static Map<String, Object> returnDMLMap(int i,String msg){
		Map<String, Object> map=new HashMap<String, Object>();
		boolean success=false;
		if(i>0){
			success=true;
			msg=isNull(msg)?"数据操作成功!":msg;
		}else{
			msg=isNull(msg)?"数据操作失败!":msg;
		}
		map.put("flag", success);
		map.put("msg", msg);
		return map;
	}
	
	public static Map<String, Object> returnDMLMap(boolean flag,String msg){
		Map<String, Object> map=new HashMap<String, Object>();
		if(flag){
			msg=isNull(msg)?"数据操作成功!":msg;
		}else{
			msg=isNull(msg)?"数据操作失败!":msg;
		}
		map.put("flag", flag);
		map.put("msg", msg);
		return map;
	}
	
	public static Map<String, Object> returnDMLMap(int i,String successMsg,String failMsg){
		Map<String, Object> map=new HashMap<String, Object>();
		String msg="";
		boolean success=false;
		if(i>0){
			success=true;
			msg=isNull(successMsg)?"数据操作成功!":successMsg;
		}else{
			msg=isNull(failMsg)?"数据操作失败!":failMsg;
		}
		map.put("flag", success);
		map.put("msg", msg);
		return map;
	}
	
	public static Map<String, Object> returnDMLMap(boolean flag,String successMsg,String failMsg){
		Map<String, Object> map=new HashMap<String, Object>();
		String msg="";
		if(flag){
			msg=isNull(successMsg)?"数据操作成功!":successMsg;
		}else{
			msg=isNull(failMsg)?"数据操作失败!":failMsg;
		}
		map.put("flag", flag);
		map.put("msg", msg);
		return map;
	}
}
