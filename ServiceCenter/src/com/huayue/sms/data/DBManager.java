package com.huayue.sms.data;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

public class DBManager
{
    private static DBManager instance = null;

    private Vector drivers;
    private Hashtable pools;
    private DBPool pool;

    private DBManager() throws Exception
    {
        init();
    }

    public static synchronized DBManager getInstance() throws Exception
    {
        if (null == instance) instance = new DBManager();
        return instance;
    }

    public void setMinConn(int min) { pool.setMinConn(min); }
    public void setMaxConn(int max) { pool.setMaxConn(max); }
    public void setUrl(String url) { pool.setUrl(url); }
    public void setUser(String user) { pool.setUser(user); }
    public void setPassword(String password) { pool.setPassword(password); }
    public void setTimeout(int timeout) { pool.setTimeout(timeout); }
    public void setTTL(int ttl) { pool.setTTL(ttl); }

    public int getMinConn() { return pool.getMinConn(); }
    public int getMaxConn() { return pool.getMaxConn(); }
    public int getTimeout() { return pool.getTimeout(); }
    public int getTTL() { return pool.getTTL(); }

    public int[] getPoolStat() { return pool.getPoolStat(); }

    public synchronized Connection getConnection(String poolName) throws Exception
    {
        if (null == pool) return null;
        return pool.getConnection();
    }

    public synchronized void setSQLText(String sql, Connection conn) throws Exception
    {
        if (this.pool != null) this.pool.setSQLText(sql, conn);
    }

    public synchronized void freeConnection(String poolName, Connection conn) throws Exception
    {
        if (null == pool)
        {
            closeConnection(conn);
            return;
        }
        pool.freeConnection(conn);
    }

    private void closeConnection(Connection conn) throws Exception
    {
        conn.close();
    }

    private final synchronized void init() throws Exception
    {
        drivers = new Vector();
        pools = new Hashtable();
        InputStream is = getClass().getResourceAsStream("/db.properties");
        Properties dbProps = new Properties();

        dbProps.load(is);
        loadDrivers(dbProps);
        createPools(dbProps);
    }

    private void loadDrivers(Properties props) throws Exception
    {
        String driverClasses = props.getProperty("drivers");
        StringTokenizer st = new StringTokenizer(driverClasses);
        while (st.hasMoreElements())
        {
            String driverClassName = st.nextToken().trim();
            Driver driver = (Driver)Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(driver);
            drivers.addElement(driver);
        }
    }

    private void createPools(Properties props) throws Exception
    {
        Enumeration propNames = props.propertyNames();
        while (propNames.hasMoreElements())
        {
            String name = (String)propNames.nextElement();
            if (name.endsWith(".url"))
            {
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = props.getProperty(poolName + ".url");

                if (null == url) throw new Exception("Illegal Configuration file");

                String user = props.getProperty(poolName + ".user");
                String password = props.getProperty(poolName + ".password");
                String maxconn = props.getProperty(poolName + ".maxconn", "100");
                String minconn = props.getProperty(poolName + ".minconn", "10");
                String timeout = props.getProperty(poolName + ".timeout", "1000000");

                this.pool = new DBPool(poolName, url, user, password, Integer.parseInt(minconn), Integer.parseInt(maxconn));
                this.pool.setTimeout(Integer.parseInt(timeout));
                new Thread(this.pool).start();
            }
        }
    }
}
