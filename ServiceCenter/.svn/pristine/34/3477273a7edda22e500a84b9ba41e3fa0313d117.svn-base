package com.huayue.sms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huayue.platform.entity.User;
import com.huayue.sms.service.UserService;


/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(Login.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username ,password;
		User user = null;
		ArrayList<User> list = null;
		UserService srv = new UserService();
		request.setCharacterEncoding("UTF-8");
		
		try
		{
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			username = null == username ? "" : username.trim();
			password = null == password ? "" : password.trim();
			
			list = srv.getUserMessages();
			for(int i = 0 ,j = list.size() - 1;i <= j;i ++ ){
				user = list.get(i);
				if(username.equals(user.getName()) && password.equals(user.getPassword())){
					request.getSession().setAttribute("login_user", user);
					response.sendRedirect("/index.jsp");
					return;
				}
				if(i == j){
					if(!user.getName().equals(username) || !user.getPassword().equals(password)) throw new Exception("用户名或密码错误,请重新输入!");
				}
			}
		}catch(Exception ex){
			log.error(ex);
			request.setAttribute("error_message", "用户名或密码错误,请重新输入!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
