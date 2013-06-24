package com.huayue.apply;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenGen {
	
	public static final String TOKEN_FOR_SUBMIT = "token";
	
	private static TokenGen instance = new TokenGen();

	private TokenGen() {
	}

	public static TokenGen getInstance() {
		return instance;
	}

	public synchronized boolean isTokenValid(HttpServletRequest request) {
		// 没有session,判为非法
		HttpSession session = request.getSession(false);
		if (session == null)
			return false;

		// session中不含token,
		// 说明form被提交过后执行了resetToken()清除了token
		// 判为非法
		String stoken = (String) session.getAttribute(TOKEN_FOR_SUBMIT);
		if (stoken == null)
			return false;

		// request请求参数中不含token,
		// 判为非法
		String rtoken = request.getParameter(TOKEN_FOR_SUBMIT);
		if (rtoken == null)
			return false;

		// request请求中的token与session中保存的token不等,判为非法
		return stoken.equals(rtoken);
	}

	public synchronized void saveToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Random rand = new Random();
		Double d = rand.nextDouble();
		session.setAttribute(TOKEN_FOR_SUBMIT, d.toString());
	}
}
