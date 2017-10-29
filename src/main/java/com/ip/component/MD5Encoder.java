package com.ip.component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {
	public static String hashMD5(String pwd) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
		messageDigest.update(pwd.getBytes(),0, pwd.length());  
		String hashed = new BigInteger(1,messageDigest.digest()).toString(16);  
		if (hashed.length() < 32) {
		   hashed = "0" + hashed; 
		}
		return hashed;
	}
}
