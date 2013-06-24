package com.huayue.sms.data;

import java.sql.*;
import java.lang.reflect.*;

class Stmt implements java.lang.reflect.InvocationHandler
{
    private Statement stmt = null;
    public Stmt(Statement stmt)
    {
        this.stmt = stmt;
    }

    public Statement getStatement() throws Exception
    {
        Statement stmt2 = (Statement)Proxy.newProxyInstance
            (
                stmt.getClass().getClassLoader(),
                new Class[] { Statement.class }, this
            );
        return stmt2;
    }
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        String sql;
        Object obj = m.invoke(stmt, args);
        if (m.getName().matches("^(execute)|(executeQuery)|(executeUpdate)$") && args.length > 0)
        {
            // setQueryTimeout
            sql = (String)args[0];
            if (!"SELECT 1 AS X FROM DUAL".equals(sql))
                DBManager.getInstance().setSQLText(sql, this.stmt.getConnection());
        }
        return obj;
    }
}

public class Conn implements java.lang.reflect.InvocationHandler
{
    private long requestTime = 0L;
    private long releaseTime = 0L;

    private String sqlText = null;

    private Connection conn = null;
    private Connection instance = null;

    public Conn(Connection conn)
    {
        this.conn = conn;
    }

    public void setSQLText(String sql)
    {
        this.sqlText = sql;
    }

    public Connection getConnection() throws Exception
    {
        if (null != instance) throw new Exception("unusable connection");
        instance = (Connection)Proxy.newProxyInstance
            (
                conn.getClass().getClassLoader(),
                new Class[] { Connection.class }, this
            );
        requestTime = System.currentTimeMillis();
        releaseTime = 0;
        instance.setAutoCommit(true);
        return instance;
    }

    public Connection getInstance()
    {
        return this.instance;
    }

    public void close() throws Exception
    {
        System.out.println("DBM: [Close] " + sqlText);
        conn.close();
    }

    public void shutdown() throws Exception
    {
        this.instance = null;
        releaseTime = System.currentTimeMillis();
        System.out.println("DBM: [" + getLifetime() + " ms] " + sqlText);
    }

    public long getLifetime()
    {
        return releaseTime - requestTime;
    }

    public long getStandingTime()
    {
        return System.currentTimeMillis() - requestTime;
    }

    public void forceKill()
    {
        try
        {
            this.conn.close();
        }
        catch (Exception e) { }
    }

    public String getSQLText()
    {
        return this.sqlText;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        Object obj = m.invoke(conn, args);

        if (m.getName().matches("^(prepareStatement)|(prepareCall)$") && args.length > 0)
        {
            setSQLText((String)args[0]);
        }

        if ("createStatement".equals(m.getName()))
        {
            return new Stmt((Statement)obj).getStatement();
        }

        return obj;
    }
}
