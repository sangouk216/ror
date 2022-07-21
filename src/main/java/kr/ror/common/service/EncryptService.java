package kr.ror.common.service;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.util.Base64Utils;

public class EncryptService {

	private TextEncryptor enc;
	
	private String secretKey;
	private String saltKey;
	
	
	public void init() {
		String secret = Base64Utils.decodeFromUrlSafeString(secretKey).toString();
		String salt = stringToHex(saltKey);
		
		enc = Encryptors.text(secret, salt);
	}
	
	
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}

	public String encrypt(String plainText) {
		return enc.encrypt(plainText);
	}
	
	public String decrypt(String encryptedText) {
		return enc.decrypt(encryptedText);
	}
	
	private String stringToHex(String s) {
		String result = "";
		
		for(int i = 0; i < s.length(); i++) {
			result += String.format("%02X", (int) s.charAt(i));
		}
		
		return result;
	}
	
	
}
