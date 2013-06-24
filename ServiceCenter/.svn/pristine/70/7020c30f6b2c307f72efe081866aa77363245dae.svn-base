package com.huayue.sms.operator;

import java.util.ArrayList;  
import java.util.List;  
import javax.crypto.spec.SecretKeySpec;  

import org.apache.log4j.Logger;
import org.smslib.AGateway;  
import org.smslib.AGateway.GatewayStatuses;  
import org.smslib.AGateway.Protocols;  
import org.smslib.ICallNotification;  
import org.smslib.IGatewayStatusNotification;  
import org.smslib.IInboundMessageNotification;  
import org.smslib.IOrphanedMessageNotification;  
import org.smslib.InboundMessage;  
import org.smslib.InboundMessage.MessageClasses;  
import org.smslib.Library;  
import org.smslib.Message.MessageTypes;  
import org.smslib.Service;  
import org.smslib.crypto.AESKey;  
import org.smslib.modem.SerialModemGateway;  

import com.huayue.sms.service.SmsReceiveService;

  
public class ReadMessages {
	
	private static final Logger log = Logger.getLogger(ReadMessages.class);
	
	private static final Service srv = Service.getInstance();
	
	static
	{
		
		ReadMessages rmsgs = new ReadMessages();
		// Create the notification callback method for inbound & status report  
        // messages. 
		InboundNotification inboundNotification = rmsgs.new InboundNotification();
		
		// Create the notification callback method for inbound voice calls.  
		CallNotification callNotification = rmsgs.new CallNotification();
		
		//Create the notification callback method for gateway statuses. 
		GatewayStatusNotification statusNotification = rmsgs.new GatewayStatusNotification();
		
		OrphanedMessageNotification orphanedMessageNotification = rmsgs.new OrphanedMessageNotification();
		
		try
		{
			// Create the Gateway representing the serial GSM modem.
			SerialModemGateway gateway = new SerialModemGateway(Constants.Modem_ID,Constants.Com_Port,Constants.Bit_Rate,Constants.Manufacturer,Constants.Model);
			
			// Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway... 
			gateway.setProtocol(Protocols.PDU);
			
			gateway.setInbound(true);
			gateway.setOutbound(true);
			
			// Let SMSLib know which is the SIM PIN.  
            gateway.setSimPin("0000");  
            
            srv.setInboundMessageNotification(inboundNotification);
            srv.setCallNotification(callNotification);
            srv.setGatewayStatusNotification(statusNotification);
            srv.setOrphanedMessageNotification(orphanedMessageNotification);
            
            srv.addGateway(gateway);
            srv.startService();
            
		}catch(Exception ex){
			log.error(ex);
		}
	}
	
	public List<InboundMessage> recieveMsg() throws Exception  
    {  
        // Define a list which will hold the read messages.  
        List<InboundMessage> msgList = null;  
        SmsReceiveService rev = null; 
        try
        {
	         
	        // Read Messages. The reading is done via the Service object and  
	        // affects all Gateway objects defined. This can also be more directed to a specific  
	        // Gateway - look the JavaDocs for information on the Service method calls.  
	        msgList = new ArrayList<InboundMessage>();  
	        srv.readMessages(msgList, MessageClasses.ALL);  
	        for(InboundMessage msg : msgList){
	        	System.out.println(msg.getDstPort());
	        	System.out.println(msg.getText() + "--------" + msg.getSmscNumber());
	        }
	        rev = new SmsReceiveService(msgList);
	        rev.saveMessages();
	        // Sleep now. Emulate real world situation and give a chance to the notifications  
	        // methods to be called in the event of message or voice call reception.  
	        log.info("Now Sleeping - Hit <enter> to stop service.");   
        }  
        catch (Exception ex)  
        {  
            log.error(ex); 
            throw ex;
        }  
        
        return msgList;
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
	
    public class InboundNotification implements IInboundMessageNotification  
    {  
        public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg)  
        {  
            if (msgType == MessageTypes.INBOUND) System.out.println(">>> New Inbound message detected from Gateway: "   
            		+ gateway.getGatewayId());  
            else if (msgType == MessageTypes.STATUSREPORT) System.out.println(">>> New Inbound Status " +   
            		"Report message detected from Gateway: " + gateway.getGatewayId());  
            System.out.println(msg);  
        }  
    }  
  
    public class CallNotification implements ICallNotification  
    {  
        public void process(AGateway gateway, String callerId)  
        {  
            System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);  
        }  
    }  
  
    public class GatewayStatusNotification implements IGatewayStatusNotification  
    {  
        public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)  
        {  
            System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);  
        }  
    }  
  
    public class OrphanedMessageNotification implements IOrphanedMessageNotification  
    {  
        public boolean process(AGateway gateway, InboundMessage msg)  
        {  
            System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());  
            System.out.println(msg);  
            // Since we are just testing, return FALSE and keep the orphaned message part.  
            return false;  
        }  
    }  
  
    public static void main(String args[])  
    {  
        ReadMessages app = new ReadMessages();  
        try  
        {  
            app.recieveMsg();  
        }  
        catch (Exception e)  
        {  
            log.error(e);  
        }  
    }  
}
