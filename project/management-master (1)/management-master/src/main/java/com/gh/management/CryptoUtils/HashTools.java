package com.gh.management.CryptoUtils;


import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class HashTools {

    //Hash 1->SHA-128 bits
    public static byte[] Hash1(byte[] input) throws Exception{
        MessageDigest digest=MessageDigest.getInstance("MD5");
        return digest.digest(input);
    }

    //Hash 2->256 bits
    public static byte[] Hash2(byte[] input) throws Exception{
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] hash=digest.digest(input);
        return hash;
    }

    //Hash 2->160 bits
    public static byte[] Hash3(byte[] input) throws Exception{
        MessageDigest digest=MessageDigest.getInstance("SHA-1");
        byte[] hash=digest.digest(input);
        return hash;
    }

    //Hash Type->根据安全参数自定义 bits
    public static byte[] Hash_Type(byte[] input,String type) throws Exception{
        MessageDigest digest=MessageDigest.getInstance(type);
        byte[] hash=digest.digest(input);
        return hash;
    }

    //Hash3 由ecc提供

    public static byte[] Hmac(byte[] key,byte[] data) throws Exception{

        SecretKey secretKey=new SecretKeySpec(key,"HmacMD5");

        Mac mac=Mac.getInstance(secretKey.getAlgorithm());

        mac.init(secretKey);

        return mac.doFinal(data);

    }


    //Hash 4->512 bits
    public byte[] Hash4(byte[] input)throws Exception{
        MessageDigest digest=MessageDigest.getInstance("SHA-512");
        return digest.digest(input);
    }

    //MAC（MD5的MAC）->128 bits
    /**
     * HMAC生成密钥
     */
    public static byte[] HmacKey(byte[] psw) {
        //加入BouncyCastleProvider的支持
        SecretKey key=null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            //产生密钥
            keyGenerator.init(128,new SecureRandom(psw));
            key = keyGenerator.generateKey();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return key.getEncoded();
    }


}
