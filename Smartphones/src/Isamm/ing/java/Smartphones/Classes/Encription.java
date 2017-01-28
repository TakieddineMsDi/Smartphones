package Isamm.ing.java.Smartphones.Classes;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encription {
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B',
			'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	public static String decrypt(String encryptedData, Key key) {
		String decryptedValue = null;
		try {
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder()
					.decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
			return decryptedValue;
		} catch (Exception E) {
			System.out.println("Exception à Encription.decrypt() "
					+ E.getMessage());
			return null;
		}
	}

	public static String encrypt(String Data, Key key) {
		String encryptedValue = null;
		try {
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(Data.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
			return encryptedValue;
		} catch (Exception E) {
			System.out.println("Exception à Encription.encript() "
					+ E.getMessage());
			return null;
		}
	}

	private static Key generateKey() {
		Key key = null;
		try {
			key = new SecretKeySpec(keyValue, ALGO);
			return key;
		} catch (Exception E) {
			System.out.println("Exception à Encription.generateKey() "
					+ E.getMessage());
			return null;
		}
	}

	public static Key getKey(String Password) {
		if (Password.equals("password"))
			return generateKey();
		else {
			System.out.println("Erreur Encription : Password Incorrect!");
			return null;
		}
	}

	public Encription() {
	}
}