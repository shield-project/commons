package org.shoper.commons.encryption.config.property;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ShawnShoper
 * @date 2018/7/23 15:46
 */
@Getter
@Setter
public class JasyptEncryptorProperty {
    private final String default_secretKey = "test";
    private String secretKey = default_secretKey;
}
