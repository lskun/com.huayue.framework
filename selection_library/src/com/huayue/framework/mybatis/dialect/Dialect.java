/**
 * 
 */
package com.huayue.framework.mybatis.dialect;

/**
 * @author lsk0414
 *
 */
public abstract class Dialect {
	
	public static enum Type{
		ORACLE,MYSQL
	}
	
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
	
}
