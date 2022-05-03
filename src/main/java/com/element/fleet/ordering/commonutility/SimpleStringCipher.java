package com.element.fleet.ordering.commonutility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.element.fleet.ordering.exceptions.OrderingErrorOccured;

class SimpleStringCipher {
	private static byte[] linebreak = {};
	private static String secret = "iaMnoTshar13my73";
	private static SecretKey key;
	private static Cipher cipher;
	private static Base64 coder;

	static {
		try {
			key = new SecretKeySpec(secret.getBytes(), "AES");			
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
			coder = new Base64(32, linebreak, true);
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			e.printStackTrace();
			throw new OrderingErrorOccured();
		}
	}

	private static synchronized String encrypt(final String plainText) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		final byte[] cipherText = cipher.doFinal(plainText.getBytes());
		return new String(coder.encode(cipherText));
	}

	private static synchronized String decrypt(final String codedText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final byte[] encypted = coder.decode(codedText.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, key);
		final byte[] decrypted = cipher.doFinal(encypted);
		return new String(decrypted);
	}

	public static void main(final String[] args){
		try {

			final String encrypted = encrypt("testqa12");
      //		final String encrypted = encrypt("testqa21");
         			System.out.println("Encrypted:" + encrypted);
			System.out.println("Decrypted:" + decrypt(encrypted));
		} catch(Exception e) {
			e.printStackTrace();
			throw new OrderingErrorOccured();
		}
	}

}
