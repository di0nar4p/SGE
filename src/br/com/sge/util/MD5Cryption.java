package br.com.sge.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Cryption {

	public static String transformStringToMD5(String value) {
		MessageDigest messageDiagest;
		try {
			messageDiagest = MessageDigest.getInstance("MD5");
			messageDiagest.update(value.getBytes(), 0, value.length());
			return new BigInteger(1, messageDiagest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
