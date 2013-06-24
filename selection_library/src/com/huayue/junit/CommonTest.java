/**
 * 
 */
package com.huayue.junit;

import com.huayue.framework.util.Format;

/**
 * @author lsk0414
 *
 */
public class CommonTest {
	
	public static void main(String[] args)throws Exception{
		/**
		Thread t = new Thread(){
			public void run(){
				pong();
			}
		};
		t.run();
		System.out.println("ping");
		**/
		
		System.out.println(Format.convertSize(23040));
	}
	
	static synchronized void pong(){
		System.out.println("pong");
	}
}
