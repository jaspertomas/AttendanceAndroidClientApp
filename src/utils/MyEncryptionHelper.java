package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//from http://www.mkyong.com/java/java-md5-hashing-example/
//usage:
//		String s="baabaablacksheep";
//		System.out.println(md5(s));
 public class MyEncryptionHelper {
 	public static String encrypt(String string)
 	{
         MessageDigest md;
 		try {
 			md = MessageDigest.getInstance("SHA1");
 	        md.update(string.getBytes());
 	        
 	        byte byteData[] = md.digest();
 	 
 	        //convert the byte to hex format method 1
 	        StringBuffer sb = new StringBuffer();
 	        for (int i = 0; i < byteData.length; i++) {
 	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
 			}
 	         return sb.toString();
 		} catch (NoSuchAlgorithmException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
         }		
 		return null;
 		
 	}
 }
