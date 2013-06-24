/**
 * 
 */
package com.huayue.platform.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huayue.framework.dao.impl.BaseDaoImpl;
import com.huayue.platform.dao.UserDao;
import com.huayue.platform.entity.User;
import com.huayue.platform.mapper.UserMapper;

/**
 * @author lsk0414
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, UserMapper> implements UserDao{
		
	public User login(User user) {
		return getMapper().login(user);
	}

	public ArrayList<User> findAllUsers(Map params) {
		return getMapper().findAllUsers(params);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer,User> selectForMap(){
		return getSqlSession().selectMap("com.huayue.platform.mapper.UserMapper.selectForMap", null, "id");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<User> select() {
		return getMapper().select();
	}

}
