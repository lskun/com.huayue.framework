package com.huayue.framework.smslib;

import org.smslib.InboundMessage;

public interface InboundMessageActionListener {
	/**
	 * modem接收到消息时会自动执行此方法.
	 * @param msg 消息对象
	 */
	public void actionPerformedMsg(InboundMessage msg);
}
