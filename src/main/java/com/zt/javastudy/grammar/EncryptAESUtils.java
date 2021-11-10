package com.zt.javastudy.grammar;
 
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class EncryptAESUtils {
 
    /**
     * 加密算法AES
     */
    public static String encryptAES(String value, String key) throws Exception {
        if (null == value || "".equals(value)) {
            return "";
        }
 
        byte[] valueByte = value.getBytes();
        byte[] sl = encryptAES(valueByte, EncryptAESUtils.hexToBytes(key));
        String result = Base64.encodeBase64String(sl);
 
        return result;
    }
 
    public static byte[] encryptAES(byte[] input, byte[] key) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        return c.doFinal(input);
    }
 
    /**
     * 将16进制的字符串转换成bytes
     *
     * @param hex
     * @return 转化后的byte数组
     */
    public static byte[] hexToBytes(String hex) {
        return hexToBytes(hex.toCharArray());
    }
 
    /**
     * 将16进制的字符数组转换成byte数组
     *
     * @param hex
     * @return 转换后的byte数组
     */
    public static byte[] hexToBytes(char[] hex) {
        int length = hex.length / 2;
        byte[] raw = new byte[length];
        for (int i = 0; i < length; i++) {
            int high = Character.digit(hex[i * 2], 16);
            int low = Character.digit(hex[i * 2 + 1], 16);
            int value = (high << 4) | low;
            if (value > 127) {
                value -= 256;
            }
            raw[i] = (byte) value;
        }
        return raw;
    }
 
    public static void main(String[] args) {
        try {
            String key_AES = "B4id6+t7VHP0ttJz6M7vwQ==";
            String jsonStr = "ZRJtRXKHGEyXEgJtlMSaFCmmwfJ90FlXxccnPERSTsFKsV79ETA2cXflr2qry9l0";
            String encryptedValue  = EncryptAESUtils.encryptAES(jsonStr, key_AES);
            System.out.println(encryptedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}