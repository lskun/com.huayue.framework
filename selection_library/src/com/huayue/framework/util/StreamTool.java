package com.huayue.framework.util;

import java.io.*;  

/** 
 * �������� 
 *  
 * @author ����ǿ 
 */  
public class StreamTool {  
  
    /** 
     * @�������� InputStream תΪ byte 
     * @param InputStream 
     * @return �ֽ����� 
     * @throws Exception 
     */  
    public static byte[] inputStreamToByte(InputStream inStream)  
            throws Exception {  
        // ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
        // byte[] buffer = new byte[1024];  
        // int len = -1;  
        // while ((len = inStream.read(buffer)) != -1) {  
        // outSteam.write(buffer, 0, len);  
        // }  
        // outSteam.close();  
        // inStream.close();  
        // return outSteam.toByteArray();  
        int count = 0;  
        while (count == 0) {  
            count = inStream.available();  
        }  
        byte[] b = new byte[count];  
        inStream.read(b);  
        return b;  
    }  
  
    /** 
     * @�������� byte תΪ InputStream 
     * @param �ֽ����� 
     * @return InputStream 
     * @throws Exception 
     */  
    public static InputStream byteToInputStream(byte[] b) throws Exception {  
        InputStream is = new ByteArrayInputStream(b);  
        return is;  
    }  
  
    /** 
     * @���� ���������ֽڵ�ת�� 
     * @param ������ 
     * @return }λ���ֽ����� 
     */  
    public static byte[] shortToByte(short number) 
    {  
        int temp = number;  
        byte[] b = new byte[2];  
        for (int i = 0; i < b.length; i++) {  
            b[i] = new Integer(temp & 0xff).byteValue();// �����λ���������λ  
            temp = temp >> 8; // ������8λ  
        }  
        return b;  
    }  
  
    /** 
     * @���� �ֽڵ�ת���������?
     * @param }λ���ֽ����� 
     * @return ������ 
     */  
    public static short byteToShort(byte[] b) {  
        short s = 0;  
        short s0 = (short) (b[0] & 0xff);// ����? 
        short s1 = (short) (b[1] & 0xff);  
        s1 <<= 8;  
        s = (short) (s0 | s1);  
        return s;  
    }  
  
    /** 
     * @�������� �������ֽ������ת��?
     * @param ���� 
     * @return ��λ���ֽ����� 
     */  
    public static byte[] intToByte(int i) {  
        byte[] bt = new byte[4];  
        bt[0] = (byte) (0xff & i);  
        bt[1] = (byte) ((0xff00 & i) >> 8);  
        bt[2] = (byte) ((0xff0000 & i) >> 16);  
        bt[3] = (byte) ((0xff000000 & i) >> 24);  
        return bt;  
    }  
  
    /** 
     * @�������� �ֽ���������͵�ת��?
     * @param �ֽ����� 
     * @return ���� 
     */  
    public static int bytesToInt(byte[] bytes) {  
        int num = bytes[0] & 0xFF;  
        num |= ((bytes[1] << 8) & 0xFF00);  
        num |= ((bytes[2] << 16) & 0xFF0000);  
        num |= ((bytes[3] << 24) & 0xFF000000);  
        return num;  
    }  
  
    /** 
     * @�������� �ֽ�����ͳ����͵�ת��?
     * @param �ֽ����� 
     * @return ������ 
     */  
    public static byte[] longToByte(long number) {  
        long temp = number;  
        byte[] b = new byte[8];  
        for (int i = 0; i < b.length; i++) {  
            b[i] = new Long(temp & 0xff).byteValue();  
            // �����λ���������λ  
            temp = temp >> 8;  
            // ������8λ  
        }  
        return b;  
    }  
  
    /** 
     * @�������� �ֽ�����ͳ����͵�ת��?
     * @param �ֽ����� 
     * @return ������ 
     */  
    public static long byteToLong(byte[] b) {  
        long s = 0;  
        long s0 = b[0] & 0xff;	// ����? 
        long s1 = b[1] & 0xff;  
        long s2 = b[2] & 0xff;  
        long s3 = b[3] & 0xff;  
        long s4 = b[4] & 0xff;	// ����? 
        long s5 = b[5] & 0xff;  
        long s6 = b[6] & 0xff;  
        long s7 = b[7] & 0xff; 	// s0����  
        s1 <<= 8;  
        s2 <<= 16;  
        s3 <<= 24;  
        s4 <<= 8 * 4;  
        s5 <<= 8 * 5;  
        s6 <<= 8 * 6;  
        s7 <<= 8 * 7;  
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;  
        return s;  
    }  
}  
