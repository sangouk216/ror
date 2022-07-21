package kr.ror.common.util;

import java.net.URLDecoder;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;

public class EncryptorUtil {
    
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CHARSET_EUC_KR = "EUC-KR";
        
	private static String key = "_^^__!@98765@JBT";//
	private static String initVector = "�븳湲�濡쒖쿂由�!"; //
	
	private static IvParameterSpec iv; 
	private static SecretKeySpec skeySpec;
	
	static {
		try {
			iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		} catch(Exception e) {}
	}
	
	
	
	public static String encrypt(String value) {
		return encrypt(key, initVector, value);
	}
	
    public static String encrypt(String key, String initVector, String value) {
    	try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

//            return DatatypeConverter.printBase64Binary(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

//            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

//            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String decrypt(String encrypted) {
    	return decrypt(key, initVector, encrypted);
    }
    
    public static String decrypt(String key, String encrypted) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] gdk = md.digest(key.getBytes());
            return decrypt(gdk, gdk, encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static String decrypt(byte[] md5Key, byte[] md5InitVector, String encrypted) {
        try {
        	IvParameterSpec md5Iv = new IvParameterSpec(md5InitVector);
        	SecretKeySpec md5keySpec =  new SecretKeySpec(md5Key, "AES");
        	
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, md5keySpec, md5Iv);

//            byte[] original = cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted));

//            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public static String decodeUrl(String encoded) {
        return decodeUrl(encoded, CHARSET_UTF_8);
    }
    
    public static String decodeUrl(String encoded, String charset) {
        try {
            return URLDecoder.decode(encoded, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}