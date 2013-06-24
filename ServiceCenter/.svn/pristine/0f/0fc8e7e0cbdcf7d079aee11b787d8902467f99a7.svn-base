package com.huayue.crm.control;

import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayue.crm.base.util.BinaryCategoryUtil;
import com.huayue.crm.dao.ClientDao;
import com.huayue.crm.domain.Client;
import com.huayue.crm.service.ClientCategoryService;
import com.huayue.framework.util.DateUtil;
import com.huayue.framework.util.Format;
import com.huayue.framework.util.PageInfo;



@Controller
@RequestMapping("/crm")
public class ClientManagerController {
	
	public static final Logger log = Logger.getLogger(ClientManagerController.class);
	
	public static final String RESULT = "result";
	
	@Resource(name = "clientCategoryService")
	ClientCategoryService clientCategoryService;
	
	@Autowired
	ClientDao clientDao;
	
	@ModelAttribute("categories")
	public Map<Integer,String> getAllCategories(){
		return clientCategoryService.getAllCategory();
	}
	
	@ModelAttribute("nations")
	public Map<String,String> getAllNations(){
		return clientCategoryService.getAllNations();
	}
	
	@RequestMapping(value = "client_add")
	public String addClient(){
		return "client_add";
	}
	
	@RequestMapping(value = "client_manager")
	public String managerClient(){
		return "client_manager";
	}
	
	@RequestMapping(value = "data_export")
	public String exportData(){
		return "data_export";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addClientInformation(Client client ,ModelMap map,HttpServletRequest request)
	{
		
		try
		{
			String[] st = request.getParameterValues("categoryId");
			client.setCategoryId(BinaryCategoryUtil.arrayToBinaryCategory(st));
			if(request.getParameter("time") == null || "".equals(request.getParameter("time")))
				client.setRegisterTime(System.currentTimeMillis());
			else
				client.setRegisterTime(DateUtil.stringtoDate(request.getParameter("time"), DateUtil.MONTG_DATE_FORMAT).getTime());
			clientDao.save(client);
			map.put(RESULT, "OK");
		}catch(Exception ex){
			ex.printStackTrace();
			log.error(ex);
			map.put(RESULT, ex.toString());
		}
		return "feedback";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public void delClientById(@RequestParam(value = "ids") String[] ids,HttpServletResponse response)
	{		
		try
		{
			System.out.println("----" + ids[0]);
			clientDao.delete(ids);
			response.getWriter().print("{\"del\":\"true\"}");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error(ex);
			try {
				response.getWriter().print("{\"del\":\"error\"}");
			} catch (IOException e) {}
		}
	}
	
	@RequestMapping("/showInfo")
	public String showClientMessage(@RequestParam(value="id")String id,ModelMap model){		
		Client client = clientDao.showInfo(Long.parseLong(id));
		model.put("ClientCategory", BinaryCategoryUtil.getCategoryExportStringValue(client.getCategoryId()));
		model.put("Client", client);
		return "client_info";
	}
	
	@RequestMapping("/editClient")
	public String editClientInformation(@RequestParam(value="id") String id ,ModelMap model){
		Client client = null;	
		
		try
		{
			client = clientDao.showInfo(Long.parseLong(id));
			model.put("Client", client);
			model.put("CategoryList", BinaryCategoryUtil.reverseToCategoryList(BinaryCategoryUtil.longToBinaryStr(client.getCategoryId())));
			model.put(RESULT, "OK");
		}
		catch(Exception ex)
		{
			model.put(RESULT, ex);
			log.error(ex);
		}
		return "edit_info";
	}
	
	@RequestMapping("/updateClient")
	public String updateClientInfo(Client client,ModelMap model)
	{
		try
		{
			clientDao.update(client);
			model.put(RESULT, "OK");
		}catch(Exception ex){
			log.error(ex);
			model.put(RESULT, ex);
		}
		return "updateback";
	}
	
	@RequestMapping("/clientListAjax")
	public @ResponseBody PageInfo listClientMsgs(
			@RequestParam(value = "pagesize",required = false ,defaultValue = "20") int pagesize,
			@RequestParam(value="pageindex",required = false,defaultValue = "1") int pageindex, HttpServletRequest request){
		
		String province ,city , county, name, unit;
		long categoryId;
		PageInfo pInfo = null;
		
		try
		{
			province = request.getParameter("deliverprovince") == null ? "" : java.net.URLDecoder.decode(request.getParameter("deliverprovince"),"UTF-8");
			city = request.getParameter("delivercity") == null ? "" : java.net.URLDecoder.decode(request.getParameter("delivercity"),"UTF-8");
			county = request.getParameter("deliverarea") == null ? "" : java.net.URLDecoder.decode(request.getParameter("deliverarea"),"UTF-8");
			name = request.getParameter("name") == null ? "" : java.net.URLDecoder.decode(request.getParameter("name"),"UTF-8");
			unit = request.getParameter("unit") == null ? "" : java.net.URLDecoder.decode(request.getParameter("unit"),"UTF-8");
			//System.out.println(request.getParameter("unit"));
			String[] st = request.getParameterValues("categoryId");
			categoryId = BinaryCategoryUtil.arrayToBinaryCategory(st);
			pInfo = clientDao.fuzzySearch(pagesize, pageindex, province, city, county, name, categoryId, unit);
		}
		catch(Exception ex)
		{
			log.error(ex);
		}
		
		return pInfo;
	}
	
	@RequestMapping(value="/duplicateRecordManager",method = RequestMethod.GET)
	public String listDuplicateRecord(ModelMap map){
		
		try{
			map.put("recordsMap",clientDao.listDuplicateRecord());
		}catch(Exception ex){
			log.error(ex);
			//ex.printStackTrace();
		}
		return "recordsMap";
	}
	
	@RequestMapping(value="numberCollection",method = RequestMethod.GET)
	public String numberCollection(ModelMap map,HttpServletRequest request){
		map.put("nums", request.getParameter("ids").trim().replaceAll("<br/>", "\r\n"));
		return "crm/multi_send";
	}
}
