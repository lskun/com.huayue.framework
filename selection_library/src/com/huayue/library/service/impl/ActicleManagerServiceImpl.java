/**
 * 
 */
package com.huayue.library.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.huayue.framework.service.impl.BaseServiceImpl;
import com.huayue.framework.util.Page;
import com.huayue.library.dao.ActicleManagerDao;
import com.huayue.library.domain.Acticle;
import com.huayue.library.service.ActicleManagerService;

/**
 * @author lsk0414
 *
 */
@Service
public class ActicleManagerServiceImpl extends BaseServiceImpl<Acticle> implements ActicleManagerService{
	
	
	private ActicleManagerDao acticleManagerDao;
	
	@Autowired
	public ActicleManagerServiceImpl(ActicleManagerDao acticleManagerDao){
		this.acticleManagerDao = acticleManagerDao;
		setBaseDao(acticleManagerDao);
	}
	
	@Override
	public void deleteByID(Serializable primaryKey) throws DataAccessException {
		acticleManagerDao.deleteByID(primaryKey);
		acticleManagerDao.deleteMapping(primaryKey);
	}

	/* (non-Javadoc)
	 * @see com.huayue.library.service.ActicleManagerService#getDirectory(int)
	 */
	public String getDirectory(int id) {
		// TODO Auto-generated method stub
		return acticleManagerDao.getDirectory(id);
	}
}
 