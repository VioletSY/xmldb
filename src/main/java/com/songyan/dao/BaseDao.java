package com.songyan.dao;

/**
 * @author songyan
 * @date 2020年4月28日 下午4:48:51
 * @desc dao层基本方法
 */
public interface BaseDao<T> {
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	T get(String id); 
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	int insert(T t); 

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int delete(String id);

	/**
	 * 更新
	 * @param t
	 * @return
	 */
	int update(T t);

}
