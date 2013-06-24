package com.huayue.datasource;

public class CustomerContextHolder {

	public static final String DATA_SOURCE_FROM = "dataSource";
	
	public static final String DATA_SOURCE_TO = "dataSource1";
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}
	
	public static String getCustomerType() {
		return contextHolder.get();
	}
	
	public static void clearCustomerType() {
		contextHolder.remove();
	}
}

