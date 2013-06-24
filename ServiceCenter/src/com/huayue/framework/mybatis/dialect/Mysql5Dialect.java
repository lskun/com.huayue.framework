/**
 * 
 */
package com.huayue.framework.mybatis.dialect;

/**
 * @author lsk0414
 *
 */
public class Mysql5Dialect extends Dialect{
	
	protected static final String SQL_END_DELIMITER = ";";
	
	/* (non-Javadoc)
	 * @see com.huayue.framework.mybatis.dialect.Dialect#getLimitString(java.lang.String, int, int)
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return MySql5PageHelper.getLimitString(sql, offset, limit);
	}
	
	public String getLimitString(String sql, boolean hasOffset) {
		return MySql5PageHelper.getLimitString(sql,-1,-1);
	}

	public boolean supportsLimit() {
		return true;
	}
	
}
