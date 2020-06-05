package com.songyan.dao;

import java.util.List;

import com.songyan.model.node.User;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:08:31
 * @desc 用户
 */
public interface IUserDao extends BaseDao<User>{

	List<User> getList();
 
	List<User> getList(String name, int page, int limit);

}
