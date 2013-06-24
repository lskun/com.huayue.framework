/**
 * 
 */
package com.huayue.library.dao;

import java.util.*;

import com.huayue.framework.dao.BaseDao;
import com.huayue.library.domain.User;
import com.huayue.library.mapper.UserMapper;

/**
 * @author lsk0414
 *
 */
public interface UserDao extends BaseDao<User,UserMapper>{
	
	User login(User user);
	
	Map<Integer,String> generateUserMap();
}