package com.huayue.sms.operator;

import org.apache.log4j.Logger;
import org.smslib.AGateway;  
import org.smslib.IOutboundMessageNotification;  
import org.smslib.Library;  
import org.smslib.Message.MessageEncodings;
import org.smslib.OutboundMessage;  
import org.smslib.Service;  
import org.smslib.modem.SerialModemGateway; 

public class SendMessage 
{
	//http://wenku.baidu.com/view/746de5d776eeaeaad1f33091.html
	private static final Logger log = Logger.getLogger(SendMessage.class);
	
	private String mobile;
	
	private String content;
	
	private static final Service srv = Service.getInstance();
		
	public SendMessage(){}
	
	public SendMessage(String mobile,String content){
		this.mobile = mobile;
		this.content = content;
	}
	
	//initialize sms Service...
	static
	{
		OutboundNotification outboundNotification = new OutboundNotification();
		log.info(Library.getLibraryDescription());
		log.info("Version: " + Library.getLibraryVersion());
		
		/* 
        	modem.com1:网关ID（即短信猫端口编号） 
        	COM4:串口名称（在window中以COMXX表示端口名称，在linux,unix平台下以ttyS0-N或ttyUSB0-N表示端口名称），通过端口检测程序得到可用的端口 
        	115200：串口每秒发送数据的bit位数,必须设置正确才可以正常发送短信，可通过程序进行检测。常用的有115200、9600 
        	Huawei：短信猫生产厂商，不同的短信猫生产厂商smslib所封装的AT指令接口会不一致，必须设置正确.常见的有Huawei、wavecom等厂商 
        	最后一个参数表示设备的型号，可选  
        */  
        SerialModemGateway gateway = new SerialModemGateway(Constants.Modem_ID, Constants.Com_Port, Constants.Bit_Rate, Constants.Manufacturer, Constants.Model);  
        gateway.setInbound(true);   	
        gateway.setOutbound(true);		  
        gateway.setSimPin("0000");		//sim卡锁，一般默认为0000或1234  
        
        // Explicit SMSC address set is required for some modems.  
        // Below is for VODAFONE GREECE - be sure to set your own!  
        // gateway.setSmscNumber("+18601931356");
        
        srv.setOutboundMessageNotification(outboundNotification); //发送短信成功后的回调函方法
        
        try
        {
	        srv.addGateway(gateway);  //将网关添加到短信猫服务中   
	        srv.startService();   	 
	         
	        //打印信息  
	        log.info("  Manufacturer: " + gateway.getManufacturer()); 
	        log.info("  Model: " + gateway.getModel()) ;
	        log.info("  Serial No: " + gateway.getSerialNo());
	        log.info("  SIM IMSI: " + gateway.getImsi());
	        log.info("  Signal Level: " + gateway.getSignalLevel() + " dBm");
	        log.info("  Battery Level: " + gateway.getBatteryLevel() + "%"); 
	        
        }
        catch(Exception e)
        {
        	log.error(e);
        }
        
	}
	
	public boolean send() throws Exception  
    {  
             
        // Send a message synchronously.  
        OutboundMessage msg = new OutboundMessage(mobile, content + Constants.Topic);    //参数1：手机号码 参数2：短信内容
        msg.setEncoding(MessageEncodings.ENCUCS2);
        
        try
        {
	        srv.sendMessage(msg); 
	        log.info(msg);
	        
	        //Or, send out a WAP SI message.  
	        //OutboundWapSIMessage wapMsg = new OutboundWapSIMessage("306974000000",    
	        //new URL("http://www.smslib.org/"), "Visit SMSLib now!");  
	        //Service.getInstance().sendMessage(wapMsg);  
	        //System.out.println(wapMsg);  
	        // You can also queue some asynchronous messages to see how the callbacks  
	        // are called...  
	        //msg = new OutboundMessage("309999999999", "Wrong number!");  
	        //srv.queueMessage(msg, gateway.getGatewayId());  
	        //msg = new OutboundMessage("308888888888", "Wrong number!");  
	        //srv.queueMessage(msg, gateway.getGatewayId());  
	        //log.info("Now Sleeping - Hit <enter> to terminate.");  
        
        }catch(Exception ex){
        	log.error(ex);
        	return false;
        }
         
        return true; 
    } 
	
	public void closeService()
	{
		try
		{
			srv.stopService();
		}
		catch(Exception ex)
		{
			log.error(ex);
		}
	}
	  
	/* 
	     短信发送成功后，调用该接口。并将发送短信的网关和短信内容对象传给process接口 
	*/  
	
    public static class OutboundNotification implements IOutboundMessageNotification  
    {  
        public void process(AGateway gateway, OutboundMessage msg)  
        {  
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());  
            System.out.println(msg);  
        }  
    }  
  
    public static void main(String args[])  
    {  
        SendMessage app = new SendMessage("18210918840"," 加密前：HongLonglong我要测试一下，看看有没有问题，要是有问题了肿么办啊。这可肿么办啊？？？"
        + "加密后：3C6E9F9AA736471B25770E3FF1017CB7FFCD2D38A7E24FCE403FCA42E039D7E535F7ABDF4AE7B9937C6AB17FB639A4F6161A003D2D5AD59D9E6F5F8ADB7342A7B35A1A40B8356DDBF577C8E05B194A397E5EA909CC586AD92D0CF3852681127138D0C1E1991CE75F35948B797EA04167F0403A0C58C6486C0F442E9600880899"
        + "解密后：HongLonglong我要测试一下，看看有没有问题，要是有问题了肿么办啊。这可肿么办啊？？？");  
        try  
        {  
            app.send();  
        }  
        catch (Exception e)  
        {  
        	log.error(e);
        }  
    }  
}
