package com.huayue.apply.util;

import java.util.*;

public class SecurityCode {
	private static Random random = new Random();
	
	public static String produce()throws Exception{
		//输出由英文，数字随机组成的验证文字，具体的组合方式根据生成随机数确定。   
        String sRand = "";   
        String ctmp = "";   
        int itmp = 0; 
        
        //制定输出的验证码为八位   
        for(int i = 0;i < 8; i++){   
            //random.nextInt(2)在0-9中间去随机，不包含2，这样是为了下面不去生成中文验证码，验证出现中文时，不好判断  
            switch(random.nextInt(4)){   
                case 1:     //生成a-z的字母   
                     itmp = random.nextInt(26) + 97;   
                     ctmp = String.valueOf((char)itmp);   
                     break;   
                case 2:		//生成A-Z的字母
                	 itmp = random.nextInt(26) + 65;
                	 ctmp = String.valueOf((char)itmp);
                	 break;
                default:   
                     itmp = random.nextInt(10) + 48;   
                     ctmp = String.valueOf((char)itmp);   
                     break;   
            }   
            sRand += ctmp;  
        }
        return sRand;
	}

}
