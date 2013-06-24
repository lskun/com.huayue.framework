package com.huayue.framework.util;

import java.io.*;
import java.net.*;
import java.util.*;

import com.huayue.platform.entity.Header;



public class Tool {

    private static int CONNECTION_TIME_OUT = 30000;

    //不能创建实例
    private Tool() { }

    // IP字符串转为数字表现形式
    public static long ip2Number(String ip) throws Exception
    {
        String hex = "";
        if (!ip.matches("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$")) throw new Exception("Invalid ip address.");
        hex += ("000" + Integer.toHexString(Integer.parseInt(ip.replaceAll("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$", "$1")))).replaceAll("^0+(\\w{2})$", "$1");
        hex += ("000" + Integer.toHexString(Integer.parseInt(ip.replaceAll("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$", "$2")))).replaceAll("^0+(\\w{2})$", "$1");
        hex += ("000" + Integer.toHexString(Integer.parseInt(ip.replaceAll("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$", "$3")))).replaceAll("^0+(\\w{2})$", "$1");
        hex += ("000" + Integer.toHexString(Integer.parseInt(ip.replaceAll("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$", "$4")))).replaceAll("^0+(\\w{2})$", "$1");
        return Long.parseLong(hex, 16);
    }

    // 将以数字表现的IP转为直观的字符串形式
    public static String number2Ip(long nIp) throws Exception
    {
        String ip, hex = Long.toHexString(nIp);
        if (8 != hex.length()) throw new Exception("Invalid ip address.");
        ip = Integer.parseInt(hex.substring(0, 2), 16) + ".";
        ip += Integer.parseInt(hex.substring(2, 4), 16) + ".";
        ip += Integer.parseInt(hex.substring(4, 6), 16) + ".";
        ip += Integer.parseInt(hex.substring(6, 8), 16);
        return ip;
    }

    public static synchronized String loadUrl(String url) throws Exception
    {
        return loadUrl(url, null, "UTF-8");
    }

    // 执行系统命令, 不返回执行结果
    public static void run(String cmd) throws Exception
    {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(cmd);
        runtime = null;
    }

    // 执行系统命令, 并得到执行结果
    public static String exec(String cmd) throws Exception
    {
        String text = "", line;
        Process proc = null;
        Runtime runtime = Runtime.getRuntime();
        DataInputStream dis = null;

        try
        {
            proc = runtime.exec(cmd);
            dis = new DataInputStream(proc.getInputStream());
            while ((line = dis.readLine()) != null)
            {
                text += line + "\r\n";
            }
        }
        finally
        {
            try { dis.close(); } catch (Exception e) { };
            dis = null;
            runtime = null;
        }
        return text;
    }

    public static String loadUrl(int timeOut, String url, String encoding) throws Exception
    {
        int tmp = CONNECTION_TIME_OUT;
        CONNECTION_TIME_OUT = timeOut;
        try
        {
            return loadUrl(url, null, encoding);
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            CONNECTION_TIME_OUT = tmp;
        }
    }

    // 发请HTTP(METHOD:GET)请求,载入获得目标URL的内容
    public static String loadUrl(String url, Header[] headers, String encoding) throws Exception
    {
        int ch = -1;
        String shtml = "";
        URL oUrl = null;
        HttpURLConnection urlConn = null;
        BufferedInputStream bis = null;
        byte[] buf = new byte[256];
        ByteArrayOutputStream baos = null;
        headers = null == headers ? new Header[0] : headers;

        try
        {
            baos = new ByteArrayOutputStream(256);
            oUrl = new URL(url);
            urlConn = (HttpURLConnection)oUrl.openConnection();
            urlConn.setConnectTimeout(CONNECTION_TIME_OUT);
            urlConn.setRequestProperty("Accept", "*/*");
            urlConn.setRequestProperty("Referer", url);
            urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.00; Windows 98)");
            urlConn.setRequestProperty("Pragma", "no-cache");
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            for (int i = 0, l = headers.length; i < l; i++)
            {
                urlConn.setRequestProperty(headers[i].name, headers[i].value);
            }
            bis = new BufferedInputStream(urlConn.getInputStream());
            while ((ch = bis.read(buf)) != -1)
            {
                baos.write(buf, 0, ch);
            }
            shtml = baos.toString(encoding);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            throw e;
        }
        finally
        {
            try { bis.close(); } catch (Exception x) { }
            try { urlConn.disconnect(); } catch (Exception x) { }
            try { baos.close(); } catch (Exception x) { }

            urlConn = null;
            oUrl = null;
            bis = null;
            baos = null;
        }
        return shtml;
    }

    // 发请HTTP(METHOD:GET)请求,下载指定URL的文件
    public static String download(String url, Header[] headers, String fPath, String fName) throws Exception
    {
        int ch = -1;
        String shtml = "", extName;
        URL oUrl = null;
        HttpURLConnection urlConn = null;
        BufferedInputStream bis = null;
        byte[] buf = new byte[256];
        FileOutputStream baos = null;
        headers = null == headers ? new Header[0] : headers;

        try
        {
            //baos = new FileOutputStream(fPath);
            oUrl = new URL(url);
            urlConn = (HttpURLConnection)oUrl.openConnection();
            urlConn.setConnectTimeout(100000);
            urlConn.setRequestProperty("Accept", "*/*");
            urlConn.setRequestProperty("Referer", url);
            urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.00; Windows 98)");
            urlConn.setRequestProperty("Pragma", "no-cache");
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            for (int i = 0, l = headers.length; i < l; i++)
            {
                urlConn.setRequestProperty(headers[i].name, headers[i].value);
            }
            extName = urlConn.getContentType().toLowerCase().replaceAll("^\\w+?/(\\w+)$", "$1").replace("jpeg", "jpg");
            baos = new FileOutputStream(fPath + File.separator + fName + "." + extName);
            bis = new BufferedInputStream(urlConn.getInputStream());
            while ((ch = bis.read(buf)) != -1)
            {
                baos.write(buf, 0, ch);
            }

            shtml = fName + "." + extName;
        }
        finally
        {
            try { bis.close(); } catch (Exception x) { }
            try { urlConn.disconnect(); } catch (Exception x) { }
            try { baos.close(); } catch (Exception x) { }

            urlConn = null;
            oUrl = null;
            bis = null;
            baos = null;
        }
        return shtml;
    }

    public static String postData(String url, String data, Header[] headers, String encoding) throws Exception
    {
        int ch = -1;
        String shtml = "", ret = null;
        URL oUrl = null;
        HttpURLConnection urlConn = null;
        BufferedInputStream bis = null;
        byte[] buf = new byte[256];
        ByteArrayOutputStream baos = null;
        PrintWriter writer = null;

        try
        {
            headers = null == headers ? new Header[]{ } : headers;
            baos = new ByteArrayOutputStream(512);
            oUrl = new URL(url);
            urlConn = (HttpURLConnection)oUrl.openConnection();
            urlConn.setDoOutput(true);
            urlConn.setConnectTimeout(100000);
            urlConn.setRequestProperty("Accept", "*/*");
            urlConn.setRequestProperty("Referer", url);
            urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.00; Windows 98)");
            urlConn.setRequestProperty("Pragma", "no-cache");
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            for (int i = 0; i < headers.length; i++) urlConn.setRequestProperty(headers[i].name, headers[i].value);
            urlConn.setRequestMethod("POST");
            
            writer = new PrintWriter(urlConn.getOutputStream());
            writer.print(data);
            writer.flush();
            writer.close();

            bis = new BufferedInputStream(urlConn.getInputStream());
            while ((ch = bis.read(buf)) != -1) baos.write(buf, 0, ch);
            ret = baos.toString(encoding);
        }
        finally
        {
            try { bis.close(); } catch (Exception x) { }
            try { urlConn.disconnect(); } catch (Exception x) { }
            try { baos.close(); } catch (Exception x) { }
            try { writer.close(); } catch (Exception e) { }
            urlConn = null;
            buf = null;
            oUrl = null;
        }
        return ret;
    }

    //public static synchronized String loadUrl(String url, String encoding, HttpProxy proxy) throws Exception
    //{
    //    String shtml = "", line;
    //    URL oUrl = null;
    //    HttpURLConnection urlConn = null;
    //    DataInputStream reader = null;
    //    try
    //    {
    //        oUrl = new URL(url);
    //        if (null == proxy) urlConn = (HttpURLConnection)oUrl.openConnection();
    //        else urlConn = (HttpURLConnection)oUrl.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.ip, proxy.port)));
    //        urlConn.setDoInput(true);
    //        urlConn.setRequestProperty("Accept", "*/*");
    //        urlConn.setRequestProperty("Referer", url);
    //        urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.00; Windows 98)");
    //        urlConn.setRequestProperty("Pragma", "no-cache");
    //        urlConn.setRequestProperty("Cache-Control", "no-cache");
    //        reader = new DataInputStream(urlConn.getInputStream());
    //        while ((line = reader.readLine()) != null) shtml += new String(line.getBytes("ISO-8859-1"), encoding) + "\r\n";
    //        reader.close();
    //        urlConn.disconnect();
    //    }
    //    catch (Exception e)
    //    {
    //        throw e;
    //    }
    //    finally
    //    {
    //        if (null != reader) reader.close();
    //        urlConn = null;
    //        oUrl = null;
    //        reader = null;
    //    }
    //    return shtml;
    //}

    //获得日期字符串以作为日志文件名,一天一个
    public static String getDate()
    {
        String sDate = "";
        java.util.Date date = new java.util.Date();
        sDate = (date.getYear() + 1900) + "_" + (date.getMonth() + 1) + "_" + date.getDate();
        date = null;
        return sDate;
    }

    //获取GMT格式日期字符串如Fri, 09 Jul 2004 15:56:43 GMT
    public static String getGMTString(Date date)
    {
        date = null == date ? new Date() : date;
        String[] day = new String[] { "Sun" , "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        String ret = day[date.getDay()] + ", " + date.toGMTString();
        date = null;
        day = null;
        return ret;
    }

    //重载记录日志的方法
    public static void log(String msg,String pos)
    {
        log(msg, pos, getDate());
    }

    //记录日志
    public static void log(String msg,String pos,String fname)
    {
        String path = "";
        File file = null;
        try
        {
            file = new File("log");
            if ((!file.exists()) || (!file.isDirectory())) file.mkdir();
            file = null;
            FileUtils.writeFile("log/" + fname + ".txt",
                "Time: " + new Date().toLocaleString() + "\r\nLocation: " + pos + "\r\nDescription: " + msg + "\r\n\r\n",
                true
            );
        }
        catch (Exception ex)
        {
            System.out.println(" Error on write log to file.");
            System.out.println("\tMessage: " + ex.getMessage());
        }
    }

    //从文件中读取配置信息
    public static HashMap loadConfig(String fname) throws Exception
    {
        HashMap info = new HashMap();

        int idx = -1;
        String text = null;
        FileInputStream fis = null;
        DataInputStream dis = null;
        try
        {
            fis = new FileInputStream(fname);
            dis = new DataInputStream(fis);
            while ((text = dis.readLine()) != null)
            {
                if (0 == text.length()) continue;
                if ('#' == text.charAt(0)) continue;
                idx = text.indexOf(" = ");
                if (0 > idx) continue;
                info.put(text.substring(0, idx), text.substring(idx + 3));
                text = null;
            }

        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            try { fis.close(); }catch (Exception e) { };
            try { dis.close(); }catch (Exception e) { };
            fis = null;
            dis = null;
        }
        return info;
    }

    //编码
    public static String escape(String src)
    {
        int i;
        char j;
        if (null == src) return "";
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++)
        {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else
                if (j < 256)
                {
                    tmp.append("%");
                    if (j < 16)
                        tmp.append("0");
                    tmp.append(Integer.toString(j, 16));
                }
                else
                {
                    tmp.append("%u");
                    tmp.append(Integer.toString(j, 16));
                }
        }
        return tmp.toString();
    }

    //解码
    public static String unescape(String src)
    {
        if (null == src) return "";
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length())
        {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos + 1) == 'u')
                {
                    ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;

                }
                else
                {
                    ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                }
                else
                {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    //IP -> 数字
    public static long ipToNumeric(String ip)
    {
        long ret = 0L;
        String[] parts = ip.split("\\.");
        if (4 != parts.length) return 0;
        ret = Long.parseLong(parts[0], 10) * 256 * 256 * 256
                + Long.parseLong(parts[1], 10) * 256 * 256
                + Long.parseLong(parts[2], 10) * 256
                + Long.parseLong(parts[3], 10);
        parts = null;
        return ret;
    }
    /**
     * list转json
     * @param list
     * @return
     */
    public static <T> String convertToJson(List<T> list){
    	StringBuilder json = new StringBuilder();
    	json.append("[");
    	for(int i = 0, j = list.size(); i < j ; i++ ){
    		T t = list.get(i);
    		if(i < j - 1){
    			json.append("{");
    			json.append(t.toString());
    			json.append("},");
    		}else{
    			json.append("{");
    			json.append(t.toString());
    			json.append("}");
    		}
    		//System.out.println(t.toString());
    	}
    	json.append("]");
    	return json.toString();
    }
    
}