/**
 * 
 */
package com.huayue.attend.dao;

import java.io.Serializable;
import java.lang.reflect.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author lsk0414
 *	公共Dao基类
 */
public class BaseDao<T> {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Class entityClass; //DAO的泛型类,即子类所指定的T所对应的类型
	
	public BaseDao(){
		//通过反射方式获取子类DAO对应的泛型实体类
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		
		entityClass = (Class)params[0];
	}
	
	public T get(Serializable id){
		return (T)hibernateTemplate.get(entityClass, id);
	}
	
	public void save(T entity){
		hibernateTemplate.save(entity);
	}
	
	public void update(T entity){
		hibernateTemplate.update(entity);
	}
	
	public void delete(T entity){
		hibernateTemplate.delete(entity);
	}
	
	public HibernateTemplate getHibernateTemplate(){
		return hibernateTemplate;
	}
}
