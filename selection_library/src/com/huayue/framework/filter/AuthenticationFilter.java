/**
 * 
 */
package com.huayue.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.library.domain.User;

/**
 * @author lsk0414
 *
 */
public class AuthenticationFilter implements Filter{
	
	private static final Logger log = Logger.getLogger(AuthenticationFilter.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean auth = false;
		String url;
		User user;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		try{
			url = req.getRequestURI();
			user = (User)req.getSession().getAttribute("login_user");
			
			if (url.equals("/")) auth = true;
            if (url.endsWith(".jsp")) auth = true;
            if (url.equals("/login.jsp")) auth = false;
            if (url.endsWith(".do")) auth = true;
			if (url.equals("/user/login.do")) auth = false;		
			
			if (auth && null == user){
				resp.sendRedirect("/login.jsp");
				return;
			}
			chain.doFilter(req, resp);
		}catch(Exception ex){
			log.error(ex);
			resp.sendRedirect("/404.jsp");
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
