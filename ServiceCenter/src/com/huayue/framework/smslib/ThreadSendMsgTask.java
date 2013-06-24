package com.huayue.framework.smslib;

import org.apache.log4j.Logger;
import org.smslib.OutboundMessage;

import com.huayue.platform.entity.Message;
import com.huayue.sms.operator.Constants;
import com.huayue.sms.service.SmsSendService;


public class ThreadSendMsgTask implements Runnable {
	
	private static final Logger log = Logger.getLogger(ThreadSendMsgTask.class);
	private String tel;
	private String msgText;
	private long id;
	public ThreadSendMsgTask(String msgText, String tel) {
		this.tel = tel;
		this.msgText = msgText;
	}
	
	public ThreadSendMsgTask(String msgText, String tel,long id) {
		this.tel = tel;
		this.msgText = msgText;
		this.id = id;
	}

	public void run() {

		// 等待Modem初始化完毕
		while(!ParamObj.initModemStatus) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try{
			OutboundMessage msg = new OutboundMessage(tel,msgText);
			if(SendReadMsger.modem.sendMsg(msg))
				new SmsSendService().updateOutboundStatus(id);

			/*******
			if(SendReadMsger.modem.sendMsg(msg)){
				Message message = new Message();
				message.setContent(msgText);
				message.setPhoneNumber(tel);
				message.setSend_time(msg.getDate().getTime());
				message.setSend_state(Constants.SEND_SUCCESS);
				
				new SmsSendService(message).updateSmsRecord();
				}
				**********/ 
		}catch(Exception ex){
			log.info(ex);
			//ex.printStackTrace();
		}
	}

}
