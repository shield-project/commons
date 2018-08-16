package org.shoper.commons.encryption;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ShawnShoper
 * @date 2018/7/23 14:16
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "org.shoper.commons.test")
public class TextPro {
    private String text;
    private String name;
    private String password;
}
