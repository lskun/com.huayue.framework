/**
 * 
 */
package com.huayue.library.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huayue.framework.dao.impl.BaseDaoImpl;
import com.huayue.library.dao.UserDao;
import com.huayue.library.domain.User;
import com.huayue.library.mapper.UserMapper;
/**
 * @author lsk0414
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User,UserMapper> implements UserDao{

	/* (non-Javadoc)
	 * @see com.huayue.library.dao.UserDao#login(com.huayue.library.domain.User)
	 */
	public User login(User user) {
		// TODO Auto-generated method stub
		return getMapper().login(user);
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.dao.UserDao#generateUserMap()
	 */
	public Map<Integer,String> generateUserMap() {
		List<Map> result = getMapper().getUserMap();
		Map<Integer,String> userMap = new HashMap<Integer,String>(result.size());
		for(Map map : result){
			userMap.put((Integer)map.get("id"), (String)map.get("name"));
		}
		return userMap;
	}
}
