/**
 * 
 */
package com.huayue.framework.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.huayue.framework.dao.BaseDao;
import com.huayue.framework.mapper.BaseMapper;
import com.huayue.framework.service.BaseService;

/**
 * @author lsk0414
 *
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T, ? extends BaseMapper<T>> baseDao;
	
	/* 
	 * @see com.huayue.framework.service.BaseService#setBaseDao(com.huayue.framework.dao.BaseDao)
	 */
	public void setBaseDao(BaseDao<T, ? extends BaseMapper<T>> baseDao) {
		this.baseDao = baseDao;
	}

	/* 
	 * @see com.huayue.framework.service.BaseService#findByID(java.io.Serializable)
	 */
	public T findByID(Serializable primaryKey) throws DataAccessException {
		return baseDao.findByID(primaryKey);
	}

	/* 
	 * @see com.huayue.framework.service.BaseService#deleteByID(java.io.Serializable)
	 */
	public void deleteByID(Serializable primaryKey) throws DataAccessException {
		baseDao.deleteByID(primaryKey);
	}

	/* 
	 * @see com.huayue.framework.service.BaseService#findAllObjLength()
	 */
	public int findAllObjLength() throws DataAccessException {
		return baseDao.findAllObjLength();
	}

	/* 
	 * @see com.huayue.framework.service.BaseService#addObj(java.lang.Object)
	 */
	public void addObj(T t) throws DataAccessException {
		baseDao.addObj(t);
	}

	/* 
	 * @see com.huayue.framework.service.BaseService#findByParam(java.lang.String, java.io.Serializable)
	 */
	public List<T> findByParam(String paramName, Serializable paramValue)
			throws DataAccessException {
		return baseDao.findByParam(paramName, paramValue);
	}

	/* (non-Javadoc)
	 * @see com.huayue.framework.service.BaseService#update(java.lang.Object)
	 */
	public void update(T t) throws DataAccessException {
		baseDao.update(t);
	}

}
