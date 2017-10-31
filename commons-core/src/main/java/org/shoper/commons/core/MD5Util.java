package org.shoper.commons.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


    // 全局数组
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private MD5Util() {

    }

    /**
     * 返回形式为数字跟字符串
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:50
     */
    public static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 返回形式只为数字
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:50
     */
    public static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:49
     */

    private static String byteToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            stringBuilder.append(byteToArrayString(bytes[i]));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取MD5的字符串形式
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:49
     */
    public static String getMD5Code(String strObj) {
        try {
            return byteToString(getMD5(strObj));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取MD5的byte array
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:49
     */
    public static byte[] getMD5(String strObj) throws NoSuchAlgorithmException {
        // md.digest() 该函数返回值为存放哈希值结果的byte数组
        return MessageDigest.getInstance("MD5").digest(strObj.getBytes());
    }
}
