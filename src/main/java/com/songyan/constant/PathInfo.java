package com.songyan.constant;

import java.io.File;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:52:50
 * @desc 文档路径信息
 */
public class PathInfo {
	/** 公共路径 **/
	private static final String SYSTERM = System.getProperty("user.dir");
	private static final String SYSTERM_MAIN = SYSTERM + File.separator + "src" + File.separator + "main";
	private static final String SYSTERM_RESOURCES = SYSTERM_MAIN + File.separator + "resources";
	private static final String DB = SYSTERM_RESOURCES + File.separator + "db";
	
	/** 数据库模块路径 **/
	private static final String MODULE_USER = DB + File.separator + "user";// 用户模块
	
	/** 具体路径 **/
	public static final String USER_INFO = MODULE_USER + File.separator + "user.xml";
}
