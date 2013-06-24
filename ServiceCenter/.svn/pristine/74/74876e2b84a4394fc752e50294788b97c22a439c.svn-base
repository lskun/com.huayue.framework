package com.huayue.framework.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet Filter implementation class EncodingFilter
 */
public class EncodingFilter implements Filter {
	
	private String encoding = null;
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        
		//String requestURI = request.getRequestURI();
        //System.out.println(requestURI);
        Map map = request.getParameterMap();
        
        for(Iterator it = map.keySet().iterator(); it.hasNext();)
        {
            String name = (String)it.next();
            Object value = map.get(name);
            if(value instanceof String[])
            {
                String strArray[] = (String[])value;
                for(int i = 0; i < strArray.length; i++)
                {
                    strArray[i] = new String(strArray[i].toString().getBytes("iso-8859-1"), encoding);                  
                }
                value = strArray;
            }
        }
    
    	request.setCharacterEncoding(encoding);
    	response.setCharacterEncoding(encoding);
			
        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
