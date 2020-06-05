package com.songyan.model.node;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Node;

import com.songyan.constant.UserInfo;
import com.songyan.util.XmlUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:14:57
 * @desc 用户模型
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = UserInfo.NODE_ROOT)
public class User {
	
	/**
	 * 主键
	 */
	@XmlAttribute(name = "id")
	private String id;
	
	/**
	 * 姓名
	 */
	@XmlAttribute(name = "name")
	private String name;
	
	/**
	 * 年龄
	 */
	@XmlAttribute(name = "age")
	private int age;
	
	/**
	 * 用户名
	 */
	@XmlAttribute(name = "userName")
	private String userName;
	
	/**
	 * 密码
	 */
	@XmlAttribute(name = "password")
	private String password;
	
	public User(Node node) {
    	super();
    	XmlUtils xmlUtils = XmlUtils.getInstance();
		this.id = xmlUtils.getNodeAttributeValue(node, UserInfo.NODE_ATTR_ID);
    	this.name = xmlUtils.getNodeAttributeValue(node, UserInfo.NODE_ATTR_NAME);
    	this.age = xmlUtils.getNodeAttributeValue(node, UserInfo.NODE_ATTR_AGE,Integer.class);
    	this.userName = xmlUtils.getNodeAttributeValue(node, UserInfo.NODE_ATTR_USERNAME);
    	this.password = xmlUtils.getNodeAttributeValue(node, UserInfo.NODE_ATTR_PASSWORD);
	}

	public User(String name) {
		super();
		this.name = name;
	}

}
