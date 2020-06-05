package com.songyan.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;

import com.songyan.constant.PathInfo;
import com.songyan.constant.UserInfo;
import com.songyan.dao.IUserDao;
import com.songyan.model.Attribute;
import com.songyan.model.Attributes;
import com.songyan.model.XmlNode;
import com.songyan.model.node.User;
import com.songyan.model.node.root.UserRoot;
import com.songyan.util.FileSelectUtil;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:09:15
 * @desc 用户
 */
@Repository
public class UserDao implements IUserDao{

	/**
	 * 信息存储文档路径 
	 */
	private final static String path = PathInfo.USER_INFO;
	
	/**
	 * 根节点名称
	 */
	private final static String rootNodeName = UserInfo.NODE_ROOT;
	
	/**
	 * 主键
	 */
	private final static String key = UserInfo.NODE_ATTR_KEY;
	
	/**
	 * 查询表达式头部
	 */
	private final static String begin = UserInfo.EXPRESS_BEGIN;
	
	/**
	 * 节点操作对象
	 */
	private final static XmlNode xmlNode = new XmlNode(path,rootNodeName,key,begin);

	@Override
	public int insert(User user) { 
		Attributes attributes = new Attributes();
		attributes.add(new Attribute(UserInfo.NODE_ATTR_ID,user.getId()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_NAME,user.getName()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_AGE,user.getAge(),18));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_USERNAME,user.getUserName()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_PASSWORD,user.getPassword()));
		return xmlNode.insert(attributes);
	}

	@Override
	public List<User> getList() {
		List<User> UserList =((UserRoot) xmlNode.getList(UserRoot.class)).getList();
		if(UserList!=null){
			Collections.reverse(UserList);
		}
		return UserList;
	}

	@Override
	public User get(String id) {
		Node node = xmlNode.getNode(id);
		return node == null ? null : new User(node);
	}

	@Override
	public int update(User user) {
		Attributes attributes = new Attributes();
		attributes.add(new Attribute(UserInfo.NODE_ATTR_ID,user.getId()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_NAME,user.getName()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_AGE,user.getAge()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_USERNAME,user.getUserName()));
		attributes.add(new Attribute(UserInfo.NODE_ATTR_PASSWORD,user.getPassword()));
		return xmlNode.update(user.getId(),attributes);
	}

	@Override
	public int delete(String id) {
		return xmlNode.delete(id); 
	}

	@Override 
	public List<User> getList(String name, int page, int limit) {
		List<User> userList = getList(name);
		return FileSelectUtil.getList(userList,page,limit);
	}

	private List<User> getList(String name) {
		List<User> userList = getList();
		List<User> result = new ArrayList<>();
		if(userList!=null){
			for (User user : userList) {
				String userName = user.getName();
				if (FileSelectUtil.like(name, userName)) {
					result.add(user);
				}
			}
		}
		return result;
	}

}
