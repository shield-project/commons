package org.shoper.commons.captcha.config;

import org.shoper.commons.captcha.Captcha;
import org.shoper.commons.captcha.CaptchaBufferImage;
import org.shoper.commons.captcha.simple.CaptchaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ShawnShoper on 2017/3/6.
 */
@Configuration
@EnableConfigurationProperties(Captcha.class)
public class CaptchaConfig {
    @Autowired
    Captcha captcha;
    @Bean
    public CaptchaGenerator captchaGenerator(){
        return new CaptchaGenerator(captcha);
    }
    @Bean
    public CaptchaBufferImage getCaptchaBufferImage(){
        return new CaptchaBufferImage(captchaGenerator());
    }
}
