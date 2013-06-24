package com.huayue.platform.service.impl;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huayue.platform.service.UserService;

import com.huayue.framework.service.impl.BaseServiceImpl;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.Page;
import com.huayue.platform.dao.UserDao;
import com.huayue.platform.entity.User;
/**
 * @author lsk0414
 *
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao){
		this.userDao = userDao;
		setBaseDao(userDao);
	}
	
	/* 通用分页
	 * @see com.huayue.platform.service.UserService#findByPage()
	 */
	public Page<User> findByPage(HttpServletRequest request,String mapperMethod) {
		return userDao.findByPage(request, mapperMethod);
	}

	/* (non-Javadoc)
	 * @see com.huayue.platform.service.UserService#selectForMap()
	 */
	public Map<Integer, User> selectForMap() {
		return userDao.selectForMap();
	}

}
