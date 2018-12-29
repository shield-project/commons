package org.shoper.commons.captcha.simple;

import org.shoper.commons.captcha.BackGroundColor;
import org.shoper.commons.captcha.Captcha;
import org.shoper.commons.captcha.exception.CaptchaException;
import org.shoper.commons.core.NumberUtil;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ShawnShoper on 2017/3/3.
 */
public class CaptchaGenerator {
    private Captcha captcha;
    int loopCount = 1;
    private final static String tmpDir = System.getProperty("java.io.tmpdir");

    public CaptchaGenerator(Captcha captcha) {
        this.captcha = captcha;
        if (captcha.isAnimate()) loopCount = 8;
    }

    public BufferedImage generateImage(String code) {
        BufferedImage bufferedImage = null;
        if (!captcha.isAnimate())
            bufferedImage = generateImage(code, captcha.isAnimate());
        else {
            String fileName = tmpDir + File.separator + UUID.randomUUID().toString() + ".gif";
            GifSequenceWriter gifSequenceWriter = null;
            try {
                ImageOutputStream outputStream = new FileImageOutputStream(new File(fileName));
                gifSequenceWriter = new GifSequenceWriter(outputStream, 1, 500, true);
                for (int i = 0; i < loopCount; i++) {
                    bufferedImage = generateImage(code, captcha.isAnimate());
                    gifSequenceWriter.writeToSequence(bufferedImage);
                }
                gifSequenceWriter.close();

                FileImageInputStream fileImageInputStream = new FileImageInputStream(new File(fileName));
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileImageInputStream.length());

                bufferedImage = ImageIO.read(new File(fileName));

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "gif", byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                bufferedImage = ImageIO.read(byteArrayInputStream);
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                if (new File(fileName).delete()) {
                    System.out.println("删除成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bufferedImage;
    }

    public BufferedImage generateImage(String code, boolean animate) {
        BufferedImage buffImg = new BufferedImage(this.captcha.getWidth(), this.captcha.getHeight(), animate ? BufferedImage.TYPE_INT_BGR : BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = buffImg.createGraphics();
        //设置背景色
        g.setColor(Objects.isNull(this.captcha.getBackGroundColor()) ? Color.WHITE : this.captcha.getBackGroundColor().build());
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
            if (animate)
                g.setColor(new Color(red, green, blue));
            else
                g.setColor(new Color(red, green, blue, alpha));
            g.drawString(world[i] + "", wd + i * captcha.getWorldSpace(), wh);
        }

        return buffImg;

    }

    public static void main(String[] args) throws Exception {
        System.out.println();
        Captcha captcha = new Captcha();
        captcha.setAnimate(true);
        captcha.setBackGroundColor(new BackGroundColor(120, 120, 120, 0));
        CaptchaGenerator captchaGenerator = new CaptchaGenerator(captcha);
        String code = captchaGenerator.generateCode();
        BufferedImage bufferedImage = captchaGenerator.generateImage(code);
        ImageIO.write(bufferedImage, "gif", new File("D://img/aaaa.gif"));
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
