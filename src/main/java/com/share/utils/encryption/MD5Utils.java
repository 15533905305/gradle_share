package com.share.utils.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	public static String generateMD5Code(String password) {
		StringBuilder builder=null;
		try {
			//获取messageDegist对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			//生成byte数组
			byte[] digest = md.digest(password.getBytes());
			 builder= new StringBuilder();
			for (byte b : digest) {
				int input = b&0xff;
				if(input<16){
					builder.append(0);
				}
				builder.append(Integer.toHexString(input));
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String generateMd5Code = generateMD5Code("123456"+"ABCD");
		System.out.println(generateMd5Code);
		System.out.println(generateMd5Code.length());
	}
}
