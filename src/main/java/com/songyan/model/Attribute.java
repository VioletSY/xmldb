package com.songyan.model;

import com.songyan.util.DataUtil;

/**
 * @author songyan
 * @date 2020年4月28日 上午10:04:35
 * @desc 属性对象
 */
public class Attribute {
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Attribute(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Attribute(String key, Object value, Object defaultValue) {
		super();
		this.key = key;
		this.value = DataUtil.parseToString(value,defaultValue);
	}
	
	public Attribute(String key, Object value) {
		super();
		this.key = key;
		this.value = DataUtil.parseToString(value);
	}
}
