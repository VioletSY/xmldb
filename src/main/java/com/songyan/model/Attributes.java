package com.songyan.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songyan
 * @date 2020年4月28日 上午10:03:44
 * @desc 属性列表
 */
public class Attributes {
	private List<Attribute> list;

	public Attributes() {
		super();
		list = new ArrayList<>();
	}
	
	public void add(Attribute attribute) {
		list.add(attribute);
	}
	
	public void remove(Attribute attribute){
		list.remove(attribute);
	}
	
	public List<Attribute> getList(){
		return list;
	}
	
	
}
