/**
 * 
 */
package com.huayue.platform.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.huayue.framework.service.BaseService;
import com.huayue.framework.util.Page;
import com.huayue.platform.entity.User;

/**
 * @author lsk0414
 *
 */
public interface UserService extends BaseService<User>{
	Page<User> findByPage(HttpServletRequest request,String mapper);
	
	public Map<Integer,User> selectForMap();
}
