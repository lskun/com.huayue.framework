package com.huayue.attend.control;

import java.util.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;



import com.huayue.attend.entity.AnalysisAttendData;
import com.huayue.attend.entity.UserInfo;
import com.huayue.attend.service.AttendService;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.Page;
import com.huayue.framework.util.PageConstants;
import com.huayue.platform.entity.User;

@Controller
@SessionAttributes({"userMap","departMap","leaveTypeMap","symbolMap"})
@RequestMapping("/attend")
public class AttendExecuteController {
	
	private static Logger log = Logger.getLogger(AttendExecuteController.class);
	
	@Resource(name="attendService")
	AttendService attendService;
	
	@RequestMapping(value="/show" ,method = RequestMethod.GET)
	public @ResponseBody List<UserInfo> showAll(){
		List<UserInfo> list = null;
		
		try{
			list = attendService.showAllUserInfo();
		}catch(Exception ex){
			log.error(ex);
		}
		return list;
	}
	
	@RequestMapping(value="/data")
	public String getUserData(@RequestParam(value="id",required = true)String id,@RequestParam(value="startTime",required = false)String startTime,
			@RequestParam(value="endTime",required = false)String endTime,ModelMap model){
		
		try{
			model.put("USER_MSG", attendService.showUserAttendData(Integer.parseInt(id), startTime, endTime));
		}catch(Exception ex){
			log.error(ex);
		}
		return "user_attend";
	}
	
	@RequestMapping(value="/req")
	public String calculateAttendData(@RequestParam(value="userId",required=false)String id,HttpServletRequest request,ModelMap model){
		String startTime;
		String endTime;
		long userId;
		try{
			if(request.getMethod().equalsIgnoreCase("GET")){
				startTime = DateUtil.getMonthFirstDay() + " 00:00:00";
				endTime = DateUtil.dateToString(new Date(System.currentTimeMillis()), DateUtil.LONG_DATE_FORMAT) + " 23:59:00";
				userId = ((User)request.getSession().getAttribute("login_user")).getId();
 			}else{
 				startTime = request.getParameter("startTime");
 				endTime = request.getParameter("endTime");
 				userId = Integer.parseInt(id);
 			}
			model.addAttribute("AttendMap",attendService.calculateAttendData((int)userId, startTime, endTime));
			model.addAttribute("userId", userId);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("result", "OK");
		}catch(Exception ex){
			log.error(ex);
			model.put("result", "无对应考勤记录.");
		}
		return "attend_result";
	}
	
	@RequestMapping("personalAttend")
	public String calculateAndAnalysisAttendData(ModelMap model,HttpServletRequest request){
		HttpSession session = request.getSession();
		long userId = ((User)session.getAttribute("login_user")).getId();
		String monthDate = DateUtil.dateToString(new Date(System.currentTimeMillis()), DateUtil.MONTG_DATE_FORMAT); 
		AnalysisAttendData attendResult = null;
		try {
			attendResult = attendService.calculateAndAnalysisAttendData(monthDate, (int)userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Integer,String> cmap = DateUtil.getMonthMapping(monthDate);
		model.addAttribute("daysMapping", cmap);
		model.addAttribute("monthDate", monthDate);
		model.addAttribute("analysisAttendData", attendResult);
		return "personal_attend_result";
		
	}
	
	@RequestMapping("allAttend")
	public String allAttend(ModelMap model){
		String monthDate = DateUtil.dateToString(new Date(System.currentTimeMillis()), DateUtil.MONTG_DATE_FORMAT);
		model.addAttribute("monthDate", monthDate);
		return "allAttend"; 
	}
	
	/**
	 * 统计当月日常考勤
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("calculateAll")
	public String calculateAll(HttpServletRequest request ,ModelMap model ,HttpSession session){
		String monthDate = request.getParameter("monthDate");
		List<AnalysisAttendData> list = null ;
		Page<AnalysisAttendData> pagination = null;
		
		try{
			if(
					((User)session.getAttribute("login_user")).getPermission() < 2){
				return "permission_deny";
			}
			if(StringUtils.isBlank(request.getParameter("userId"))){
				int pageIndex = request.getParameter(PageConstants.CUR_PAGE_NAME) == null
						?
						PageConstants.FIRST_PAGE: Integer.parseInt(request.getParameter(PageConstants.CUR_PAGE_NAME));
				int pageSize = request.getParameter(PageConstants.PAGE_DATA_NAME) == null
						? 
						15: Integer.parseInt(request.getParameter(PageConstants.PAGE_DATA_NAME));
				Map<Integer,String> map = (Map<Integer,String>)session.getAttribute("userMap");
				int Count = map.size();
				Integer[] tmp = new Integer[Count];
				tmp = map.keySet().toArray(tmp);
				Integer[] ids = null;
				if((pageIndex-1) * pageSize + pageSize > tmp.length){
					ids = new Integer[tmp.length - (pageIndex - 1) * pageSize];
					System.arraycopy(tmp, (pageIndex-1)  * pageSize, ids, 0, tmp.length - (pageIndex - 1) * pageSize);
				}
				else{
					ids = new Integer[pageSize];
					System.arraycopy(tmp, (pageIndex - 1) * pageSize, ids, 0, pageSize);
				}
				pagination = new Page<AnalysisAttendData>(pageIndex,Count,pageSize);
				list = attendService.calculateAll(monthDate, ids);		
				pagination.setNextPage(pagination.nextPage());
				pagination.setPrevPage(pagination.prevPage());
				pagination.setItems(list);
			}else{
				int id = Integer.parseInt(request.getParameter("userId"));
				list = attendService.calculateAll(monthDate, new Integer[]{id});
				pagination  = new Page<AnalysisAttendData>(1,1,1);
				pagination.setItems(list);
			}
		}catch(Exception ex){
			log.error(ex);
		}
		
		Map<Integer,String> cmap = DateUtil.getMonthMapping(monthDate);
		model.addAttribute("daysMapping", cmap);
		model.addAttribute("page", pagination);
		model.addAttribute("monthDate", monthDate);
		return "total_attend_result";
	}
	
	/****
	@RequestMapping(value="/req",method = RequestMethod.GET)
	public String reqToStatistics(ModelMap map){
		map.put("startTime", DateUtil.getMonthFirstDay() + " 00:00:00");
		map.put("endTime", DateUtil.getMonthLastDay() + " 23:59:00");
		return "statistics";
	}
	****/
}
