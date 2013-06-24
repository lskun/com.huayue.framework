package com.huayue.framework.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Format
{
    private Format() { }

    // text参数是否为一个数字，保括小数
    public static boolean isNumeric(String text)
    {
        return matches(text, "^[-]?\\d*.\\d*$");
    }
    
    //邮箱格式验证
    public static boolean isEmail(String searchPhrase){
    	String check = "^([a-z0-9A-Z]+[_-|\\.]?)+[a-z0-9A-Z]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    	Pattern regex = Pattern.compile(check);
    	Matcher matcher = regex.matcher(searchPhrase);
        return matcher.matches();
    }
    
    // text参数是否为一个整数
    public static boolean isInteger(String text)
    {
        return matches(text, "^\\d+$");
    }

    // text参数是否为一个日期格式，比如2011-01-24
    public static boolean isDate(String text)
    {    	
        return matches(text, "^[1-9]\\d{3}[-][01][1-9][-][0-3]\\d");
    }
    
    //手机验证
    public static boolean isMobileNO(String mobile){     
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");     
        Matcher m = p.matcher(mobile);                
        return m.matches();     
    }
    
    public static boolean isPostCode(String postCode){
    	Pattern p = Pattern.compile("^\\d{6}$");
    	Matcher m = p.matcher(postCode);
    	return m.matches();
    }
    // 清除
    public static String clear(String text)
    {
        return text == null ? "" : text;
    }

    // 替换为适用于HTML显示的文本
    public static String toHTML(String text)
    {
        text = stripTags(clear(text));
        text = text.replaceAll(" ", "&nbsp;").replaceAll("[\r\n]", "<br/>").replaceAll("\\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        return text;
    }
    
    // 是否为空的字符串
    public static boolean isEmpty(String text)
    {
        if (null == text) return true;
        if (text.length() == 0) return true;
        if (text.trim().length() == 0) return true;
        return false;
    }
    
    public static String clear(Object text)
    {
        return clear((String)text);
    }

    // 替换HTML实体符
    public static String stripTags(String text)
    {
        return clear(text).replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("[\r\n]+", "<br/>");
    }

    private static boolean matches(String srcText, String pattern)
    {
        return clear(srcText).matches(pattern);
    }

    public static String convertSize(int size)
    {
        if (0 >= size) return "-";
        int k = 0, idx;
        String ch = size + "B";
        String chars = "BKMGT";
        float fSize = (float)size;
        
        for (k = 0; k < 4; k++)
        {
            // ch = fSize + String.valueOf(chars.charAt(k));
            if (fSize < 1024f) break;
            fSize = fSize / 1024f;
        }
        ch = String.valueOf(fSize);       
        idx = ch.indexOf(".");
       
        if (idx > -1 && idx < ch.length() - 3) ch = ch.substring(0, idx + 3);
        return ch + chars.charAt(k);
    }
    
    public static String Q2BChange(String input,boolean flag) {  
        String result = "";  
        char[] str = input.toCharArray();  
        for(int i=0;i<str.length;i++ )
        {  
            int code = str[i];//获取当前字符的unicode编码  
            if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母以及各种字符  
            {  
               result +=(char)(str[i] - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码  
            }
            else if (code == 12288)//空格    
            {  
               result +=(char)(str[i] - 12288 + 32);               
              }else if(code == 65377){  
                  result +=(char)(12290);  
              }else if(code == 12539){  
                  result +=(char)(183);  
              }else if(code == 8482 && flag==true){//如果是特殊字符TM 并且是需要转换的所作操作  
                  
              }else if(code ==8226){ //特殊字符 ‘·’的转化  
                  result += (char)(183);  
              }else{  
               result += str[i];  
              }  
        }  
          
        return result;  
    }  
    //
    public static String subStr(String str){
    	if(str.length() > 10) str = str.substring(0,10);
    	return str;
    }
    
    public static void main(String[] args)throws Exception{
    	//System.out.println(Q2BChange("1235",true));
    	System.out.println(Format.isEmail("chinahua.221211008@sina.com.cn"));
    }
}
