/**
 * 
 */
package com.huayue.library.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huayue.library.dao.ActicleManagerDao;
import com.huayue.library.mapper.ActicleMapper;
import com.huayue.framework.dao.impl.BaseDaoImpl;
import com.huayue.library.domain.Acticle;
/**
 * @author lsk0414
 *
 */
@Repository
public class ActicleManagerDaoImpl extends BaseDaoImpl<Acticle,ActicleMapper> implements ActicleManagerDao{

	public void deleteMapping(Serializable primaryKey) {
		getMapper().deleteMappings((Integer) primaryKey);
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.dao.ActicleManagerDao#getDirectory(int)
	 */
	public String getDirectory(int id) {
		// TODO Auto-generated method stub
		return getMapper().findDirectory(id);
	}
	
}
