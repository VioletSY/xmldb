package com.songyan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.songyan.model.node.User;
import com.songyan.service.IUserService;

/**
 * @author songyan
 * @date 2020年5月28日 上午8:08:18
 * @desc 用户
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserService service;
	
	/**
	 * 获取列表数据
	 * @return
	 */
	@RequestMapping("getList")
	@ResponseBody
	public Object getList(String name,int page, int limit) {
		return service.getList(name,page,limit);
	}
	
	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Object get(String id) {
		return service.get(id);
	}
	
	/**
	 * 新增
	 * @param id
	 * @return
	 */
	@RequestMapping("insert")
	@ResponseBody
	public Object insert(String name) {
		return service.insert(new User(name));
	}
	
	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		return service.delete(id);
	}
	
	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(User user) {
		return service.update(user);
	}
	
}
