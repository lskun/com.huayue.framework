package com.huayue.crm.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("clientCategoryService")
public class ClientCategoryService {
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(ClientCategoryService.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public Map<Integer,String> getAllCategory(){
		String sql = "SELECT ID,NAME FROM CRM_CLIENT_CATEGORY";
		Map<Integer,String> categories = new HashMap<Integer,String>();
		
		List list = jdbcTemplate.queryForList(sql);
		for(int i = 0 ; i < list.size();i++){
			Map map = (Map)list.get(i);
			categories.put((Integer)map.get("ID"), (String)map.get("NAME"));
		}
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getAllNations(){
		String sql = "SELECT ID,NAME FROM CRM_NATION";
		Map<String,String> nations = new HashMap<String,String>();
		List list = jdbcTemplate.queryForList(sql);
		for(int i = 0,j=list.size(); i < j;i++){
			Map map = (Map)list.get(i);
			nations.put(String.valueOf(map.get("ID")), (String)map.get("NAME"));
		}
		return nations;
	}
	
	/**
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	***/
	
}
