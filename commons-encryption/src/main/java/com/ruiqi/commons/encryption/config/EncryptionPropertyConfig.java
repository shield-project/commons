package com.ruiqi.commons.encryption.config;

import com.ruiqi.commons.encryption.config.property.JasyptEncryptorProperty;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Log4j
public class EncryptionPropertyConfig {

    @Bean
    public JasyptEncryptorProperty jasyptEncryptorProperty() throws IOException {
        JasyptEncryptorProperty jasyptEncryptorProperty = new JasyptEncryptorProperty();
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/encrypted.properties"));
            String secretKey = properties.getProperty("secret-key");
            jasyptEncryptorProperty.setSecretKey(secretKey);
        } catch (FileNotFoundException e) {
            log.warn("Project has not found encrypted.properties,use the default properties");
        }
        return jasyptEncryptorProperty;
    }

    @Bean
    @ConditionalOnMissingBean(value = {EncryptUtil.TextEncryptor.class})
    public EncryptUtil.TextEncryptor textEncryptor(JasyptEncryptorProperty jasyptEncryptorProperty) {
        return new EncryptUtil.TextEncryptor(jasyptEncryptorProperty.getSecretKey());
    }

    @Bean
    @ConditionalOnMissingBean(value = {EncryptUtil.PassworldEncryptor.class})
    public EncryptUtil.PassworldEncryptor passworldEncryptor() {
        return new EncryptUtil.PassworldEncryptor();
    }

    @Bean(name = "encryptablePropertyResolver")
    public EncryptablePropertyResolver encryptablePropertyResolver(EncryptUtil.TextEncryptor textEncryptor) {
        return new EncryptionPropertyResolver(textEncryptor);
    }


}