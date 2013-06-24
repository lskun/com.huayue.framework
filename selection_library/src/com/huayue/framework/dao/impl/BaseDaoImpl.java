/**
 * 
 */
package com.huayue.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.huayue.framework.dao.BaseDao;
import com.huayue.framework.mapper.BaseMapper;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.Page;
import com.huayue.framework.util.PageConstants;

/**
 * @author lsk0414
 *
 */
public class BaseDaoImpl<T, E> extends SqlSessionDaoSupport implements BaseDao<T, E>{
	
	private Class<E> mapperClass;
	
	private Class<T> entityClass;
	
	@Autowired
	SqlSessionFactory sessionFactory;
	
	public BaseDaoImpl(){
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		entityClass = (Class)params[0];
		mapperClass = (Class)params[1];
	}

	/* 设置对应的mapperClass
	 * @see com.huayue.framework.dao.BaseDao#setMapperClass(java.lang.Class)
	 */
	/*****
	public void setMapperClass(Class<E> mapperClass) throws DataAccessException {
		this.mapperClass = mapperClass;
	}
	*****/
	/* 得到对应的mapper对象
	 * @see com.huayue.framework.dao.BaseDao#getMapper()
	 */
	public E getMapper() throws DataAccessException {
		return sessionFactory.getConfiguration().getMapper(mapperClass, getSqlSession());
	}
	
	public BaseMapper<T> getBaseMapper(){
		return (BaseMapper<T>)getMapper();
	}
	
	/* 根据主键查询对应的对象
	 * @see com.huayue.framework.dao.BaseDao#findByID(java.io.Serializable)
	 */
	public T findByID(Serializable primaryKey) throws DataAccessException {
		return getBaseMapper().findByID(primaryKey);
	}

	/* 根据主键删除对应的对象
	 * @see com.huayue.framework.dao.BaseDao#deleteByID(java.io.Serializable)
	 */
	public void deleteByID(Serializable primaryKey) throws DataAccessException {
		getBaseMapper().deleteByID(primaryKey);
	}

	/* 查询所有对象的长度#数据
	 * @see com.huayue.framework.dao.BaseDao#findAllObjLength()
	 */
	public int findAllObjLength() throws DataAccessException {
		// TODO Auto-generated method stub
		return getBaseMapper().findAllObjLength();
	}

	/* 保存对象到数据库表中
	 * @see com.huayue.framework.dao.BaseDao#addObj(java.lang.Object)
	 */
	public void addObj(T t) throws DataAccessException {
		getBaseMapper().addObj(t);
	}

	/* 根据对象的字段进行查询
	 * @see com.huayue.framework.dao.BaseDao#findByParam(java.lang.String, java.io.Serializable)
	 */
	public List<T> findByParam(String paramName, Serializable paramValue)
			throws DataAccessException {
		return getBaseMapper().findByParam(paramName, paramValue);
	}

	/* 更新对象到数据库表中
	 * @see com.huayue.framework.dao.BaseDao#update(java.lang.Object)
	 */
	public void update(T t) throws DataAccessException {
		getBaseMapper().update(t);
	}

	/* (non-Javadoc)
	 * @see com.huayue.framework.dao.BaseDao#findByPage(com.huayue.framework.util.Page)
	 */
	public Page<T> findByPage(Page<T> page) throws DataAccessException {
		List<T> results = getBaseMapper().findByPage(page);
		page.setItems(results);
		return page;
	}

	/* 通用分页
	 * @Param mapperMethod : Mapper类的全限定名对应的方法
	 * @see com.huayue.framework.dao.BaseDao#findByPage(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	/**
	public Page<T> findByPage(HttpServletRequest request, String mapperMethod)
			throws DataAccessException {
		String indexStr = request.getParameter(PageConstants.CUR_PAGE_NAME);
		String sizeStr = request.getParameter(PageConstants.PAGE_DATA_NAME);
		
		//当前页
		int pageIndex = Format.isInteger(indexStr) ? Integer.parseInt(indexStr) : PageConstants.FIRST_PAGE;				
		//每页显示的数据量
		int pageSize = Format.isInteger(sizeStr) ? Integer.parseInt(sizeStr) : PageConstants.PAGE_DATA;
		//记录总数
		int recordCount;
		Map map = request.getParameterMap();
		Map params = new HashMap(map.size());	
		if(map.size() == 0) recordCount = getBaseMapper().findAllObjLength();	
		else{
			
			for(java.util.Iterator it = map.keySet().iterator();it.hasNext();){
				String name = (String)it.next();
				params.put(name, request.getParameter(name));
			}
			recordCount = getBaseMapper().findAllObjLengthByParams(params);
		}
		
		Page<T> pagination = new Page<T>(pageIndex ,recordCount ,pageSize);
		List<T> items = getSqlSession().selectList(
				mapperMethod, map.size() == 0 ? null : params, new RowBounds(pageIndex == PageConstants.FIRST_PAGE ? 0  : pageSize * (pageIndex - 1) ,pageSize));
		pagination.setItems(items);
		return pagination;
	}
	**/
}
