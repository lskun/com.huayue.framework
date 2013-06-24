/**
 * 
 */
package com.huayue.library.mapper;

import org.apache.ibatis.annotations.Select;

import com.huayue.framework.mapper.BaseMapper;
import com.huayue.library.domain.User;
import java.util.*;
/**
 * @author lsk0414
 *
 */
public interface UserMapper extends BaseMapper<User>{
	
	@Select("select * from sms_user where user_name = #{name} and password = #{password}")
	User login(User user);
	
	@Select("select id,user_name as name from sms_user")
	List<Map> getUserMap();
}
