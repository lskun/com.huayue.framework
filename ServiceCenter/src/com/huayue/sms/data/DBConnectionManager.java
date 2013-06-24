package com.huayue.sms.data;

import java.sql.*;

public class DBConnectionManager
{
    private static DBConnectionManager instance = null;
    private DBManager dbManager = null;

    private DBConnectionManager() throws Exception
    {
        dbManager = DBManager.getInstance();
    }

	public static synchronized DBConnectionManager getInstance() throws Exception
    {
        if (null == instance) instance = new DBConnectionManager();
        return instance;
	}

    public synchronized Connection getConnection(String pool) throws Exception
    {
        return dbManager.getConnection(pool);
    }

    public synchronized void freeConnection(String pool, Connection conn) throws Exception
    {
        dbManager.freeConnection(pool, conn);
    }

	public synchronized void release()
    {
		// do nothing here
	}
	
	
}
