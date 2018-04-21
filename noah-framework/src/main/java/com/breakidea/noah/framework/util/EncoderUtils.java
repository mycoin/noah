package com.breakidea.noah.framework.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncoderUtils {

	/**
	 * @param data
	 * @return
	 */
	public static String DESDecrypt(String data, byte[] privateKey) {
		byte[] datas = null;
		byte[] dataByte = data.getBytes();

		try {
			if ((dataByte.length % 2) == 0) {
				datas = new byte[dataByte.length / 2];
				for (int n = 0; n < dataByte.length; n += 2) {
					String item = new String(dataByte, n, 2);
					datas[n / 2] = (byte) Integer.parseInt(item, 16);
				}
			} else {
				return null;
			}

			SecretKey securekey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(privateKey));
			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(Cipher.DECRYPT_MODE, securekey, new SecureRandom());
			return new String(cipher.doFinal(datas));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param id
	 * @return
	 */
	public static String DESEncrypt(String data, byte[] privateKey) {
		try {
			DESKeySpec dks = new DESKeySpec(privateKey);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

			SecretKey securekey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());

			StringBuilder result = new StringBuilder();
			byte[] output = cipher.doFinal(data.getBytes());

			for (int n = 0; output != null && n < output.length; n++) {
				String stmp = Integer.toHexString(output[n] & 0XFF);
				if (stmp.length() == 1) {
					result.append('0');
				}
				result.append(stmp);
			}
			return result.toString();
		} catch (Exception e) {

		}
		return null;
	}

	public static String encode(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bs = digest.digest(password.getBytes());
			String hexString = "";
			for (byte b : bs) {
				int temp = b & 255;
				if (temp < 16 && temp >= 0) {
					hexString = hexString + "0" + Integer.toHexString(temp);
				} else {
					hexString = hexString + Integer.toHexString(temp);
				}
			}
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
