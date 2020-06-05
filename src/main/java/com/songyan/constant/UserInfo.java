package com.songyan.constant;

/**
 * @author songyan
 * @date 2020年6月5日 上午8:07:05
 * @desc
 */
public class UserInfo {
	/** xml根节点 **/
	public static final String NODE_ROOT = "user";
	public static final String NODE_GROUP = "users";

	/** 属性名称 **/
	public static final String NODE_ATTR_ID = "id";
	public static final String NODE_ATTR_NAME = "name";
	public static final String NODE_ATTR_AGE = "age";
	public static final String NODE_ATTR_USERNAME = "userName";
	public static final String NODE_ATTR_PASSWORD = "password";
	public static final String NODE_ATTR_KEY = NODE_ATTR_ID;
	
	/** 查询表达式 **/
	public static final String EXPRESS_BEGIN = "/"+ NODE_GROUP +"/";
	public static final String EXPRESS_ID = "[@id='";
	public static final String EXPRESS_END = "']"; 
	
}
