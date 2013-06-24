package com.huayue.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class BBString
{

    public static String MD5(String a)
    {
        return MD5.MD5Encode(a);
    }

    public static String getAbsoluteURL(HttpServletRequest request, String spec) throws Exception
    {
        return new java.net.URL(new java.net.URL(request.getRequestURL().toString()), spec).toString();
    }

    public static void main(String[] args)
    {
        Matcher matcher = null;
        Pattern pattern = Pattern.compile("(\\w+)=([^&#]+)");

        try
        {
            matcher = pattern.matcher("a=fdewr%432dsf32%u234dfjfkasdjfljl#&dsef=fdafe&name=matrixy&password=34223jkldfs&time=1232190392&rnd=0.344143214321432");
            while (matcher.find())
            {
                System.out.println(matcher.group(1) + " -> " + matcher.group(2));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static String htmlEntity(String text)
    {
        if (null == text) return "";
        return text.replaceAll("\"", "&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static HashMap getCookies(HttpServletRequest request) throws Exception
    {
        HashMap cooks = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (null == cookies) return cooks;
        for (int i = 0, l = cookies.length; i < l; i++)
        {
            cooks.put(cookies[i].getName(), cookies[i]);
        }
        cookies = null;
        return cooks;
    }

    public static HashMap parseParam(String input) throws Exception
    {
        HashMap params = new HashMap();

        String name, value;
        String[] arr = input.split("&");

        for (int i = 0, l = arr.length; i < l; i++)
        {
            if ("".equals(arr[i])) continue;
            name = arr[i].replaceAll("^([^=]+)=(.*)$", "$1");
            value = arr[i].replaceAll("^([^=]+)=(.*)$", "$2");
            value = java.net.URLDecoder.decode(value);
            params.put(name, value);
        }
        
        return params;
    }

    public static String getTime(String t)
    {
        if (t == null || t == "")
        {
            return t;
        }
        long a = Long.parseLong(t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String m = formatter.format(a);
        return m;
    }

    public static String getSDate(long t)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(t);
    }

    public static String getDate(long t)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(t);
    }


    public static String getPageSql(String sql1, String sql, int pid, int eve)
    {
        if (pid == 1)
        {
            sql = "select " + sql1 + " from (" + sql + ") where rownum <=  " + eve;
        }
        else
        {
            sql = "select " + sql1 + ",rownum as con" +
            " from (" + sql + ") where rownum <= " + eve * pid;
            sql = "select " + sql1 + " from (" + sql + ") where con>" + eve * (pid - 1);
        }
        return sql;
    }

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
                if (src.charAt(pos + 1) == 'u'){
                    ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else{
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

    public static String getPageSql(String sql1, String sql, int top)
    {
        sql = "select " + sql1 + " from (" + sql + ") where rownum <=  " + top;
        return sql;
    }

    public static String getSecCode(int uid, String email, String password)
    {
        String code = "";
        BASE64Encoder encoder = new BASE64Encoder();
        code = encoder.encode(MD5(uid + ":" + email + ":" + MD5(password)).getBytes());
        encoder = null;
        return code;
    }

    public static String reverse(String text)
    {
        if (null == text) return "";
        char ch;
        String out = "";
        if (text.length() % 2 == 1) text += " ";
        char[] chars = text.toCharArray();
        for (int i = chars.length - 1; i >= 0; i -= 2)
        {
            out += chars[i - 1] + "" + chars[i];
        }
        return out.replace("\\s*", "");
    }

    public static String[] getRightURL(String imgfile)
    {
        if (null == imgfile || "".equals(imgfile.trim())) { return null; }
        String[] url = new String[3];
        int i = imgfile.indexOf("http://img.artxun.com/hadweb/");
        BASE64Encoder encoder = new BASE64Encoder();
        if (i > -1)
        {
            imgfile = imgfile.replaceAll("^.*/(.*?)/(\\d+?)/(\\d+?)/.*.jpg$", "$1/$2/$3");
            if (imgfile.matches("^([\\w-\\d]+)/(\\d+?)/(\\d+?)$"))
            {
                url[0] = "http://img.artxun.com/hadweb/imgbyte.jsp?old=old&imgbyte=/" + imgfile + "/test.jpg";
                String sp = MD5.MD5Encode(imgfile.substring(0, imgfile.indexOf("/")));//得到爬虫名
                String md5 = imgfile.replaceAll("^.*(\\d+)/(\\d+)$", "$1$2");
                md5 = MD5.MD5Encode(md5);
                url[1] = (new StringBuffer()).append(sp).append('/').append(md5.substring(0, 2)).append('/').append(md5).toString();
                url[2] = "http://img4.artxun.com/img?sp=" + sp + "&id=" + md5 + "&s=" + reverse(encoder.encode(url[0].getBytes()).replaceAll("\\s*", "")) + "&is=thumbnail_400_400.jpg";
                return url;
            }
        }
        else if (imgfile.indexOf("Resource") > -1 || imgfile.indexOf("Downloads") > -1)
        {
            imgfile = imgfile.replace("Downloads/", "");
            imgfile = imgfile.replace("Resource/", "");
            imgfile = imgfile.replace(".jpg", "");
            if (imgfile.matches("^([\\w-\\d]+)/(\\d+?)/(\\d+?)$"))
            {
                url[0] = "http://img.artxun.com/hadweb/imgbyte.jsp?old=old&imgbyte=/" + imgfile + "/test.jpg";
                String sp = MD5.MD5Encode(imgfile.substring(0, imgfile.indexOf("/")));//得到爬虫名
                String md5 = imgfile.replaceAll("^.*(\\d+)/(\\d+)$", "$1$2");
                md5 = MD5.MD5Encode(md5);
                url[1] = (new StringBuffer()).append(sp).append('/').append(md5.substring(0, 2)).append('/').append(md5).toString();
                url[2] = "http://img4.artxun.com/img?sp=" + sp + "&id=" + md5 + "&s=" + reverse(encoder.encode(url[0].getBytes()).replaceAll("\\s*", "")) + "&is=thumbnail_400_400.jpg";
            }
            return url;
        }
        else
        {
            url[0] = imgfile;
            String md5 = MD5.MD5Encode(imgfile);
            String sp = imgfile.replaceAll("^http://(.*?)(?:[:]\\d+)?/.*$", "$1");
            sp = MD5.MD5Encode(sp.replace(".", "_").toLowerCase());
            url[1] = (new StringBuffer()).append(sp).append('/').append(md5.substring(0, 2)).append('/').append(md5).toString();
            url[2] = "http://img4.artxun.com/img?sp=" + (sp) + "&id=" + md5 + "&s=" + reverse(encoder.encode(imgfile.getBytes()).replaceAll("\\s*", "")) + "&is=thumbnail_400_400.jpg";
            return url;
        }
        return null;
    }

    public static String getImageUrl(String url)
    {
        String[] info = getRightURL(url);
        if (null == info) return null;
        return info[2];
    }
    public static String get_image_url(String url)
    {
        String[] info = getRightURL(url);
        if (null == info) return null;
        return info[2];
    }

    public static String getImageUrl(String url, String is)
    {
        url = get_image_url(url);
        url = url == null ? "" : url.replace("thumbnail_400_400.jpg", is);
        url = url.replace("img?sp=", "pictures/").replace("&id=", "/").replace("&s=", "/").replace("&is=", "/");

        char ch = url.charAt(65);
        if (ch == '4' || ch == '5' || ch == '6' || ch == '7') url = url.replaceAll("img4.artxun.com", "img401.artxun.com");
        if (ch == '8' || ch == '9' || ch == 'a' || ch == 'b') url = url.replaceAll("img4.artxun.com", "img402.artxun.com");
        if (ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f') url = url.replaceAll("img4.artxun.com", "img403.artxun.com");

        return url;
    }
}
