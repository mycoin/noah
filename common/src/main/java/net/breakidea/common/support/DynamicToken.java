package net.breakidea.common.support;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DynamicToken {

    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static char[] DIGITS = ALPHABET.toCharArray();
    private static final String SEPARATOR = "-";
    private static final int MASK = DIGITS.length - 1;
    private static final int SHIFT = Integer.numberOfTrailingZeros(DIGITS.length);

    private static Map<Character, Integer> CHAR_MAP = null;

    static {
        CHAR_MAP = new HashMap<Character, Integer>();
        for (int i = 0; i < DIGITS.length; i++) {
            CHAR_MAP.put(DIGITS[i], i);
        }
    }

    /**
     * 摘要对象
     */
    private Mac mac;

    /**
     * 秘钥
     */
    private String secKey;

    /**
     * 与系统的时间间隔
     */
    private int invertal;

    /**
     * 构造函数
     * @param key
     */
    public DynamicToken( String key ) {
        this.secKey = key;
        try {
            mac = Mac.getInstance("HmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 动态口令
     * @throws Exception
     */
    public String getDynamicCode( String key, long systemTime, int invertal ) throws Exception {
        byte[] data;
        data = sha1(key, (systemTime + invertal) / 30000);// sha1生成 20字节（160位）的数据摘要
        int o = data[19] & 0xf;// 通过对最后一个字节的低4位二进制位建立索引，索引范围为 （0-15）+4 ，正好20个字节。
        int number = hashToInt(data, o) & 0x7fffffff; // 然后计算索引指向的连续4字节空间生成int整型数据。
        return output(String.valueOf(number % 1000000));// 对获取到的整型数据进行模运算，再对结果进行补全（长度不够6位，在首位补零）得到长度为6的字符串
    }

    /**
     * 获取 动态口令
     * @throws Exception
     */
    public String getDynamicCode( String key, long systemTime ) throws Exception {
        return getDynamicCode(key, systemTime, invertal);
    }

    /**
     * 获取 动态口令
     * @throws Exception
     */
    public String getDynamicCode( String key ) throws Exception {
        return getDynamicCode(key, System.currentTimeMillis(), invertal);
    }

    /**
     * 获取 动态口令
     * @throws Exception
     */
    public String getDynamicCode() throws Exception {
        return getDynamicCode(secKey, System.currentTimeMillis(), invertal);
    }

    /**
     * 设置时间偏移量  单位：毫秒
     * @return
     */
    public void setTimeIntertal( int offset ) {
        this.invertal = offset;
    }

    /**
     * 取数据摘要
     * @param secret
     * @param msg
     * @return 加密后的字节数字
     * @throws Exception
     */
    private byte[] sha1( String secret, long msg ) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decodeBase32(secret), "");// 创建秘钥

        mac.reset();
        mac.init(secretKey);// 初始化秘钥

        byte[] value = ByteBuffer.allocate(8).putLong(msg).array();// 将long类型的数据转换为byte数组
        return mac.doFinal(value);// 计算数据摘要
    }

    /**
     * @param secret
     * @return
     * @throws Exception
     */
    private byte[] decodeBase32( String encoded ) throws Exception {

        // Remove whitespace and separators
        encoded = encoded.trim().replaceAll(SEPARATOR, "").replaceAll(" ", "");

        // Remove padding. Note: the padding is used as hint to determine how many
        // bits to decode from the last incomplete chunk (which is commented out
        // below, so this may have been wrong to start with).
        encoded = encoded.replaceFirst("[=]*$", "");
        encoded = encoded.toUpperCase(Locale.US);

        if (encoded.length() == 0) {
            return new byte[0];
        }

        int encodedLength = encoded.length();
        int outLength = encodedLength * SHIFT / 8;
        byte[] result = new byte[outLength];
        int buffer = 0;
        int next = 0;
        int bitsLeft = 0;
        for (char c : encoded.toCharArray()) {
            if (!CHAR_MAP.containsKey(c)) {
                throw new Exception("Illegal character: " + c);
            }
            buffer <<= SHIFT;
            buffer |= CHAR_MAP.get(c) & MASK;
            bitsLeft += SHIFT;
            if (bitsLeft >= 8) {
                result[next++] = (byte) (buffer >> (bitsLeft - 8));
                bitsLeft -= 8;
            }
        }
        // We'll ignore leftover bits for now.
        //
        // if (next != outLength || bitsLeft >= SHIFT) {
        // throw new DecodingException("Bits left: " + bitsLeft);
        // }
        return result;
    }

    /**
     * 将byte数组转化为整数
     * @param bytes
     * @param start
     * @return int
     */
    private int hashToInt( byte[] bytes, int start ) {
        DataInput input = new DataInputStream(new ByteArrayInputStream(bytes, start, bytes.length - start));
        int val;
        try {
            val = input.readInt();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return val;
    }

    /**
     * 格式化输出结果
     * @param intCode
     */
    private String output( String s ) {
        if (s.length() < 6) {
            s = "0" + s;
            return output(s);
        }
        return s;
    }

    public static void main( String[] args ) {
        DynamicToken dt = new DynamicToken("1234");
        try {
            System.out.println(dt.getDynamicCode());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}