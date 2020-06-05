package com.songyan.model.node.root;

import java.util.List; 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.songyan.constant.UserInfo;
import com.songyan.model.node.User;

import lombok.Data;

/**
 * 
 * @author songyan
 * @date 2020年6月5日 上午8:20:56
 * @desc 用户列表映射类
 */
@XmlRootElement(name = UserInfo.NODE_GROUP)
@XmlAccessorType(XmlAccessType.FIELD)
@Data 
public class UserRoot {
	
	@XmlElement(name=UserInfo.NODE_ROOT)
	private List<User> list; 

}
