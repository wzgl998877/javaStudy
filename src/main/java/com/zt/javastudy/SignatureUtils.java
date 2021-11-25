package com.zt.javastudy;

import org.apache.commons.codec.binary.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignatureUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class);


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 1024;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 1024;

    public static void main(String[] args){

    }

    public static String RSA2Sign(String content, String key) {
        try {
            //logger.info("私钥字符串：" + key);
            PrivateKey priKey = restorePrivateKey(Base64.decodeBase64(key));
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes("UTF-8"));
            byte[] signed = signature.sign();
            String sign = new String(Base64.encodeBase64(signed), "UTF-8");
            //logger.info("生成签名: " + sign);
            return sign;
        } catch (Exception e) {
            throw new RuntimeException("RSAcontent = " + content + "; charset = " + "UTF-8", e);
        }
    }

    /**
     * 还原私钥
     *
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原公钥
     *
     * @param keyBytes
     * @return
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取签名内容
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (StringUtils.isNotEmpty(key)&&StringUtils.isNotEmpty(value)) {
                // 参与签名的value，都进行一下trim
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        return content.toString();
    }
    /**
     * 获取签名内容
     * @param sortedParams
     * @return
     */
    public static String getObjSignContent(Map<String, Object> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(sortedParams.get(key));
            if (StringUtils.isNotEmpty(key)&& StringUtils.isNotEmpty(value)) {
                // 参与签名的value，都进行一下trim
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                ++index;
            }
        }
        return content.toString();
    }

    /**
     * 验签
     * @param content
     * @param sign
     * @param key
     * @return
     */
    public static boolean RSA2CheckContent(String content, String sign, String key)  {
        try {
            //logger.info("公钥字符串： " + key);
            PublicKey pubKey = restorePublicKey(Base64.decodeBase64(key));

            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initVerify(pubKey);

            //logger.info("验签内容： " + content);
            signature.update(content.getBytes("UTF-8"));
            boolean result = signature.verify(Base64.decodeBase64(sign.getBytes()));
            logger.info("验签结果： " + result);
            return result;
        } catch (Exception e) {
        	logger.error("RSAcontent = " + content + ",sign=" + sign + ",key = " + key+",验签产生异常["+ e +"]");

            throw new RuntimeException("RSAcontent = " + content + ",sign=" + sign + ",key = " + key, e);
        }
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param key 公钥
     * @return
     */
    public static String encrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        PublicKey publicKey = restorePublicKey(Base64.decodeBase64(key));
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param key 私钥
     * @return
     */
    public static String decrypt(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        PrivateKey privateKey = restorePrivateKey(Base64.decodeBase64(key));
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }
    
    /*
     * @Author kf1041
     * @Description  对签名字段排序拼接
     * @Date 2020/6/15 0015 16:02
     * @Param 
     * @return 
     **/
    public static String  signSort(Map<String, Object> sortedParams){
        if(null==sortedParams || sortedParams.size()==0){
            logger.info("待加签集合为空");
            return "";
        }
        StringBuffer content = new StringBuffer();
        List<Map.Entry<String,Object>> list=new ArrayList<>(sortedParams.entrySet());
        //对参数排序
        Collections.sort(list, (o1,o2)-> { return o1.getKey().compareTo(o2.getKey()); });
        int index = 0;
        for (Map.Entry<String, Object> entry : list) {
            if (StringUtils.isNotBlank(entry.getKey()) && null!=entry.getValue() && StringUtils.isNotBlank(entry.getValue().toString())) {
                // 参与签名的value，都进行一下trim
                content.append((index == 0 ? "" : "&") + entry.getKey() + "=" + entry.getValue().toString().trim());
                ++index;
            }
        }
        return content.toString() ;
    }

    /*
     * @Author kf1041
     * @Description  数字礼券对签名字段排序拼接
     * @Date 2020/6/15 0015 16:02
     * @Param
     * @return
     **/
    public static String  couponSignSort(Map<String, String> sortedParams){
        if(null==sortedParams || sortedParams.size()==0){
            logger.info("待签名字段集合为空");
            return "";
        }
        StringBuffer content = new StringBuffer();
        List<Map.Entry<String,String>> list=new ArrayList<>(sortedParams.entrySet());
        //对参数排序
        Collections.sort(list, (o1,o2)-> { return o1.getKey().compareTo(o2.getKey()); });
        int index = 0;
        for (Map.Entry<String, String> entry : list) {
            if (StringUtils.isNotBlank(entry.getKey()) && null!=entry.getValue() && StringUtils.isNotBlank(entry.getValue())) {
                // 参与签名的value，都进行一下trim
                content.append((index == 0 ? "" : "&") + entry.getKey() + "=" + entry.getValue().toString().trim());
                ++index;
            }
        }
        return content.toString() ;
    }

}
