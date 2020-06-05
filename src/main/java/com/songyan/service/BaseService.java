package com.songyan.service;

/**
 * @author songyan
 * @date 2020年4月28日 下午4:28:37
 * @desc service基本接口
 */
public interface BaseService<T> {
	
	/**
	 * 获取详情信息
	 * @param id
	 * @return
	 */
	T get(String id);

	/**
	 * 新增
	 * @param t
	 * @return
	 */
	 T insert(T t);

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
