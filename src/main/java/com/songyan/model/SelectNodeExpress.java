package com.songyan.model;

import com.songyan.constant.UserInfo;

/**
 * @author songyan
 * @date 2020年4月28日 下午2:29:04
 * @desc "筛选节点表达式"创建工具
 */
public class SelectNodeExpress {
	
	
	private final String SELECT_SYMBOL = "[@";
	private final String END = UserInfo.EXPRESS_END;
	
	private String begin; 
	private String rootNodeName;
	private String attrName;
	private String attrValue;
	
	public SelectNodeExpress(String begin,String rootNodeName, String attrName, String attrValue) {
		super();
		this.begin = begin;
		this.rootNodeName = rootNodeName;
		this.attrName = attrName;
		this.attrValue = attrValue;
	}
	
	public SelectNodeExpress setRootNodeName(String rootNodeName) {
		this.rootNodeName = rootNodeName;
		return this;
	}


	public SelectNodeExpress setAttrName(String attrName) {
		this.attrName = attrName;
		return this;
	}


	public SelectNodeExpress setAttrValue(String attrValue) {
		this.attrValue = attrValue;
		return this;
	}


	public String getExpress() {
		return begin+rootNodeName+SELECT_SYMBOL+attrName+"='"+attrValue+END;
	} 
}
