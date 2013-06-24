/**
 * 
 */
package com.huayue.apply.smsservice;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.smslib.InboundMessage;

import com.huayue.framework.util.Tool;
import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;


/**
 * @author lsk0414
 *
 */
public class SmsCacheContainer {
	
	private static ConcurrentMap<String,ArrayList<Message>> caches = new ConcurrentHashMap<String,ArrayList<Message>>();
	
	private static int count = 0;
	
	/**
	 * 缓存记录ID自增+1
	 */
	public synchronized static void add(){
		count += 1;
	}
	
	public synchronized static void refresh(){
		count = 0;
	}
	
	/**
	 * 判断缓存中是否存在对应串口GateWay的映射
	 * @param gateway --GSM MODEM串口地址
	 * @return
	 */
	public static boolean hasGateWay(String gateway){
		return caches.containsKey(gateway);
	}
	
	/**
	 * 把接收的短信放入缓存Map中
	 * @param msg
	 */
	public static void pushToContainer(InboundMessage msg){
		String gateWay = msg.getGatewayId();
		if(caches.get(gateWay) == null) {
			caches.put(gateWay, new ArrayList<Message>());
			add();
			caches.get(gateWay).add(new Message(
					count,InterativeService.packagingNum(
							msg.getOriginator()),msg.getText(),msg.getDate().getTime())
			);
		}
		else{
			add();
			caches.get(gateWay).add(new Message(
					count,InterativeService.packagingNum(
							msg.getOriginator()),msg.getText(),msg.getDate().getTime()));			
		}
			
	}
	
	/**
	 * 根据绑定的号码获取对应的接收记录链表,并转换成json格式
	 * @param mobiles
	 * @param lastNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String listReceiveMsgs(String[] mobiles, int lastNo) throws Exception{
		if(mobiles.length == 0) throw new Exception(Constants.MOBILE_LENGTH_NULL);
		ArrayList<Message> list = new ArrayList<Message>();
		
		for(String str : mobiles){
			if(!InterativeService.map.containsKey(str)) throw new Exception(Constants.MOBILE_NO_EXIST);
			ArrayList<Message> tmp = caches.get(InterativeService.map.get(str)) ;
			if(tmp == null) continue;
			if(tmp.size() == 0) continue;
			for(Message msg : tmp ){
				if(msg.getId() > lastNo) list.add(msg);
			}
		}
		Collections.sort(list, new Comparator(){

			public int compare(Object o1, Object o2) {
				Message msg1 = (Message)o1;
				Message msg2 = (Message)o2;
				
				if(msg1.getId() > msg2.getId()) return -1;
				if(msg1.getId() < msg2.getId()) return 1;
				return 0;
			}
			
		});
		return Tool.convertToJson(list);
	}
	/**
	 * 清除对应串口上的短信缓存记录
	 * @param mobiles
	 * @throws Exception
	 */
	public static void removeCache(String[] mobiles)throws Exception{
		//if(mobiles.length == 0) throw new Exception(Constants.MOBILE_LENGTH_NULL);
		
		for(String str : mobiles){
			if(!InterativeService.map.containsKey(str)) throw new Exception(Constants.MOBILE_NO_EXIST);
			caches.remove(InterativeService.map.get(str));
		}
	}
	
	/**
	 * 清除缓存
	 */
	public static void clearAll(){
		caches.clear();
		refresh();
	}
	
	/**
	 * 根据ID从缓存中删除对应的记录
	 * @param id 缓存记录Id
	 */
	public static void delCacheRecord(int id){
		boolean flag = true;
		for(Map.Entry<String, ArrayList<Message>> entry : caches.entrySet()){
			ArrayList<Message> list = entry.getValue();
			for(Message msg : list){
				if(msg.getId() == id) {
					caches.get(entry.getKey()).remove(msg);
					flag = false;
					break;
				}
			}
			if(!flag) break;
		}
	}
	
	/**
	 * 获取缓存中所有的记录
	 * @return
	 */
	public static ArrayList<Message> listCaches(){
		ArrayList<Message> msgs = new ArrayList<Message>();
		for(Map.Entry<String, ArrayList<Message>> entry : caches.entrySet()){
			msgs.addAll(entry.getValue());
		}
		
		Collections.sort(msgs,new Comparator(){

			public int compare(Object o1, Object o2) {
				Message m1 = (Message)o1;
				Message m2 = (Message)o2;
				
				if(m1.getId() > m2.getId()) return -1;
				if(m1.getId() < m2.getId()) return 1;
				return 0;
			}
			
		});
		
		return msgs;
	}
	
}
