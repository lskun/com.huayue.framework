package com.huayue.sms.data;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

public class DBPool implements Runnable
{
    int nums = 0;
    int minconn = 0;
    int maxconn = 0;
    int timeout = 1000000;
    int ttl = 1000 * 60 * 5;

    String poolName;
    String url;
    String user;
    String password;

    Vector pools = new Vector();                    // 闲置连接池
    Vector connections = new Vector();              // 使用中的连接池

    public DBPool(String poolName, String url, String user, String password, int minconn, int maxconn)
    {
        this.minconn = minconn;
        this.maxconn = maxconn;
        this.poolName = poolName;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void setMinConn(int min) { this.minconn = min; }
    public void setMaxConn(int max) { this.maxconn = max; }
    public void setUrl(String url) { this.url = url; }
    public void setUser(String user) { this.user = user; }
    public void setPassword(String password) { this.password = password; }
    public void setTimeout(int timeout) { if (this.timeout > 50) this.timeout = timeout; }
    public void setTTL(int ttl) { this.ttl = ttl; }

    public int getMinConn() { return this.minconn; }
    public int getMaxConn() { return this.maxconn; }
    public int getTimeout() { return this.timeout; }
    public int getTTL() { return this.ttl; }

    public int[] getPoolStat() { return new int[] { pools.size(), connections.size() }; }

    public void closeConnections()
    {
        synchronized (connections)
        {
            for (int i = connections.size() - 1; i >= 0; i--)
            {
                Conn conn = (Conn)connections.remove(i);
                try { conn.forceKill(); } catch (Exception e) { }
            }
        }
    }

    protected void release()
    {
        // close all the connections
    }

    public synchronized boolean setSQLText(String sql, Connection conn) throws Exception
    {
        if (null == conn) return false;
        // System.out.println("Remove: " + conn + ":" + connections.remove(conn));
        for (int i = 0, l = connections.size(); i < l; i++)
        {
            Conn conn2 = (Conn)connections.get(i);
            if (conn2.getInstance().equals(conn))
            {
                conn2.setSQLText(sql);
                return true;
            }
        }
        return false;
    }

    public synchronized Connection getConnection() throws Exception
    {
        Conn conn = null;

        int count = pools.size();
        if (nums >= maxconn) throw new Exception("max connections exceed");

        if (count == 0) conn = newConnection();
        else conn = (Conn)pools.remove(0);
        nums += 1;
        connections.add(conn);
        return conn.getConnection();
    }

    private Conn newConnection() throws Exception
    {
        return new Conn(DriverManager.getConnection(url, user, password));
    }

    private boolean testConnection(Connection conn)
    {
        if (null == conn) return false;
        ResultSet rst = null;
        Statement stmt = null;

        try
        {
            if (conn.isClosed()) return false;
            stmt = conn.createStatement();
            rst = stmt.executeQuery("SELECT 1 AS X FROM DUAL");
            if (rst.next() && 1 == rst.getInt("X"))
            {
                return true;
            }
        }
        catch (Exception ex)
        {
            System.out.println("DBM: [Test] " + ex.toString());
            return false;
        }
        finally
        {
            try { rst.close(); } catch (Exception e) { }
            try { stmt.close(); } catch (Exception e) { }
            rst = null;
            stmt = null;
        }
        return false;
    }

    public synchronized void freeConnection(Connection conn) throws Exception
    {
        if (null == conn) return;
        // System.out.println("Remove: " + conn + ":" + connections.remove(conn));
        for (int i = 0, l = connections.size(); i < l; i++)
        {
            Conn conn2 = (Conn)connections.get(i);
            if (conn2.getInstance() == conn)
            {
                connections.remove(i);
                conn2.shutdown();
                if (testConnection(conn)) pools.add(conn2);
                else
                {
                    System.out.println("Unseable connection found");
                }
                break;
            }
        }
        nums -= 1;
        notifyAll();
    }

    public void run()
    {
        while (true)
        {
            try { Thread.sleep(10000); } catch (Exception e) { }
            log("DBM: Pool = " + pools.size() + ", Using = " + connections.size());
            synchronized(pools)
            {
                for (int i = 0, l = pools.size() - minconn; i < l; i++)
                {
                    try
                    {
                        Conn conn = (Conn)pools.remove(0);
                        conn.close();
                    } catch (Exception e) { }
                }
            }
            // 占用连接过久，需要回收
            synchronized (connections)
            {
                for (int i = connections.size() - 1; i >= 0; i--)
                {
                    try
                    {
                        Conn conn = (Conn)connections.get(i);
                        if (conn.getStandingTime() > 1000 * 60 * 60 * 30L)
                        {
                            connections.remove(i);
                            conn.forceKill();
                            nums -= 1;
                        }
                    }
                    catch (Exception e) { }
                }
            }
        }
    }

    private void log(String msg)
    {
        System.out.println(msg);
    }
}
