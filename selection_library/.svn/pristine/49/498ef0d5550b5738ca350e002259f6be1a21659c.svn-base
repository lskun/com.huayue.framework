package com.huayue.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
    public AES()
    {
        // ...
    }
    
    public static byte[] encrypt(String content, String keyWord)
    {
        try
        {
        	
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keyWord.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
         
            byte[] enCodeFormat = secretKey.getEncoded();            
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
            byte[] byteContent = content.getBytes();
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密  
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }
        return null;
    }

    public static String encode(String content, String password)
    {
        return parseByte2HexStr(encrypt(content, password));
    }

    // 解密
    public static byte[] decrypt(byte[] content, String keyWord)
    {
        try
        {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keyWord.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");                      // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);                          // 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }
        return null;
    }

    public static String decode(String content, String keyWord)
    {
        return new String(decrypt(parseHexStr2Byte(content), keyWord));
    }

    public static String parseByte2HexStr(byte buf[])
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1)
            {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if (hexStr.length() < 1)
            return null;
                    byte[] result = new byte[hexStr.length()/2];
                    for (int i = 0; i < hexStr.length() / 2; i++)
        {
                                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args)
    {
        String content = "HongLonglong我要测试一下，看看有没有问题，要是有问题了肿么办啊。这可肿么办啊？？？";
        String Key = "http://www.honglonglong.com";
        //加密
        System.out.println("加密前：" + content);
        String encryptResult = encode(content, Key);
        System.out.println("加密后：" + encryptResult);

        //解密
        String decryptResult = decode(encryptResult, Key);
        System.out.println("解密后：" + decryptResult);
        System.out.println("---" + encode("","x89x803721o3u089d787680327145iovdsyd88c078203io1huiodyf0da6b074o8y1rt54lhvcuy890z678"));

    }
}