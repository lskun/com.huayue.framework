package com.huayue.crm.control;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.huayue.crm.service.RegionService;

import java.util.*;


@Controller
@RequestMapping("/search")
public class RegionSearchController {
	private static Logger log = Logger.getLogger(RegionSearchController.class);
	
	@Resource(name="regionService")
	RegionService regionService;
	
	@ModelAttribute("provinces")
	public Map<Integer,String> getProvinces(){
		return regionService.getProvinceMap();
	}
	
	@RequestMapping(value="/listCity",method = RequestMethod.GET)
	public @ResponseBody Map<Integer,String> getCities(@RequestParam(value="id") int id){
		return regionService.getCityMapByProvince(id);
	}
	
	@RequestMapping(value="/listArea",method = RequestMethod.GET)
	public @ResponseBody Map<Integer,String> getAreas(@RequestParam(value="id") int id){
		return regionService.getAreaMapByCity(id);
	}
}
