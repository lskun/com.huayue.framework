/**
 * 
 */
package com.huayue.library.mapper;

import org.apache.ibatis.annotations.Select;

import com.huayue.framework.mapper.BaseMapper;
import com.huayue.library.domain.Acticle;
/**
 * @author lsk0414
 *
 */
public interface ActicleMapper extends BaseMapper<Acticle>{
	void deleteMappings(int acticleId);
	
	@Select("select directory from library_acticle where id = #{id}")
	String findDirectory(int id);
}
