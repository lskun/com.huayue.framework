package com.huayue.sms.data;

import java.sql.*;

public class DBAccess
{
    public int pidx = 1;
    public Connection conn = null;
    private boolean __count_records__ = false;
    private PreparedStatement __prod__ = null;
    
    //private JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.getBean("jdbcTemplate");
    
	public synchronized void openConnection() throws Exception
    {
		conn = DBManager.getInstance().getConnection("idb");
    	//conn = jdbcTemplate.getDataSource().getConnection();
        pidx = 1;
    }

    public void beginTransaction() throws Exception
    {
        conn.setAutoCommit(false);
    }

    public void endTransaction(boolean result) throws Exception
    {
        if (conn.getAutoCommit()) throw new Exception("Invalid Transaction");
        if (result) conn.commit();
        else conn.rollback();
        conn.setAutoCommit(true);
    }

    public synchronized void closeConnection()
    {
        try
        {
            //DataSourceUtils.releaseConnection(conn,jdbcTemplate.getDataSource());  
            //
        	DBManager.getInstance().freeConnection("idb", conn);
        }
        catch (Exception ex)
        {
            // ...
        }
    }

    public synchronized PreparedStatement prepareStatement(String sql) throws Exception
    {
        pidx = 1;
        return conn.prepareStatement(sql);
    }
    
    public synchronized PreparedStatement generateStatement(String sql) throws Exception
    {
        pidx = 1;
        return conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
    }
    
    public synchronized Statement createStatement() throws Exception
    {
        return conn.createStatement();
    }

    public synchronized PreparedStatement createPageQuery(String sql, int pageIndex, int pageSize) throws Exception
    {
        regCountRecords(sql);
        return conn.prepareStatement(getPageSQL("*", sql, pageIndex));
    }

    private synchronized void regCountRecords(String sql) throws Exception
    {
        __prod__ = conn.prepareStatement(getCountSQL(sql));
        __count_records__ = true;
    }

    public synchronized void addParameter(PreparedStatement prod, int val) throws Exception
    {
        if (__count_records__) __prod__.setInt(pidx, val);
        prod.setInt(pidx++, val);
    }

    public synchronized void addParameter(PreparedStatement prod, String val) throws Exception
    {
        if (__count_records__) __prod__.setString(pidx, val);
        prod.setString(pidx++, val);
    }

    public synchronized void addParameter(PreparedStatement prod, float val) throws Exception
    {
        if (__count_records__) __prod__.setFloat(pidx, val);
        prod.setFloat(pidx++, val);
    }
    
    public synchronized void addParameter(PreparedStatement prod, Timestamp val) throws Exception
    {
    	if(__count_records__) __prod__.setTimestamp(pidx, val);
    	prod.setTimestamp(pidx++, val);
    }

    public synchronized void addParameter(PreparedStatement prod, long val) throws Exception
    {
        if (__count_records__) __prod__.setLong(pidx, val);
        prod.setLong(pidx++, val);
    }

    public synchronized void addPageParameters(PreparedStatement prod, int pageIndex, int pageSize) throws Exception
    {
        if (pageIndex == 1)
        {
            prod.setInt(pidx++, pageSize);
        }
        else prod.setInt(pidx ++, (pageSize * (pageIndex - 1) + 1));
        prod.setInt(pidx ++, pageSize * pageIndex);
    }

    public synchronized int getRecordCount() throws Exception
    {
        if (!__count_records__) return -1;
        ResultSet rst = null;
        try
        {
            __count_records__ = false;
            rst = __prod__.executeQuery();
            if (!rst.next()) return -1;
            return rst.getInt("CC");
        }
        finally
        {
            try { rst.close(); } catch (Exception e) { }
            try { __prod__.close(); } catch (Exception e) { }
        }
    }

    public synchronized ResultSet executeQuery(PreparedStatement prod) throws Exception
    {
        pidx = 1;
        return prod.executeQuery();
    }

    public synchronized int executeUpdate(PreparedStatement prod) throws Exception
    {
        pidx = 1;
        return prod.executeUpdate();
    }

    private String getPageSQL(String fields, String query, int pageIndex)
    {
        //String sql = "";
        if (pageIndex == 1)
        {
            query = "SELECT " + fields + " FROM (" + query + ") WHERE ROWNUM <= ? OR (1 = ?)";
        }
        else
        {
            query = "SELECT " + fields + " FROM (SELECT A.*,ROWNUM AS ROW_NUM FROM (" + query + ") A) B WHERE B.ROW_NUM BETWEEN ? AND ?";
        }
        return query;
    }

    private String getCountSQL(String sql)
    {
        return sql.replaceAll("(?is)^SELECT([\\s\\S]+?)FROM([\\s\\S]+?)(WHERE\\s*[\\s\\S]+?)?(ORDER\\s*[\\s\\S]+?)?$", "SELECT COUNT(*) AS CC FROM $2 $3");
    }

    public synchronized void closeStatement(ResultSet rst, Statement prod)
    {
        try { rst.close(); } catch (Exception e) { }
        try { prod.close(); } catch (Exception e) { }
    }

}
