package org.shoper.commons.captcha;

import org.shoper.commons.captcha.exception.CaptchaException;
import org.shoper.commons.captcha.simple.CaptchaGenerator;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ShawnShoper on 2017/3/6.
 */
public class CaptchaTest {
    @Test
    public void captchaTest() throws CaptchaException, IOException {
        Captcha captcha = new Captcha();
        captcha.setCodeCount(4);
        captcha.setRandomCode("1234567890");
        captcha.setFontSize(30);
        captcha.setBackGroundColor(new BackGroundColor(255, 255, 255, 0));
        CaptchaGenerator captchaGenerator = new CaptchaGenerator(captcha);
        BufferedImage bufferedImage = captchaGenerator.generateImage(captchaGenerator.generateCode());
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
//        jpegEncoder.encode(bufferedImage);
        ImageIO.write(bufferedImage, "png", jpegOutputStream);
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        FileOutputStream fileOutputStream = new FileOutputStream("s.png");
        fileOutputStream.write(captchaChallengeAsJpeg);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Test
    public void voice() {
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

        Dispatch sapo = sap.getObject();
        try {

            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(2));

            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant("汪，你是个大傻逼"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
