package com.gh.management.CryptoUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

/**
 * 主要是AES算法的使用
 * @Author: zhangyan
 * @Date: 2020/3/20 8:36
 * @Version 1.0
 */
public class CryptoTools {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    //算法名
    public static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    //生成密钥
    public static byte[] generateKey() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    //生成iv
    public static AlgorithmParameters generateIV() throws Exception{
        //iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    //转化成JAVA的密钥格式
    public static Key convertToKey(byte[] keyBytes) throws Exception{
        SecretKey secretKey = new SecretKeySpec(keyBytes,KEY_ALGORITHM);
        return secretKey;
    }

    //加密
    public static byte[] encrypt(byte[] data,byte[] keyBytes,AlgorithmParameters iv) throws Exception {
        //转化为密钥
        Key key = convertToKey(keyBytes);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key,iv);
        return cipher.doFinal(data);
    }

    public static byte[] generateKeyByString(String pswKey) throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128,new SecureRandom(pswKey.getBytes("utf-8")));
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }
    public static byte[] encryptByOwnKey(byte[] content, Key key,AlgorithmParameters iv) throws Exception {


        Cipher in = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        in.init(Cipher.ENCRYPT_MODE, key,iv);
        byte[] enc = in.doFinal(content);
        return enc;
    }

    //解密
    public static byte[] decrypt(byte[] encryptedData,byte[] keyBytes,AlgorithmParameters iv) throws Exception{
        Key key = convertToKey(keyBytes);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key,iv);
        return cipher.doFinal(encryptedData);
    }

    public static byte[] decryptByOwn(byte[] content, Key key,AlgorithmParameters iv) throws Exception {

        Cipher out = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        out.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] dec = out.doFinal(content);
        return dec;
    }

    //将key哈希为128位


    public static void main(String[] args) throws Exception {
        //byte[] key=CryptoTools.generateKey();


    }




}
