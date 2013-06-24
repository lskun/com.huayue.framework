/**
 * 
 */
package com.huayue.platform.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.huayue.framework.mapper.BaseMapper;
import com.huayue.platform.entity.User;

/**
 * @author lsk0414
 *
 */
public interface UserMapper extends BaseMapper<User>{
	
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	User login(User user);
	
	//@Select("select id as 'id',user_name as 'name',password as 'password',permission as 'permission' ," +
	//		"create_time as 'createTime',modified_time as 'modifiedTime' from sms_user")
	ArrayList<User> findAllUsers(Map params);
	
	Map<Integer,User> selectForMap();
	
	ArrayList<User> select();
}
