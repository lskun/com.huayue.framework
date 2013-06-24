/**
 * 
 */
package com.huayue.apply.smsservice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.smslib.InboundMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huayue.platform.entity.Message;


/**
 * @author lsk0414
 *
 */
@Controller
@RequestMapping("/manage")
public class CacheManageController {
	
	@RequestMapping(value="delCache",method = RequestMethod.GET)
	public void delCacheRecord(@RequestParam(value="id",required = true)int id,HttpServletResponse response){
		SmsCacheContainer.delCacheRecord(id);
		try {
			response.getWriter().print("{\"del\":\"true\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="clear")
	public void clearCache(HttpServletResponse response){
		SmsCacheContainer.clearAll();
		try {
			response.getWriter().print("{\"del\":\"true\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="listCache",method = RequestMethod.GET)
	public @ResponseBody ArrayList<Message> listCache(){
		ArrayList<Message> list =  SmsCacheContainer.listCaches();
		return list;
	}
	
	@RequestMapping(value="add")
	public void addToCacheContainer(Message message,HttpServletResponse response){		
		InboundMessage msg = new InboundMessage(new java.util.Date(System.currentTimeMillis()), message.getPhoneNumber(), message.getContent(), 0, null);
		msg.setGatewayId("com6");
		SmsCacheContainer.pushToContainer(msg);
		try {
			response.getWriter().print("{\"add\":\"true\"}");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
}
