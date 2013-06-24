package com.huayue.framework.util;

import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;

public class FileUtils
{
    private FileUtils()
    {
        // ...
    }

    public static synchronized String readFile(String fPath, String encode) throws Exception
    {
        int bts = -1;
        byte[] bytes = new byte[256];
        FileInputStream fis = null;
        DataInputStream dis = null;
        ByteArrayOutputStream baos = null;
        try
        {
            fis = new FileInputStream(fPath);
            dis = new DataInputStream(fis);
            baos = new ByteArrayOutputStream(256);
            while ((bts = dis.read(bytes)) != -1) baos.write(bytes, 0, bts);
        }
        finally
        {
            try { dis.close(); } catch (Exception ex) { }
            try { fis.close(); } catch (Exception ex) { }
            try { baos.close(); } catch (Exception ex) { }
            dis = null;
            fis = null;
        }
        return baos.toString(encode);
    }

    public static synchronized void writeFile(String fPath, String content, String encoding, boolean append) throws Exception
    {
        FileOutputStream fos = null;
        Writer out = null;
        try
        {
            fos = new FileOutputStream(fPath, append);
            out = new OutputStreamWriter(fos, encoding);
            out.write(content);
        }
        finally
        {
            out.close();
            fos.close();
        }
    }

    public static synchronized void writeFile(String fPath, String content, boolean append) throws Exception
    {
        byte[] bytes;
        FileLock lock = null;
        FileChannel channel = null;
        FileOutputStream fos = null;
        try
        {
            if (null == content) throw new Exception("Content can not be null.");
            fos = new FileOutputStream(fPath, append);
            channel = fos.getChannel();
            lock = channel.lock();
            bytes = content.getBytes();
            for (int i = 0, l = bytes.length; i < l; i += 512)
            {
                fos.write(bytes, i, i + 512 > l ? l - i : 512);
            }
        }
        finally
        {
            try { lock.release(); } catch (Exception ex) { }
            try { fos.close(); } catch (Exception ex) { }
            fos = null;
            lock = null;
            bytes = null;
            channel = null;
        }
    }
}
