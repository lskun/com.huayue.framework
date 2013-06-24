package com.huayue.sms.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.huayue.attend.service.AttendService;
import com.huayue.framework.util.MD5;
import com.huayue.framework.util.Page;
import com.huayue.platform.dao.UserDao;
import com.huayue.platform.entity.User;
import com.huayue.platform.service.UserService;

@Controller
@SessionAttributes({"userMap","departMap","leaveTypeMap","symbolMap","deptMap","userDefaultDeptMap"})
@RequestMapping("/user")
public class LoginController 
{	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@Resource(name="attendService")
	AttendService attendService;
	
	@ModelAttribute("userMap")
	public Map<Integer,String> getUserMap(){
		Map<Integer,String> map = attendService.getUserMap();
		return map;
	}
 	
	@ModelAttribute("departMap")
	public Map<Integer,String> getDepartMap(){
		return attendService.getDepartmentMap();
	}
	
	@ModelAttribute("leaveTypeMap")
	public Map<Integer,String> getLeaveMap(){
		return attendService.getLeaveTypeMap();
	}
	
	@ModelAttribute("symbolMap")
	public Map<Integer,String> getSymbolMap(){
		return attendService.getSymbolMap();
	}
	
	@ModelAttribute("deptMap")
	public Map<Integer,Integer> getDeptMap(){
		return attendService.getDeptMap();
	}
	
	@ModelAttribute("userDefaultDeptMap")
	public Map<Integer,Integer> getUserDefaultDeptMap(){
		return attendService.getUserDefaultDeptMap();
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String doLogin(User user ,HttpServletRequest request,HttpServletResponse response,ModelMap model)
	{		
			if(null == user.getName() || "".equals(user.getName())|| null == user.getPassword() || "".equals(user.getPassword())){
				model.put("error_message", "用户名或密码不能为空!");
				return "login";
			}else{
				//用户密码加密方式 : md5(username + md5(password))
				user.setPassword(MD5.MD5Encode(user.getName() + MD5.MD5Encode(user.getPassword())));
				if(null != (user = userDao.login(user))){
					HttpSession session = request.getSession();
					//save one hour
					session.setMaxInactiveInterval(60 * 60);
					session.setAttribute("login_user", user);
					return "index";
				}else{
					model.put("error_message", "用户名或密码有误!");
					return "login";
				}
			}
			
	}
	
	@RequestMapping(value="update")
	public void update(User user,HttpServletResponse response){
		
		try {
			String password = user.getPassword();
			user.setPassword(MD5.MD5Encode(user.getName() + MD5.MD5Encode(password)));
			userDao.update(user);
			response.getWriter().print(password);
		} catch (Exception e) {
			try { response.getWriter().print(e.toString()); } catch (IOException e1) {}
			log.error(e);
		}
	}
	
	@RequestMapping(value="logout")
	public void logOut(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("login_user");
	    try {
			response.sendRedirect("/login.jsp");
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	@RequestMapping(value="find")
	public void find(@RequestParam(value="id",required = true)int id){
		try{
			User user = userDao.findByID(id);
			System.out.println(user.getId() + "-------" + user.getName() + "----------" + user.getPassword() + "---" + user.getCreateTime() + "---" +user.getPermission() + "---" + user.getModifiedTime());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value="findAll",method = RequestMethod.GET)
	public void findAll(){
	}
	
	@RequestMapping(value="delete",method = RequestMethod.GET)
	public void delete(@RequestParam(value="id",required = true)int id){
		userDao.deleteByID(id);
	}
	
	@RequestMapping(value="add")
	public void add(User user){
		userDao.addObj(user);
	}
	
	@RequestMapping(value="findByPage")
	public void findByPage(HttpServletRequest request){
		long startTime = System.currentTimeMillis();
		Page<User> pagination = userService.findByPage(request,"com.huayue.platform.mapper.UserMapper.findAllUsers");
		System.out.println("take :" + (System.currentTimeMillis() - startTime) + " ms ");
		System.out.println(" count : " + pagination.getRecordCount() + " index : " + pagination.getPageIndex() + " size " + pagination.getPageSize());
		for(User user : pagination.getItems()){
			System.out.println("id : " + user.getId() + "  name : " + user.getName() + " password : " + user.getPassword() + " deptId : " + user.getDeptId());
		}
	}
	
	/**
	@RequestMapping("selectMap")
	public void selectForMap(){
		ArrayList<User> list = userDao.select();
		for(User user : list){
			System.out.println(user.getId() +  "-------" + user.getName() + "------" + user.getPassword());
			user.setPassword(MD5.MD5Encode(user.getName() + MD5.MD5Encode(user.getPassword())));
			userDao.update(user);
		}
	}
	**/
}
