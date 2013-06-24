/**
 * 
 */
package com.huayue.platform.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.huayue.framework.dao.BaseDao;
import com.huayue.platform.entity.User;
import com.huayue.platform.mapper.UserMapper;

/**
 * @author lsk0414
 *
 */
public interface UserDao extends BaseDao<User, UserMapper>{
	/**
	 * 用户登录验证
	 * @param user
	 * @return
	 */
	User login(User user);
	
	ArrayList<User> findAllUsers(Map params);
	
	Map<Integer,User> selectForMap();
	
	ArrayList<User> select();
	
}
