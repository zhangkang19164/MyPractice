package com.example.other.encode;


import android.util.Base64;
import android.util.Log;

import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * create time : 2017/10/21
 * desc        :
 */

public class AESUtil {
    private static final String TAG = "AESUtil";
    //AES是加密方式
    private final static String ALGORITHM = "AES";
    //AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private final static String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    //初始化向量参数，AES 为16bytes. DES 为8bytes.
    private final static String IV_PARAMETER_SPEC = "0102030405060708";
    //将加密后的字节码处理为16进制的字符串
    private final static String HEX = "0123456789ABCDEF";
    //是否使用Base64编码
    private final static boolean IS_BASE64 = false;


    /**
     * 加密
     *
     * @param key 密钥
     * @param src 需要加密的字符串
     * @return 加密后使用Base64编码后的字符串
     * @throws Exception 向上层抛出的异常
     */
    public static String encrypt(String key, String src) throws Exception {
//        byte[] rawKey = getRawKey(key.getBytes());
        byte[] rawKey = key.getBytes();
        byte[] result = encrypt(rawKey, src.getBytes());
        if (IS_BASE64) {
            String encode = Base64.encodeToString(result,Base64.DEFAULT);
            Log.i(TAG, "encrypt: Base64Util = "+encode);
            return encode;
        } else {
            String toHex = toHex(result);
            Log.i(TAG, "encrypt: toHex = "+toHex);
            return toHex;
        }
    }

    /**
     * 解密
     *
     * @param key       密钥
     * @param encrypted 待解密的字符串
     * @return 解密后的字符串
     * @throws Exception 向上层抛出的异常
     */
    public static String decrypt(String key, String encrypted) throws Exception {
//        byte[] rawKey = getRawKey(key.getBytes());
        byte[] rawKey = key.getBytes();
        byte[] enc;
        if (IS_BASE64) {
            enc = Base64Util.decode(encrypted);
        } else {
            enc = toByte(encrypted);
        }
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }



    /**
     * 真正的加密过程
     *
     * @param key 秘钥
     * @param src 需要加密的字节码
     * @return 加密后的字符串
     * @throws Exception 向上层抛出的异常
     */
    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV_PARAMETER_SPEC.getBytes()));
        return cipher.doFinal(src);
    }

    /**
     * 真正的解密过程
     *
     * @param key       秘钥
     * @param encrypted 待解密的字符串
     * @return 解密后的内容
     * @throws Exception 向上层抛出的异常
     */
    private static byte[] decrypt(byte[] key, byte[] encrypted)
            throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV_PARAMETER_SPEC.getBytes()));
        return cipher.doFinal(encrypted);
    }

    /**
     * 获取256位的加密密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom sr;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(seed);
        keyGenerator.init(128, sr); // 192 and 256 bits may not be available
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }

    private static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (byte aBuf : buf) {
            appendHex(result, aBuf);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }


    private static final class CryptoProvider extends Provider {
        /**
         * Creates a Provider and puts parameters
         */
        private CryptoProvider() {
            super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
            put("SecureRandom.SHA1PRNG",
                    "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
        }
    }

}
