package org.shoper.commons.encryption.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;
import lombok.extern.log4j.Log4j2;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.util.StringUtils;

@Log4j2
public class EncryptionPropertyResolver implements EncryptablePropertyResolver {
    private EncryptUtil.TextEncryptor textEncryptor;

    public EncryptionPropertyResolver() {
    }

    public EncryptionPropertyResolver(EncryptUtil.TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    @Override
    public String resolvePropertyValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        if (value.startsWith(EncryptUtil.text_prefix))
            return resolveDESValue(value);
        // 不需要解密的值直接返回
        return value;
    }

    private String resolveDESValue(String value) {
        String result = value;
        try {
            result = textEncryptor.decrypt(value);
        } catch (EncryptionOperationNotPossibleException e) {
            log.warn("Found need decryption property,but the secret-key maybe not matched,return the original value " + value);
        }
        return result;
    }
}