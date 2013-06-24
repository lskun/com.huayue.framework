/**
 * 
 */
package com.huayue.framework.mybatis.interceptor;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

import com.huayue.framework.mybatis.dialect.Dialect;
import com.huayue.framework.mybatis.dialect.MySql5PageHelper;
import com.huayue.framework.mybatis.dialect.Mysql5Dialect;
import com.huayue.framework.util.Page;
/**
 * @author lsk0414
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{
	
	private static final Logger log = Logger.getLogger(PaginationInterceptor.class);
	
	 /** 
	 * 
	 * 分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。 
	 * 利用拦截器实现Mybatis分页的原理： 
	 * 要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句 
	 * 是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的 
	 * prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用 
	 * StatementHandler对象的prepare方法，即调用invocation.proceed()。 
	 * 对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设 
	 * 置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。 
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
		
		/**
		RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
		if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
			return invocation.proceed();
		}
		**/
		Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
		Dialect.Type databaseType  = null;
		try{
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch(Exception e){
			//ignore 
		}
		if(databaseType == null){
			throw new RuntimeException("the value of the dialect property in mybatis_configuration.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch(databaseType){
			case MYSQL:
				dialect = new Mysql5Dialect();				
		}
		
		String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
		Object obj = boundSql.getParameterObject();
		if(obj instanceof Page<?>){
			Page<?> page = (Page<?>)obj;
			MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");
			Connection connection = (Connection)invocation.getArgs()[0];
			this.setTotalRecord(page, mappedStatement, connection);
			if(page.getPageCount() != 0){
				int offset =  (page.getPageIndex() - 1) * page.getPageSize();				
				metaStatementHandler.setValue("delegate.boundSql.sql",  dialect.getLimitString(originalSql, offset , page.getPageSize()));
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );
				if(log.isDebugEnabled()){
					log.debug("生成分页SQL : " + boundSql.getSql());
				}
			}
		}
				
		return invocation.proceed();
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties arg0) {
		
	}
	
	/**
	 * 
	 * @param page
	 * @param mappedStatement
	 * @param connection
	 */
	private void setTotalRecord(Page<?> page ,MappedStatement mappedStatement ,Connection connection)
	{
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		String sql = boundSql.getSql();
		String countSql = MySql5PageHelper.getCountString(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration() ,countSql ,parameterMappings ,page);
		
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page ,countBoundSql);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int totalRecord = rs.getInt(1);
				page.setRecordCount(totalRecord);
			}
		}catch(SQLException ex){
			log.error(ex);
		}finally{			
				try {
					if(rs!= null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
				} catch (SQLException e) {
					log.error(e);
				}
		}
	}
}
