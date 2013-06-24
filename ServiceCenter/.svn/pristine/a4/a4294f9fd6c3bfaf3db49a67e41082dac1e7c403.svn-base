/**
 * 
 */
package com.huayue.framework.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author lsk0414
 *
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	
	private static String[] encryptPropNames= {"userName","password","s_userName","s_password"};
	
	/**
	 * 解密properties文件中的加密属性
	 */
	protected String convertProperty(String propertyName,String propertyValue){
		if(isEncryptProp(propertyName)){
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		}else{
			return propertyValue;
		}
	}
	/**
	 * 
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName){
		for(String str : encryptPropNames){
			if(str.equals(propertyName)) return true;
		}
		return false;
	}
}
