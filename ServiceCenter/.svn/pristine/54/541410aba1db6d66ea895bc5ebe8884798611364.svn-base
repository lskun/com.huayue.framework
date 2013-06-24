package com.huayue.crm.service;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("regionService")
public class RegionService {
	
	private static Logger log = Logger.getLogger(RegionService.class);
	
	public static Map<Integer,String> provinces = new HashMap<Integer,String>();
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Map<Integer,String> getProvinceMap(){
		String sql = "SELECT ID,TITILE FROM PROVINCE";
		
		List list = jdbcTemplate.queryForList(sql);
		
		for(int i = 0,j = list.size();i < j;i ++){
			Map map = (Map)list.get(i);
			provinces.put((Integer)map.get("ID"), (String)map.get("TITLE"));
		}
		return provinces;
	}
	
	public Map<Integer,String> getCityMapByProvince(int id){
		String sql = "SELECT ID,TITILE FROM CITY WHERE PROVINCEID = " + id;
		Map<Integer,String> cities = new HashMap<Integer,String>();
		
		List list = jdbcTemplate.queryForList(sql);
		
		for(int i = 0,j = list.size();i < j;i++ ){
			Map map = (Map)list.get(i);
			cities.put((Integer)map.get("ID"), (String)map.get("NAME"));
		}
		
		return cities;
	}
	
	public Map<Integer,String> getAreaMapByCity(int id){
		String sql = "SELECT ID,TITLE FROM COUNTY WHERE CITYID = " + id;
		Map<Integer,String> areas = new HashMap<Integer,String>();
		
		List list = jdbcTemplate.queryForList(sql);
		
		for(int i = 0,j = list.size(); i < j;i++){
			Map map = (Map)list.get(i);
			areas.put((Integer)map.get("ID"), (String)map.get("TITLE"));
		}
		
		return areas;
	}
	
	/***
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	***/
}
