package org.shoper.commons.captcha;

import org.shoper.commons.captcha.exception.CaptchaException;
import org.shoper.commons.captcha.simple.CaptchaGenerator;

import java.awt.image.BufferedImage;

/**
 * Created by ShawnShoper on 2017/3/6.
 */

public class CaptchaBufferImage {
    public CaptchaBufferImage(CaptchaGenerator captchaGenerator) {
        this.captchaGenerator = captchaGenerator;
    }

    private CaptchaGenerator captchaGenerator;
    private String code;
    private BufferedImage bufferedImage;
    public CaptchaBufferImage generateCaptcha() throws CaptchaException {
        this.code = captchaGenerator.generateCode();
        this.bufferedImage = captchaGenerator.generateImage(this.code);
        return this;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
