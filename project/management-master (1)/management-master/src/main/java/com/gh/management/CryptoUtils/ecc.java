package com.gh.management.CryptoUtils;

import com.gh.management.Configuration.Initialization;
import lombok.Data;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.WNafUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static org.bouncycastle.math.ec.ECConstants.TWO;

/**
 * @Author: zhangyan
 * @Date: 2020/9/23 22:51
 * @Version 1.0
 */
@Data
@Configuration
public class ecc {

    private X9ECParameters ecp;

    private ECDomainParameters domainParameters;

    private ECPoint P; //椭圆的生成元

    private BigInteger N;//椭圆的阶

    private ECKeyGenerationParameters keyGenerationParameters;

    private ECKeyPairGenerator generator;

    private Logger logger = Logger.getLogger("ecc");
    @Autowired
    private Initialization initialization;

    @Bean
    public void  setup_ecc() {
        logger.info("ecc_type:"+initialization.getEccType());
        this.ecp = SECNamedCurves.getByName(initialization.getEccType());
        this.domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());
        this.P = ecp.getG();
        this.N = ecp.getN();
        this.keyGenerationParameters = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        generator = new ECKeyPairGenerator();
        generator.init(keyGenerationParameters);

    }

    public ecc() {

        //"secp256r1"
        this.ecp = SECNamedCurves.getByName("secp256r1");
        this.domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());
        this.P = ecp.getG();
        this.N = ecp.getN();
        this.keyGenerationParameters = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        generator = new ECKeyPairGenerator();
        generator.init(keyGenerationParameters);

    }

    public ecc(String ecctype) {

        this.ecp = SECNamedCurves.getByName(ecctype);
        this.domainParameters = new ECDomainParameters(ecp.getCurve(), ecp.getG(), ecp.getN(), ecp.getH(), ecp.getSeed());
        this.P = ecp.getG();
        this.N = ecp.getN();
        this.keyGenerationParameters = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        generator = new ECKeyPairGenerator();
        generator.init(keyGenerationParameters);

    }

    //从有限域中随机生成,生成ECC的私钥
    public BigInteger generateRandomness() {
        SecureRandom random=keyGenerationParameters.getRandom();
        if (random == null) {
            random = CryptoServicesRegistrar.getSecureRandom();
        }
        BigInteger var1 = N;//椭圆的阶
        int var2 = var1.bitLength();
        int var3 = var2 >>> 2;

        BigInteger var4;
        do {
            do {
                var4 = new BigInteger(var2, random);
            } while(var4.compareTo(TWO) < 0);
        } while(var4.compareTo(var1) >= 0 || WNafUtil.getNafWeight(var4) < var3);

        return var4;
    }

    //将哈希值映射到上届为N的有限域中去
    public BigInteger hash2Zq(byte[] input) throws Exception{
        //这部分为调整ECC type时使用，一般用256
        String type=null;
        if (getN().bitLength()==112){
            type="SHA-"+getN().bitLength()*2;
        }else if (getN().bitLength()==521){
            type="SHA-512";
        } else {
            type="SHA-"+getN().bitLength();
        }

        MessageDigest digest=MessageDigest.getInstance(type);

        byte[] hash=digest.digest(input);

        //将二进制转化为正整数
        BigInteger randomNumber=new BigInteger(1,hash);

        BigInteger var1 = domainParameters.getN();//椭圆的阶
        int var2 = var1.bitLength();
        int var3 = var2 >>> 2;
        if (randomNumber.compareTo(TWO) <0){
            throw new Exception("随机数太短");
        }
        //TODO:如果随机数超过了N，就进行一个mod操作
        if (randomNumber.compareTo(var1) >= 0){
            System.out.println(("随机数超过了椭圆阶的大小或者不符合要求"));
            randomNumber=randomNumber.mod(var1);
        }

        if (randomNumber.compareTo(var1) >= 0 ||
                WNafUtil.getNafWeight(randomNumber) < var3){
            throw new Exception("随机数超过了椭圆阶的大小或者不符合要求");
        }

        return randomNumber;
    }

    //将byte映射到椭圆的点上
    public ECPoint hash2ECPoint(byte[] input) throws Exception{
        BigInteger zq=hash2Zq(input);

        return P.multiply(zq);

    }

    //BigInteger也就是私钥转化为字节数组byte[]
    public static byte[] toMyByteArray(BigInteger bi) {
        byte[] array = bi.toByteArray();
        if (array[0] == 0) {
            byte[] tmp = new byte[array.length - 1];
            System.arraycopy(array, 1, tmp, 0, tmp.length);
            array = tmp;

        }
        return array;
    }

    //!!!!!非常重要的一点
    public static BigInteger toMyBigInteger(byte[] input){
        return new BigInteger(1,input);

    }


    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String printHexString(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                builder.append('0' + hex);
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase());
            builder.append(hex);
        }
        System.out.println();
        return builder.toString();
    }

    //对ECPoint进行编码 不进行压缩的编码
    public byte[] encodeECPoint(ECPoint input){
        return input.getEncoded(false);
    }

    //二进制转EC
    public ECPoint decodeECPoint(byte[] input){
        //适应不同长度的ecc类型
        int a=0;
        if (getN().bitLength()==521){
            a=133;
        }else {
            a=getN().bitLength()*2/8+1;
        }
        //System.out.println(input.length);
        //int a=133;
        byte[] P2= new byte[a];

        System.arraycopy(input,0,P2,0,P2.length);
        return this.ecp.getCurve().decodePoint(P2);
    }

    //为设备生成公私钥对，并写入配置文件中
    public void writeEccKeyPairToFile(String filename) throws Exception {
        ECPoint generator=P;
        //私钥
        BigInteger eccPrivateKey=generateRandomness();
        //公钥
        ECPoint eccPublicKey=P.multiply(eccPrivateKey);

        ArrayList list = new ArrayList();
        list.add(encodeECPoint(generator));
        list.add(toMyByteArray(eccPrivateKey));
        list.add(encodeECPoint(eccPublicKey));

        writeObjectToText(list,filename);


    }

    public static void writeObjectToText(Object object, String path) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(new File(path))));

        oos.writeObject(object);
        oos.flush();
        oos.close();
    }

    //从配置文件读取ECC公钥私钥对
    public List readEccKeyPairFromFile(String filename) throws Exception {
        ArrayList list = (ArrayList) loadDevice(filename);
        ECPoint generator=decodeECPoint((byte[])list.get(0));

        BigInteger eccPrivateKey=toMyBigInteger((byte[])list.get(1));

        ECPoint eccPublicKey=decodeECPoint((byte[])list.get(2));

        ArrayList listResult=new ArrayList();
        listResult.add(generator);
        listResult.add(eccPrivateKey);
        listResult.add(eccPublicKey);
        return listResult;

    }
    //从txt读取设备的配置
    public Object loadDevice(String location) throws Exception {
        //反序列化读取
        BufferedInputStream bfi=new BufferedInputStream(new FileInputStream(location));
        ObjectInputStream ois = new ObjectInputStream(bfi);

        Object object = ois.readObject();

        ois.close();
        bfi.close();
        return object;
    }

    //ecc自检
    public boolean ecdh_verify(){

        ECPoint generator=getP();
        BigInteger a=generateRandomness();
        BigInteger b=generateRandomness();
        ECPoint A=generator.multiply(a);
        ECPoint B=generator.multiply(b);
        ECPoint a_B=B.multiply(a);
        ECPoint b_A=A.multiply(b);
        return a_B.equals(b_A);
    }

    //生成群私钥集合
    public List<BigInteger> generate_sk_group(int size,ecc ecc){
        List<BigInteger> res=new ArrayList();
        for (int i = 0; i < size; i++) {
            res.add(ecc.generateRandomness());
        }

        return res;
    }

    //计算群私钥
    public BigInteger generate_sk(){

        return generateRandomness();
    }


    public ECPoint[] readPPFromConfigFile(String filename, ecc ecc) throws Exception {

        ArrayList list = (ArrayList) loadDevice(filename);

        //0 p
        ECPoint P=ecc.decodeECPoint((byte[])list.get(0));
        //1 Q
        ECPoint Q=ecc.decodeECPoint((byte[])list.get(1));


        return new ECPoint[]{P,Q};

    }

    //把device写入list，然后写到配置文件中
    public void writePPToFile(String filename, List<ECPoint> list, ecc ecc) throws Exception {


        ArrayList res= new ArrayList();
        res.add(ecc.encodeECPoint(list.get(0)));//1. P
        res.add(ecc.encodeECPoint(list.get(1)));//1. Q

        writeObjectToText(res,filename);


    }


    public static void main(String[] args) throws Exception{

        //简单的测试
        ecc ecc=new ecc();

        ECPoint generator=ecc.getP();

        BigInteger sk=ecc.generateRandomness();

        ECPoint pk=generator.multiply(sk);

        byte[] pk_byte=ecc.encodeECPoint(pk);

        System.out.println("pk的长度(byte)："+pk_byte.length+"私钥的长度(bit):"+sk.bitLength());

        System.out.println("==========测试ECDH====================");

        BigInteger a=ecc.generateRandomness();

        BigInteger b=ecc.generateRandomness();

        ECPoint A=generator.multiply(a);

        ECPoint B=generator.multiply(b);

        ECPoint a_B=B.multiply(a);

        ECPoint b_A=A.multiply(b);

        System.out.println(a_B.equals(b_A));

        System.out.println("======为server生成公私钥===========");


//        //生成公私钥对，并存储在文件中
//        ecc.writeEccKeyPairToFile("in_ecc_key_pair");
//
//        List res=ecc.readEccKeyPairFromFile("in_ecc_key_pair");
//
//        ECPoint read_generator=(ECPoint)res.get(0);
//
//        System.out.println("=======验证读取的密钥是否正确===============");
//        System.out.println(read_generator.equals(generator));


    }


}
