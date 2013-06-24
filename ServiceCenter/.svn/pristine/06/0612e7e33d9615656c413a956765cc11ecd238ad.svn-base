package com.huayue.framework.smslib;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Library;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.AGateway.Protocols;
import org.smslib.Message.MessageTypes;
import org.smslib.balancing.LoadBalancer;
import org.smslib.modem.SerialModemGateway;
import org.smslib.routing.Router;

public class Modem {
	private static Service srv;
	
	private SendReadMsger sendReadMsger = null;

	private static Modem modem;
	private static Object clockObj = Modem.class;

	public final static String MSG_FILE_PATH = "/modem.properties"; // properties/modem.properties
	
	private static final Logger log = Logger.getLogger(Modem.class);
	
	private Modem() {
		this.initService();
	}

	public static Modem newInstance() {
		synchronized (clockObj) {
			if(modem == null) {
				modem = new Modem();
			}
			return modem;
		}
	}

	/**
	 *
	 * @param tel 手机号码
	 * @param text 消息内容
	 * @param date 时间
	 */
	private void msgArrive(InboundMessage msg) {
		sendReadMsger.msgArrive(msg);
	}

	/**
	 * 发送消息.
	 * @param msg 消息内容
	 * @return true : 成功; false : 失败.
	 */
	public boolean sendMsg(OutboundMessage msg) {

		msg.setEncoding(Message.MessageEncodings.ENCUCS2);

		try {
			
			/**Collection<AGateway> col = srv.getGateways();
			for(AGateway way : col){
				System.out.println("gatewayId : " + way.getGatewayId());
				if(way.sendMessage(msg)) break;
			}
			***/
			srv.queueMessage(msg);

			log.info("gateway: " + msg.getGatewayId()  + " content :" + msg.getText() + " recipient: " + msg.getRecipient());
		} catch (Exception e) {
			log.error(" ======= 发送消息有异常 ============ " + e);
			return false;
		}
		return true;
	}

	private void initService() {

		// Define a list which will hold the read messages.
		// List<InboundMessage> msgList;

		// Create the notification callback method for inbound voice calls.
		CallNotification callNotification = new CallNotification();
		
		//Create the notification callback method for gateway statuses.
		GatewayStatusNotification statusNotification = new GatewayStatusNotification();
		
		int baudRate = 115200;
		String manufacturer = "wavecom";
		String model = "";
		
		String gatewayId_one = "";
		String comPort_one = "";
		
		String gatewayId_two = "";
		String comPort_two = "";
		
		String gatewayId_three = "";
		String comPort_three = "";
		
		String gatewayId_four = "";
		String comPort_four = "";
		
		/***
		String gatewayId_five = "";
		String comPort_five = "";
		
		String gatewayId_six = "";
		String comPort_six = "";
		**/
		Properties properties = new Properties();
		InputStream instream = this.getClass().getResourceAsStream(MSG_FILE_PATH);
		
		try {
			properties.load(instream);

			gatewayId_one = properties.getProperty("gatewayId1");
			comPort_one = properties.getProperty("comPort1");
			
			gatewayId_two = properties.getProperty("gatewayId2");
			comPort_two = properties.getProperty("comPort2");
			
			gatewayId_three = properties.getProperty("gatewayId3");
			comPort_three = properties.getProperty("comPort3");
			
			gatewayId_four = properties.getProperty("gatewayId4");
			comPort_four = properties.getProperty("comPort4");
			/**
			gatewayId_five = properties.getProperty("gatewayId5");
			comPort_five = properties.getProperty("comPort5");
			
			gatewayId_six = properties.getProperty("gatewayId6");
			comPort_six = properties.getProperty("comPort6");
			**/
			baudRate = Integer.parseInt(properties.getProperty("baudRate"));
			manufacturer = properties.getProperty("manufacturer");
			model = properties.getProperty("model") == null ? "" : properties.getProperty("model");

		} 
		catch (IOException e1) {
			log.info(" ======= no: /properties/modem.properties ============ ");
			log.error(e1);
		}

		try	{
			log.info("Example: Read messages from a serial gsm modem.");
			log.info(Library.getLibraryDescription());
			log.info("Version: " + Library.getLibraryVersion());

			// Create new Service object - the parent of all and the main interface
			// to you.
			srv = Service.getInstance();

			// Create the notification callback method for inbound & status report messages.
			InboundNotification inboundNotification = new InboundNotification(this, srv);

			// Create the Gateway representing the serial GSM modem.
			SerialModemGateway gateway1 = new SerialModemGateway(gatewayId_one, comPort_one, baudRate, manufacturer, model);

			// Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway...
			gateway1.setProtocol(Protocols.PDU);

			// Do we want the Gateway to be used for Inbound messages?
			gateway1.setInbound(true);

			// Do we want the Gateway to be used for Outbound messages?
			gateway1.setOutbound(true);

			// Let SMSLib know which is the SIM PIN.
			gateway1.setSimPin("0000");
			
			SerialModemGateway gateway2 = new SerialModemGateway(gatewayId_two,comPort_two,baudRate, manufacturer, model);
			
			gateway2.setProtocol(Protocols.PDU);

			gateway2.setInbound(true);
			
			gateway2.setOutbound(true);

			gateway2.setSimPin("0000");
			
			SerialModemGateway gateway3 = new SerialModemGateway(gatewayId_three,comPort_three,baudRate, manufacturer, model);
			
			gateway3.setProtocol(Protocols.PDU);

			gateway3.setInbound(true);
			
			gateway3.setOutbound(true);

			gateway3.setSimPin("0000");
			
			SerialModemGateway gateway4 = new SerialModemGateway(gatewayId_four,comPort_four,baudRate, manufacturer, model);
			
			gateway4.setProtocol(Protocols.PDU);

			gateway4.setInbound(true);
			
			gateway4.setOutbound(true);

			gateway4.setSimPin("0000");
			
			/**
			SerialModemGateway gateway5 = new SerialModemGateway(gatewayId_five,comPort_five,baudRate, manufacturer, model);
			
			gateway5.setProtocol(Protocols.PDU);

			gateway5.setInbound(true);
			
			gateway5.setOutbound(true);

			gateway5.setSimPin("0000");
			
			if(gatewayId_six != null){
				SerialModemGateway gateway6 = new SerialModemGateway(gatewayId_six,comPort_six,baudRate,manufacturer,model);
			
				gateway6.setProtocol(Protocols.PDU);
			
				gateway6.setInbound(true);
			
				gateway6.setOutbound(true);
			
				gateway6.setSimPin("0000");
				
				srv.addGateway(gateway6);
			}
			**/
			// Set up the notification methods.
			srv.setInboundMessageNotification(inboundNotification);
			srv.setCallNotification(callNotification);
			srv.setGatewayStatusNotification(statusNotification);						
			
			srv.getSettings().SERIAL_POLLING = true;
			srv.getSettings().SERIAL_POLLING_INTERVAL = 1000;
			srv.getSettings().SERIAL_TIMEOUT = 30000;
			
			// Add the Gateway to the Service object.
			srv.addGateway(gateway1);
			srv.addGateway(gateway2);
			srv.addGateway(gateway3);
			srv.addGateway(gateway4);
			//srv.addGateway(gateway5);
			
			// Similarly, you may define as many Gateway objects, representing
			// various GSM modems, add them in the Service object and control all of them.

			// Start! (i.e. connect to all defined Gateways)
			
			srv.startService();
					
			//srv.stopService();
			//Printout some general information about the modem.

			log.info("  Modem Information:");
			log.info("  Manufacturer: " + gateway1.getManufacturer());
			log.info("  Model: " + gateway1.getModel());
			log.info("  Serial No: " + gateway1.getSerialNo());
			log.info("  SIM IMSI: " + gateway1.getImsi());
			log.info("  Signal Level: " + gateway1.getSignalLevel() + "%");
			log.info("  Battery Level: " + gateway1.getBatteryLevel() + "%");

			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.
			//msgList = new ArrayList<InboundMessage>();
			//this.srv.readMessages(msgList, MessageClasses.ALL);
			//for (InboundMessage msg : msgList) {
			//	System.out.println(" ------- for ---- for ---- for ---- for " + msg);
			//
			//	this.srv.deleteMessage(msg);
			//}

			// Sleep now. Emulate real world situation and give a chance to the notifications
			// methods to be called in the event of message or voice call reception.
			//System.out.println("Now Sleeping - Hit <enter> to terminate.");
			//System.in.read();

		}catch (Exception e)	{
			log.error(e);
		}finally {
			//			try {
			//				this.srv.stopService();
			//			} catch (TimeoutException e) {
			//				// 
			//				e.printStackTrace();
			//			} catch (GatewayException e) {
			//				// 
			//				e.printStackTrace();
			//			} catch (IOException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			} catch (InterruptedException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
		}
	}

	public class InboundNotification implements IInboundMessageNotification	{

		private Service srv;
		private Modem modem;

		public InboundNotification(Modem modem, Service srv) {
			this.srv = srv;
			this.modem = modem;
		}

		public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg)
		{
			if (msgType == MessageTypes.INBOUND) {
				log.info(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
				
				//System.out.println(">>> bruce111111 手机号: " + msg.getOriginator() + " 内容:" + msg.getText() + " 时间: " + msg.getDate());
				//msger.msgArrive(msg.getOriginator(), msg.getText(), msg.getDate());
				//call-back
				modem.msgArrive(msg);

				try {
					if(sendReadMsger.isDeleteAfterRead()) {
						this.srv.deleteMessage(msg);
					}
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GatewayException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (msgType == MessageTypes.STATUSREPORT) {
				log.info(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
			}
			//System.out.println("111111111=" + msg.getPduUserData() + "111111111");
			//System.out.println("111111111=" + msg.getPduUserDataHeader() + "111111111");

			try
			{
				// Uncomment following line if you wish to delete the message upon arrival.
				// ReadMessages.this.srv.deleteMessage(msg);
			}
			catch (Exception e)
			{
				log.error("Oops!!! Something gone bad..." + e);
			}
		}
	}

	public class CallNotification implements ICallNotification
	{
		public void process(AGateway gateway, String callerId)
		{
			log.info(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
		}
	}

	public class GatewayStatusNotification implements IGatewayStatusNotification
	{
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
		{
			log.info(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}
	}

	public SendReadMsger getSendReadMsger() {
		return sendReadMsger;
	}

	public void setSendReadMsger(SendReadMsger sendReadMsger) {
		this.sendReadMsger = sendReadMsger;
	}

}
