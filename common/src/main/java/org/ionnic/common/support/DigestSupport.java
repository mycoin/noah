package org.ionnic.common.support;

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

    private static final char KEY_CHARS[] = { 'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 解密
     *
     * @param src   数据源
     * @param key   密钥，长度必须是8的倍数
     * @return      返回解密后的原始数据
     * @throws Exception
     */
    public static String DESDecode( String data, String key ) {
        byte[] datas = toByte(data.getBytes());
        byte[] keys = key.getBytes();

        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();

            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(keys);

            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

            // 现在，获取数据并解密,正式执行解密操作
            return new String(cipher.doFinal(datas));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥
     * @return    返回加密后的数据
     * @throws Exception
     */

    public static String DESEncode( String data, String key ) {

        try {
            // 从原始密匙数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

            SecretKey securekey = keyFactory.generateSecret(dks);

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, new SecureRandom());

            // 正式执行加密操作
            return toHex(cipher.doFinal(data.getBytes()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param string
     * @return
     */
    public static String encrypt( String string ) {

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            byte[] btInput = string.getBytes();

            // 使用指定的字节更新摘要
            mdInst.update(btInput);

            // 获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int len = md.length;
            char str[] = new char[len * 2];
            int index = 0;
            for (int i = 0; i < len; i++) {
                byte by = md[i];
                str[index++] = KEY_CHARS[by >>> 4 & 0xf];
                str[index++] = KEY_CHARS[by & 0xf];
            }
            return new String(str);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return
     */
    public static String getGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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