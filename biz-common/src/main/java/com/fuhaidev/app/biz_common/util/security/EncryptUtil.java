package com.fuhaidev.app.biz_common.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * 加密解密工具类
 */
public final class EncryptUtil {

    protected static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    private final static String SHA = "SHA";
    private final static String SHA1 = "SHA-1";
    private final static String MD5 = "MD5";
    private final static String DES = "DES";
    private final static String AES = "AES";
    private final static String DEFAULT_KEY = "GnY5iyuhj6BPfDSG";

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    /**
     * SHA 加密
     *
     * @param data 需要加密的字节数组
     * @return 加密之后的字节数组
     * @throws Exception
     */
    public static String encryptSHA1(String data) {
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest sha = MessageDigest.getInstance(SHA1);
            // 使用指定的字节数组对摘要进行最后更新
            sha.update(data.getBytes());
            // 完成摘要计算并返回
            byte[] messageDigest = sha.digest();
            return byteArrayToHexString(messageDigest);
        } catch (Exception e) {
            logger.error("SHA1加密失败", e);
        }
        return "";
    }

    public static String encryptMD5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance(MD5);
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            return byteArrayToHexString(md);
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5加密失败", e);
        }
        return "";
    }

    /**
     * SHA 加密
     *
     * @param data 需要加密的字符串
     * @return 加密之后的字符串
     * @throws Exception
     */
    public static String encryptSHA(String data) {
        // 验证传入的字符串
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        try {
            // 创建具有指定算法名称的信息摘要
            MessageDigest sha = MessageDigest.getInstance(SHA);
            // 使用指定的字节数组对摘要进行最后更新
            sha.update(data.getBytes());
            // 完成摘要计算
            byte[] bytes = sha.digest();
            // 将得到的字节数组变成字符串返回
            return byteArrayToHexString(bytes);
        } catch (Exception e) {
            logger.error("SHA加密失败", e);
        }
        return "";
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        //System.out.println("ret = " + ret);
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    /**
     * 转换字节数组为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }


   /* public static String encrypt(String src) throws Exception {
        return ConfigTools.encrypt(src);
    }

    public static String decrypt(String src) throws Exception {
        return ConfigTools.decrypt(src);
    }*/

    public static String aesEncrypt(String content) {
        return new String(Base64.encode(aesEncrypt(content, DEFAULT_KEY)));
    }

    public static String aesDecrypt(String content) {
        return new String(aesDecrypt(Base64.decode(content.getBytes()), DEFAULT_KEY));
    }


    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] aesEncrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] aesDecrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desEncrypt(String data) throws Exception {
        return desEncrypt(data, DEFAULT_KEY);
    }

    public static String desEncrypt(String data, String key) throws Exception {
        byte[] bt = desEncrypt(data.getBytes(), key.getBytes());
        String strs = new String(Base64.encode(bt));
        return strs;
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] desEncrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    public static String desDecrypt(String data) throws IOException,
            Exception {
        return desDecrypt(data, DEFAULT_KEY);
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String desDecrypt(String data, String key) throws IOException,
            Exception {
        if (data == null) {
            return null;
        }
        byte[] buf = Base64.decode(data.getBytes());
        byte[] bt = desDecrypt(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] desDecrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * 与php中的hash_hmac('sha512', $data, $key)功能相同
     *
     * @param data
     * @param key
     * @return
     */
//    private String hmacSHA512(String data, String key) {
//        String result = "";
//        byte[] bytesKey = key.getBytes();
//        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "HmacSHA512");
//        try {
//            Mac mac = Mac.getInstance("HmacSHA512");
//            mac.init(secretKey);
//            final byte[] macData = mac.doFinal(data.getBytes());
//            byte[] hex = new Hex().encode(macData);
//            result = new String(hex, "ISO-8859-1");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}
