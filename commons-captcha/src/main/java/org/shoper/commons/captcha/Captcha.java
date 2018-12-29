package org.shoper.commons.captcha;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.awt.*;

/**
 * Created by ShawnShoper on 2017/3/2.
 */
@ConfigurationProperties(prefix = "com.daqsoft.captcha")
@Data
public class Captcha {
    private boolean animate;
    //设置字体行间距
    private int worldSpace = 20;
    //背景色
    @NestedConfigurationProperty
    private BackGroundColor backGroundColor = new BackGroundColor(255, 255, 255, 255);
    // 图片的宽度。
    private int width = 150;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int interferingLineCount = 150;
    // 验证码
    private int fontSize = 20;
    private String fontName = Font.SERIF;
    private FontStyle fontStyle = FontStyle.BOLD;
    private String randomCode = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private String codeUnicodeScope;
}
