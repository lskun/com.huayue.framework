package com.huayue.crm.base.util;

import java.util.ArrayList;
import java.util.Map;

public class BinaryCategoryUtil {
	
	public static long arrayToBinaryCategory(Object[] tmp){
		long mark = 00000000000;
		if(tmp == null) return mark;
		//if(tmp.length == 0 ) return mark;
		for(int i= 0 ; i < tmp.length ; i++ ){
			mark |= (1 << Integer.parseInt(tmp[i].toString()));
		}
		return mark;
	}
	
	//long类型转换为二进制的字符串表示形式
	public static String longToBinaryStr(long x)
	{
		String str = "";
	    for(int i = 11 ;i >= 0 ;i--)
	    {
	        if(((1<<i) & x) != 0) str += "1";
	        else str += "0";
	    }
	    return str;
	}
	
	public static ArrayList<Integer> reverseToCategoryList(String str){
		char[] c = str.toCharArray();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = c.length - 1; i > 0; i-- ){
			if(c[i] == '1') list.add(c.length - 1 - i);
		}
		return list;
	}
	
	//导出excel文件时category单元格显示
	public static String getCategoryExportStringValue(long x){
		if(x == 0L) return "";
		String str = longToBinaryStr(x);
		char[] c = str.toCharArray();
		str = "";
		for(int i = c.length - 1; i > 0; i-- ){
			if(c[i] == '1') str += ClientCategory.cmap.get(c.length - i - 1)+ ",";
		}
		return str.substring(0, str.length() - 1);
	}
	
	public static void main(String[] args)throws Exception{
		System.out.println(000000000000 == 0L);
		
		/***
		for(Map.Entry<Integer,String> map : ClientCategory.cmap.entrySet()){
			System.out.println(map.getKey() + map.getValue());
		}
		System.out.println(getCategoryExportStringValue(7));
		 ****/
		/****
		System.out.println(longToBinaryStr(137));
		String str = longToBinaryStr(137);
		char[] c = str.toCharArray();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < c.length ; i++ ){
			if(c[i] == '1')  {
				list.add(i);
				System.out.println(true);
			}
		}
		****/
	}
}
