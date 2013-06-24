package com.huayue.framework.util;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletUtils
{
    public static final String FM_INT = "^\\d+$";
    public static final String FM_FLOAT = "^\\d+\\.\\d+$";
    public static final String FM_DATE = "^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$";
    
    public static void checkError(ServletRequest request) throws Exception
    {
        String rst = (String)request.getAttribute("result");
        if (!"OK".equals(rst)) throw new Exception(rst);
    }

    public static void sendError(ServletRequest request, ServletResponse response, Exception ex) throws Exception
    {
        response.getWriter().println(Utils.getTipHtml("错误提示", ex.getMessage(), ((HttpServletRequest)request).getHeader("referer")));
        response.getWriter().flush();
    }
    /*********
    public static com.bobaoo.data.User getLoginOperator(HttpServletRequest request) throws Exception
    {
        String uid, uname;
        HashMap cookies = getCookies(request);
        if (!cookies.containsKey("user_id")) return null;
        if (!cookies.containsKey("username")) return null;
        uid = Format.clear(((Cookie)cookies.get("user_id")).getValue());
        uname = Format.clear(((Cookie)cookies.get("username")).getValue());
        return new com.bobaoo.data.User(Integer.parseInt(AES.decode(uid, Constants.AES_KEY_COOKIE)), 0L, AES.decode(uname, Constants.AES_KEY_COOKIE), null, null);
    }
	***********/
    public static Object getSession(HttpServletRequest request, String name) throws Exception
    {
        return request.getSession().getAttribute(name);
    }

    public static void sendRedirect(String url, ServletResponse response) throws Exception
    {
        ((HttpServletResponse)response).sendRedirect(url);
    }

    public static void logError(ServletRequest request, Exception ex)
    {
        ex.printStackTrace();
        request.setAttribute("result", ex.toString());
    }

    public static void logOK(ServletRequest request)
    {
        request.setAttribute("result", "OK");
    }

    public static String getFile(ServletRequest request, String fname)
    {
        return request.getRealPath(fname);
    }

    public static String getUserAgent(HttpServletRequest request)
    {
        return request.getHeader("User-Agent");
    }

    public static void set(ServletRequest request, String name, Object obj)
    {
        request.setAttribute(name, obj);
    }

    public static void set(ServletRequest request, String name, int val)
    {
        request.setAttribute(name, String.valueOf(val));
    }

    public static void set(ServletRequest request, String name, long val)
    {
        request.setAttribute(name, String.valueOf(val));
    }

    public static Object get(ServletRequest request, String name)
    {
        return request.getAttribute(name);
    }

    public static int getIntAttr(ServletRequest request, String name)
    {
        Object obj = request.getAttribute(name);
        return obj == null ? 0 : Integer.parseInt(String.valueOf(obj));
    }

    public static String getStringAttr(ServletRequest request, String name)
    {
        Object obj = request.getAttribute(name);
        return obj == null ? "" : String.valueOf(obj);
    }

    public static long[] getLongArray(ServletRequest request, String name) throws Exception
    {
        long[] array;
        String[] arr = request.getParameterValues(name);
        if (null == arr) return new long[0];
        array = new long[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            array[i] = Long.parseLong(arr[i]);
        }
        return array;
    }

    public static String[] getStringArray(ServletRequest request, String name) throws Exception
    {
        String[] arr = request.getParameterValues(name);
        if (arr == null) return new String[0];
        else return arr;
    }

    public static int[] getIntArray(ServletRequest request, String name) throws Exception
    {
        int[] array;
        String[] arr = request.getParameterValues(name);
        if (null == arr) return new int[0];
        array = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            array[i] = Integer.parseInt(arr[i]);
        }
        return array;
    }

    public static HashMap<String, String> getParameters(ServletRequest request) throws Exception
    {
        HashMap<String, String> map = new HashMap<String, String>();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements())
        {
            String key = (String)enums.nextElement();
            String val = (String)request.getParameter(key);
            val = null == val ? null : new String(val.getBytes("ISO-8859-1"), "UTF-8");
            map.put(key, val);
        }
        return map;
    }

    public static String getReferer(HttpServletRequest request)
    {
        return request.getHeader("referer");
    }

    public static String getQueryString(HttpServletRequest request) throws Exception
    {
        return request.getQueryString();
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

    public static void forwardTo(String uri, ServletRequest request, ServletResponse response) throws ServletException, java.io.IOException
    {
        request.getRequestDispatcher(uri).forward(request, response);
    }

    public static java.util.Date getDate(ServletRequest request, String name, java.util.Date val) throws Exception
    {
        name = request.getParameter(name);
        if (null == name) return val;
        name = name.trim();
        if (!name.matches(FM_DATE)) return val;
        return DateUtils.getDate(name);
    }

    public static String getString(ServletRequest request, String name, String regex, String val) throws Exception
    {
        name = request.getParameter(name);
        if (null == name || "".equals(name)) return val;
        name = Format.Q2BChange(name, true);
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8").trim();
        if (!name.matches(regex)) return val;
        return name;
    }

    public static String getString(ServletRequest request, String name) throws Exception
    {
        String val = request.getParameter(name);
        if (null == val || "".equals(val)) return val;
        val = Format.Q2BChange(val, true);
        val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
        return val;
    }

    public static int getInt(ServletRequest request, String name)
    {
    	name = request.getParameter(name);
        if (null == name) throw new NumberFormatException(name);
        name = Format.Q2BChange(name.trim(),true);
        if (!name.matches(FM_INT)) throw new NumberFormatException(name);
        return Integer.parseInt(name);
    }

    public static int getInt(ServletRequest request, String name, int val)
    {
        name = request.getParameter(name);
        if (null == name || "".equals(name)) return val;
        name = Format.Q2BChange(name.trim(),true);
        if (name.matches(FM_FLOAT)) return (int)Float.parseFloat(name);
        if (name.matches(FM_INT)) return Integer.parseInt(name);
        return val;
    }

    public static long getLong(ServletRequest request, String name)
    {
    	name = request.getParameter(name);
        if (null == name) throw new NumberFormatException(name);
        name = Format.Q2BChange(name.trim(),true);
        if (!name.matches(FM_INT)) throw new NumberFormatException(name);
        return Long.parseLong(name);
    }

    public static long getLong(ServletRequest request, String name, long val)
    {
    	name = request.getParameter(name);
        if (null == name || "".equals(name)) return val;
        name = Format.Q2BChange(name.trim(),true);
        if (!name.matches(FM_INT)) return val;
        return Long.parseLong(name);
    }

    public static String getQueryString(ServletRequest request, String name, float val) throws Exception
    {
        return getQueryString(request, name, String.valueOf(val));
    }

    public static String getQueryString(ServletRequest request, String name, int val) throws Exception
    {
        return getQueryString(request, name, String.valueOf(val));
    }

    public static String getQueryString(ServletRequest request, String name, String val) throws Exception
    {
        boolean exists = false;
        String url = "?", key, value;
        String[] vals;
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements())
        {
            key = (String)enums.nextElement();
            vals = request.getParameterValues(key);
            vals = vals == null ? new String[0] : vals;

            for (int i = 0; i < vals.length; i++)
            {
                value = new String(vals[i].getBytes("ISO-8859-1"), "UTF-8");
                if (name.equals(key)) { value = val; exists = true; }
                url += key + "=" + java.net.URLEncoder.encode(value, "UTF-8") + "&";
            }
        }
        if (!exists) url += name + "=" + java.net.URLEncoder.encode(val, "UTF-8");
        return url;
    }

    public static void setCookie(String name, String val, String domain, int expiry, HttpServletResponse response)
    {
        Cookie cookie = new Cookie(name, val);
        cookie.setPath("/");
        cookie.setDomain(domain);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }
}
