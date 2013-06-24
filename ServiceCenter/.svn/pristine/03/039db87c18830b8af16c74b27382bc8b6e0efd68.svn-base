package com.huayue.framework.smslib;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.smslib.InboundMessage;

import com.huayue.sms.service.SmsReceiveService;


/**
 * 消息收发对象.
 *
 * @author Bruce
 *
 */
public class SendReadMsger implements Runnable {
	
	private static final Logger log = Logger.getLogger(SendReadMsger.class);
	
	public static Modem modem = null;
	private ThreadPoolExecutor producerPool = null;
	private InboundMessageActionListener inboundMessageActionListener = null;

	private static SendReadMsger sendReadMsger;
	private static Object clockObj = SendReadMsger.class;

	private boolean deleteAfterRead = true;
	
	private SendReadMsger() {
		ParamObj.initModemStatus = false;

		//corePoolSize：线程池维护线程的最少数量
		//maximumPoolSize：线程池维护线程的最大数量
		//keepAliveTime： 线程池维护线程所允许的空闲时间
		//unit： 线程池维护线程所允许的空闲时间的单位
		//workQueue： 线程池所使用的缓冲队列
		//handler： 线程池对拒绝任务的处理策略

        //构造一个线程池
        producerPool = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000),
                new ThreadPoolExecutor.DiscardOldestPolicy());		//new ThreadPoolExecutor.DiscardOldestPolicy()

		Thread msgerThread = new Thread(this);
		msgerThread.start();
	}

	public static SendReadMsger newInstance() {
		synchronized (clockObj) {
			if(sendReadMsger == null) {
				sendReadMsger = new SendReadMsger();
				SmsReceiveService run = new SmsReceiveService();
				sendReadMsger.addInboundMessageActionListener(run);
			}
			return sendReadMsger;
		}
	}

	public void run() {
		log.info(" ------- init modem start ");		
		modem = Modem.newInstance();
		modem.setSendReadMsger(this);
		log.info(" ------- init modem end ");

        // Modem初始化完毕
        ParamObj.initModemStatus = true;

        // Sleep now.
        /*****
		while(true) {
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		******/
	}

	/**
	 * 消息到达是会自动执行此方法.
	 *
	 * @param text msg 消息内容
	 */
	public void msgArrive(InboundMessage msg) {
		if(null != inboundMessageActionListener) {

			// call-back
			inboundMessageActionListener.actionPerformedMsg(msg);
		}
		else {
			System.out.println(" There is a message arrived, but no InboundMessageListener! ");
		}
	}

	/**
	 * 使用线程池发送消息.
	 * @param msgText 消息内容
	 * @param tel 手机号码
	 * @return true : 成功; false : 失败.
	 */
	public void sendMsg(String msgText, String tel,long id) {
        producerPool.execute(new ThreadSendMsgTask(msgText, tel ,id));
	}

	/**
	 * 添加消息监听器.
	 * @param inboundMessageListener 消息监听器
	 */
	public void addInboundMessageActionListener(InboundMessageActionListener inboundMessageActionListener) {
		this.inboundMessageActionListener = inboundMessageActionListener;
	}

	public boolean isDeleteAfterRead() {
		return deleteAfterRead;
	}

	public void setAutoDeleteAfterRead(boolean bln) {
		this.deleteAfterRead = bln;
	}
}
