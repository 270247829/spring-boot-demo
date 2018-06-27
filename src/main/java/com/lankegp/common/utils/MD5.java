package com.lankegp.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {  
  
    // md5加密  
    public static String printMD5(String inputText) {
        return encrypt(inputText, "md5");  
    }  
  
    /** 
     * md5或者sha-1加密 
     *  
     * @param inputText 
     *            要加密的内容 
     * @param algorithmName 
     *            加密算法名称：md5或者sha-1，不区分大小写 
     * @return 
     */  
    private static String encrypt(String inputText, String algorithmName) {
        try {  
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));  
            byte s[] = m.digest();  
            // m.digest(inputText.getBytes("UTF8"));  
            return hex(s);  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  
        }  
        return "";  
    }  
  
    // 返回十六进制字符串  
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));  
        }  
        return sb.toString();  
}
 }
