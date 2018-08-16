package org.shoper.commons.encryption.config;

import lombok.extern.log4j.Log4j2;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * @author ShawnShoper
 * @date 2018/7/18 11:22
 */
@Log4j2
public class EncryptUtil {

    public static final String prefix = "\u0020";
    public static final String text_prefix = prefix + "t";
    public static final String password_prefix = prefix + "p";

    /**
     * Passworld encryptor.
     * 提供密码校验器
     */
    public static class PassworldEncryptor {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

        public String encrypt(String text) {
            return password_prefix + this.encryptor.encryptPassword(text);
        }

        public boolean check(String password, String encrypted) {
            if (!encrypted.startsWith(password_prefix))
                throw new IllegalArgumentException("校验值类型不正确,缺少Password校验位");
            return this.encryptor.checkPassword(password, encrypted.replace(password_prefix, ""));
        }
    }

    /**
     * Text encryptor
     * 提供文本加密机
     */
    public static class TextEncryptor {
        StrongTextEncryptor strongTextEncryptor = new StrongTextEncryptor();

        public TextEncryptor(String secretKey) {
            this.strongTextEncryptor.setPassword(secretKey);
        }

        public String encrypt(String text) {
            return text_prefix + this.strongTextEncryptor.encrypt(text);
        }

        public String decrypt(String text) {
            if (!text.startsWith(text_prefix))
                throw new IllegalArgumentException("校验值类型不正确,缺少Text校验位");
            return this.strongTextEncryptor.decrypt(text.replace(text_prefix, ""));
        }
    }

}
