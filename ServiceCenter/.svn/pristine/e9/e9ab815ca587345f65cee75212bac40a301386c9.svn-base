package com.huayue.framework.smslib;

/**
 * @author GongQingBao
 */
public class ParamObj {

	 // false : 表示modem初始化未完成; true : 表示modem初始化完成.
	public static boolean initModemStatus = false;

	private static ParamObj paramObj;
	private static Object clockObj = ParamObj.class;

	private ParamObj() {}

	public static ParamObj newInstance() {
		synchronized (clockObj) {
			if(paramObj == null) {
				paramObj = new ParamObj();
			}

			return paramObj;
		}
	}
}
