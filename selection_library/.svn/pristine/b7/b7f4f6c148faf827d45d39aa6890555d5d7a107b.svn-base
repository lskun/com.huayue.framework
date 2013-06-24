/**
 * 
 */
package com.huayue.framework.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.huayue.framework.dao.BaseDao;
import com.huayue.framework.mapper.BaseMapper;
import com.huayue.framework.util.Page;

/**
 * 业务公共类service接口
 * @author lsk0414
 *
 */
public interface BaseService<T> {
	void setBaseDao(BaseDao<T, ? extends BaseMapper<T>> baseDao);
	
	/**
	 * 根据主键查询对应的对象
	 * @param primaryKey 对象的主键
	 * @return 查询后的对象
	 * @throws DataAccessException DataAccessException
	 */
	T findByID(Serializable primaryKey) throws DataAccessException;
		
	/**
	 * 根据对象主键删除对应的对象
	 * @param primaryKey 对象的
	 * @throws DataAccessException DataAccessException
	 */
	void deleteByID(Serializable primaryKey) throws DataAccessException;
	
	/**
	 * 查询所有对象的  度
	 * @return 数据的长度
	 * @throws DataAccessException DataAccessException
	 */
	int findAllObjLength() throws DataAccessException;
	
	/**
	 * 分页查询对象
	 * @param page Page对象
	 * @return 返回查询出的数据
	 */
	Page<T> findByPage(Page<T> page) throws DataAccessException;
	
	/**
	 * 保存对象到数据库表中
	 * @param t 要保存的对象
	 * @throws DataAccessException DataAccessException
	 */
	void addObj(T t) throws DataAccessException;
	
	void update(T t) throws DataAccessException;
	/**
	 * 根据对象的字段进行查询
	 * @param paramName 要查询的字段
	 * @param paramValue 该字段对应的值
	 * @return 查询到的数据
	 * @throws DataAccessException DataAccessException
	 */
	List<T> findByParam(String paramName, Serializable paramValue) throws DataAccessException;
}
