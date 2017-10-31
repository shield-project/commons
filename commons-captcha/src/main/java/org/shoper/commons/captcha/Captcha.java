package org.shoper.commons.captcha;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.awt.*;

/**
 * Created by ShawnShoper on 2017/3/2.
 */
@ConfigurationProperties(prefix = "com.daqsoft.captcha")
public class Captcha {
    //设置字体行间距
    private int worldSpace = 20;
    //背景色
    @NestedConfigurationProperty
    private BackGroundColor backGroundColor = new BackGroundColor(255, 255, 255,255);
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

    public BackGroundColor getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(BackGroundColor backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public int getWorldSpace() {
        return worldSpace;
    }

    public void setWorldSpace(int worldSpace) {
        this.worldSpace = worldSpace;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

    public int getInterferingLineCount() {
        return interferingLineCount;
    }

    public void setInterferingLineCount(int interferingLineCount) {
        this.interferingLineCount = interferingLineCount;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getCodeUnicodeScope() {
        return codeUnicodeScope;
    }

    public void setCodeUnicodeScope(String codeUnicodeScope) {
        this.codeUnicodeScope = codeUnicodeScope;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
