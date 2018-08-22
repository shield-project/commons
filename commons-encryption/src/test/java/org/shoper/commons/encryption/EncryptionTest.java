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
        EncryptUtil.TextEncryptor textEncryptor = new EncryptUtil.TextEncryptor("fitness-testing_random12349sadjn~21321n");
        String text = textEncryptor.encrypt("jdbc:mysql://127.0.0.1:3306/fitness-testing?useSSL=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false");
        System.out.println(text);
    }
    @Test
    public void textDecryptorTest(){
        EncryptUtil.TextEncryptor textEncryptor = new EncryptUtil.TextEncryptor("fitness-testing_random12349sadjn~21321n");
        String text = textEncryptor.decrypt(" tn435m9qibvJMJPln/hDPhaFkSV5dJxvFH3SkmLrxJZfYs8HtJV8qiRAynMjmgVfzpktG6zQMN4zjlYluVPB069UmzDB7lOfNqMVen3gxmEQ46abOPIZ9EQ1/iGPWNrRS4SWu0M52V5i4JsSZ6+qgmGBwJ89qKfoaBLxnV8oWYxjaJUz/sMNqAQ3WLcxDs2LDJYwXuo7glJo=");
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
