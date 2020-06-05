package com.songyan.util;

import java.util.List;

/**
 * @author songyan
 * @date 2020年5月7日 下午2:13:51
 * @desc 文档存储方式选择工具类
 */
public class FileSelectUtil {

	/**
	 * 模糊匹配
	 * @param name
	 * @param imageName
	 * @return
	 */
	public static boolean like(String name, String imageName) {
		if(Validate.isNull(name) || (!Validate.isNull(imageName) && imageName.trim().contains(name.trim())))
			return true;
		return false;
	}
	
	/**
	 * 精确匹配
	 * @param type
	 * @param imageType
	 * @return
	 */
	public static boolean equals(String type, String imageType) {
		if(Validate.isNull(type) || (!Validate.isNull(imageType) && imageType.trim().equals(type.trim())))
			return true;
		return false;
	}
	
	/**
	 * 精确匹配
	 * @param type
	 * @param imageType
	 * @return
	 */
	public static boolean equals(int type, int imageType) {
		if(type == imageType)
			return true;
		return false;
	}

	/**
	 * 获取分页数据
	 * @param <T>
	 * @param imageList
	 * @param page
	 * @param limit
	 * @return
	 */
	public static <T> List<T> getList(List<T> imageList, int page, int limit) {
		int start = (page-1)*limit;
		int end = page*limit;
		end = end>imageList.size()?imageList.size():end;
		return imageList.subList(start, end);
	}

}
