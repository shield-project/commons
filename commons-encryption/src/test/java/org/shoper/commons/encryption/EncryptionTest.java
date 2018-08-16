package org.shoper.commons.encryption;

import org.shoper.commons.encryption.config.EncryptUtil;
import org.junit.Test;

/**
 * @author ShawnShoper
 * @date 2018/7/23 16:06
 */
public class EncryptionTest {
    @Test
    public void textEncryptorTest(){
        EncryptUtil.TextEncryptor textEncryptor = new EncryptUtil.TextEncryptor("asdsad");
        String text = textEncryptor.encrypt("asdsadsadasas");
        System.out.println(text);
    }
    @Test
    public void textDecryptorTest(){
        EncryptUtil.TextEncryptor textEncryptor = new EncryptUtil.TextEncryptor("asdsad");
        String text = textEncryptor.decrypt(" tQS4KVcHxZggAPD/zhJCsICKDH77AUi4G");
        System.out.println(text);
    }
    @Test
    public void passwordEncryptorTest(){
        EncryptUtil.PassworldEncryptor passworldEncryptor = new EncryptUtil.PassworldEncryptor();
        String text = passworldEncryptor.encrypt("asdsadsadasas");
        System.out.println(text);
    }
    @Test
    public void passwordDecryptorTest(){
        String password = "  pqiG33SasYj9LPetnz2k5Jv4u9xjXr2T2vgNg6hBM37J+YlIp1LpPn+kGmYaKDbwX";
        EncryptUtil.PassworldEncryptor textEncryptor = new EncryptUtil.PassworldEncryptor();
        boolean check = textEncryptor.check("asdsadsadasas", password);
        System.out.println(check);
    }
}
