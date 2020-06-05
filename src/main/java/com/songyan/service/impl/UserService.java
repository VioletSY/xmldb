package com.songyan.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songyan.dao.IUserDao;
import com.songyan.model.node.User;
import com.songyan.service.IUserService;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:09:06
 * @desc 用户
 */
@Service
public class UserService implements IUserService{
		
	@Autowired
	private IUserDao dao;

	@Override
	public User get(String id) {
		return dao.get(id);
	}

	@Override
	public User insert(User user) {
		if(user==null || user.getId()==null){
			user.setId(UUID.randomUUID().toString());
		}
		dao.insert(user);
		return user;
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public int update(User user) {
		return dao.update(user);
	}

	@Override
	public List<User> getList(String name, int page, int limit) {
		return dao.getList(name,page,limit);
	}

}
