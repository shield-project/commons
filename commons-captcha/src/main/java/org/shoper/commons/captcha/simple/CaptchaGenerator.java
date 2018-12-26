package org.shoper.commons.captcha.simple;

import org.shoper.commons.captcha.Captcha;
import org.shoper.commons.captcha.exception.CaptchaException;
import org.shoper.commons.core.NumberUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

/**
 * Created by ShawnShoper on 2017/3/3.
 */
public class CaptchaGenerator {
    private Captcha captcha;

    public CaptchaGenerator(Captcha captcha) {
        this.captcha = captcha;
    }

    public BufferedImage generateImage(String code) {
        BufferedImage buffImg = new BufferedImage(this.captcha.getWidth(), this.captcha.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buffImg.createGraphics();
        //设置背景色
        g.setColor(Objects.isNull(this.captcha.getBackGroundColor())?Color.WHITE:this.captcha.getBackGroundColor().build());
        g.fillRect(0, 0, this.captcha.getWidth(), this.captcha.getHeight());
        g.setFont(getFont());
        Random random = new Random();
        //绘画干扰线
        for (int i = 0; i < this.captcha.getInterferingLineCount(); i++) {
            int xs = random.nextInt(captcha.getWidth());
            int ys = random.nextInt(captcha.getHeight());
            int xe = xs + random.nextInt(captcha.getWidth() / 8);
            int ye = ys + random.nextInt(captcha.getHeight() / 8);
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            int alpha = random.nextInt(255);
            g.setColor(new Color(red, green, blue, alpha));
            g.drawLine(xs, ys, xe, ye);
        }
        //绘画字体
        char[] world = code.toCharArray();
        //character width
        int wd = (captcha.getWidth() - captcha.getWorldSpace() * captcha.getCodeCount()) / 2;
        //character height
        int wh = captcha.getHeight() - (captcha.getHeight() - captcha.getFontSize()) / 2;
        for (int i = 0; i < this.captcha.getCodeCount(); i++) {
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            int alpha = random.nextInt(100) + 155;
            g.setColor(new Color(red, green, blue, alpha));
            g.drawString(world[i] + "", wd + i * captcha.getWorldSpace(), wh);
        }
        return buffImg;
    }

    private Font getFont() {
        return new Font(this.captcha.getFontName(), this.captcha.getFontStyle().getOrdinal(), this.captcha.getFontSize());
    }

    public String generateCode() throws CaptchaException {
        int codeCount = this.captcha.getCodeCount();
        StringBuilder code = new StringBuilder(codeCount);
        Random random = new Random();
        if (codeCount < 1)
            throw new CaptchaException("code count not has be less than 1");
        if (Objects.nonNull(this.captcha.getCodeUnicodeScope())) {
            String[] scope = this.captcha.getCodeUnicodeScope().split("-");
            if (!NumberUtil.isNumber(scope))
                throw new CaptchaException("unicode scope must be number");
            Integer[] nums = NumberUtil.parseNumber(scope);
            for (int count = codeCount; count > 0; --count) {
                int codeScope = random.nextInt(nums[1]) % (nums[1] - nums[0] + 1) + nums[0];
                char word = (char) codeScope;
                code.append(word);
            }
        } else {
            for (int count = codeCount; count > 0; --count) {
                char[] words = this.captcha.getRandomCode().toCharArray();
                int codeIndex = random.nextInt(words.length) % (words.length + 1);
                char word = words[codeIndex];
                code.append(word);
            }
        }
        return code.toString();
    }
}
