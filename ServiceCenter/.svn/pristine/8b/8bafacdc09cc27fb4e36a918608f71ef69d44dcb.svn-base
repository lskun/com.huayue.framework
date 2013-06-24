package com.huayue.framework.util;


public class StrToByte {  
     
    public static String byte2hex(byte[] b) 
    {  
        String hs = "";  
        String stmp = "";   
        for (int n = 0; n < b.length; n++) 
        {  
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
            if (stmp.length() == 1)  
                hs = hs + "0" + stmp;  
            else 
                hs = hs + stmp;  
        }  
        return hs;  
    }
    
    public static byte[] hex2byte(String str) 
    {   
        if (str == null)   
            return null;  
        str = str.trim();  
        int len = str.length();  
        if (len == 0 || len % 2 == 1)  
            return null;   
        byte[] b = new byte[len / 2];   
        
        try 
        {  
            for (int i = 0; i < str.length(); i += 2) {  
                b[i / 2] = (byte) Integer  
                .decode("0x" + str.substring(i, i + 2)).intValue();  
            }   
            return b;  
        } catch (Exception e) {  
            return null;  
        }  
    }  
    public static void main(String[] args) {  
        String str = "absadfawegsdcd";  
        String result = "";  
        result = byte2hex(str.getBytes());  
        System.out.println(result);  
        System.out.println(new String(hex2byte(result)));  
    }  
} 
