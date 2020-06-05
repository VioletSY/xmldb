package com.songyan.service;

import java.util.List;

import com.songyan.model.node.User;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:09:26
 * @desc 用户
 */
public interface IUserService extends BaseService<User>{

	/**
	 * 获取列表数据
	 * @param name
	 * @param page
	 * @param limit
	 * @return
	 */
	List<User> getList(String name,int page, int limit);

}
