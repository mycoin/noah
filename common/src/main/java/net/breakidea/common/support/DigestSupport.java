package net.breakidea.common.support;

import java.security.GeneralSecurityException;
import java.security.KeyException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @author apple
 *
 */
public abstract class DigestSupport {

    private static final String DES = "DES";

    /**
     * 解密
     *
     * @param src   数据源
     * @param key   密钥，长度必须是8的倍数
     * @return      返回解密后的原始数据
     * @throws Exception
     */
    public static String DESDecode( String data, String key ) throws GeneralSecurityException {
        byte[] datas = toByte(data.getBytes());
        byte[] keys = key.getBytes();

        try {

            key = padKey(key);

            // The DES algorithm requires a trusted random number generator
            SecureRandom sr = new SecureRandom();

            // Create a DESKeySpec object from the original key data
            DESKeySpec dks = new DESKeySpec(keys);

            // Create a key factory, and then use it to convert the DESKeySpec object into a SecretKey object
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher object to complete the decryption operation
            Cipher cipher = Cipher.getInstance(DES);

            // Cipher object is initialized with a key.
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

            // Now, access to data and decryption, the official implementation of the decryption operation
            return new String(cipher.doFinal(datas));

        } catch (Exception e) {
            throw new GeneralSecurityException(e);
        } finally {
        }
    }

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥
     * @return    返回加密后的数据
     * @throws KeyException
     * @throws GeneralSecurityException
     * @throws Exception
     */

    public static String DESEncode( String data, String key ) throws GeneralSecurityException {
        try {

            key = padKey(key);

            // Create a DESKeySpec object from the original key data
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            // Create a key factory, and then use it to convert DESKeySpec into a SecretKey object
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher object to complete the encryption operation
            Cipher cipher = Cipher.getInstance(DES);

            // Cipher object is initialized with a key.
            cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());

            // Formal execution of cryptographic operations
            return toHex(cipher.doFinal(data.getBytes()));

        } catch (Exception e) {
            throw new GeneralSecurityException(e);
        }
    }

    /**
     * @param string
     * @param key
     * @return
     */
    public static String encrypt( String string, String key ) {
        char[] keyChars = key.toCharArray();

        try {

            // MD5 abstract algorithm to obtain the MessageDigest object
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            byte[] btInput = string.getBytes();

            // Using the specified byte update summary
            mdInst.update(btInput);

            // Obtain the cipher text
            byte[] md = mdInst.digest();

            // Converting the cipher text into a string of sixteen
            int len = md.length;

            char str[] = new char[len * 2 + 4];
            int index = 0;

            for (int i = 0; i < len; i++) {
                byte by = md[i];
                str[index++] = keyChars[by >>> 4 & 0xf];
                str[index++] = keyChars[by & 0xf];
                if (i == 3 || i == 5 || i == 7 || i == 9) {
                    str[index++] = '-';
                }
            }
            return new String(str);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param string
     * @return
     */
    public static String encrypt( String string ) {
        return encrypt(string, "0123456789abcdef");
    }

    /**
     * @return
     */
    public static String getGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * @param args
     */
    public static void main( String[] args ) {
        System.out.println(encrypt("11"));
    }

    /**
     * @param key
     * @return
     */
    private static String padKey( String key ) {
        if (key == null || key.length() < 8) {
            return "12345678" + key;
        }
        return key;
    }

    /**
     * @param b
     * @return
     */
    private static byte[] toByte( byte[] b ) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException();
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    private static String toHex( byte[] b ) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}