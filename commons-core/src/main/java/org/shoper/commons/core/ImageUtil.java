package org.shoper.commons.core;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片信息类。
 *
 * @author ShawnShoper
 * @date 2017/1/18 0018 16:43
 */


/**
 * 图片工具包,提供图片获取高宽以及转MD5
 * Created by ShawnShoper on 16/10/8.
 * version	0.0.2
 */
public class ImageUtil {
    static class ImageInfo {
        private int width;
        private int heigt;

        public int getHeigt() {
            return heigt;
        }

        public void setHeigt(int heigt) {
            this.heigt = heigt;
        }

        public ImageInfo(int width, int heigt) {
            this.width = width;
            this.heigt = heigt;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
    /**
     * Read image basic properties
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static ImageInfo readImageToInfo(InputStream inputStream) throws IOException {
        BufferedImage bi = getBufferedImage(inputStream);
        return new ImageInfo(bi.getWidth(), bi.getHeight());
    }

    /**
     * convert R-G-B point to byte array
     * 图片转byte array
     * @param bi
     * @return
     */
    public static byte[] toRGBBytes(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        List <Byte> byteList = new ArrayList <>();
        for (int i = miny; i < height; i++) {
            for (int j = minx; j < width; j++) {
                int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                int r = pixel << 8 >>> 24;
                int g = pixel << 16 >>> 24;
                int b = pixel << 24 >>> 24;
//				rgb[0] = (pixel & 0xff0000) >> 16;
//				rgb[1] = (pixel & 0xff00) >> 8;
//				rgb[2] = (pixel & 0xff);
                byte[] bytes = new byte[]{(byte) r, (byte) g, (byte) b};
                byteList.add(bytes[0]);
                byteList.add(bytes[1]);
                byteList.add(bytes[2]);
            }
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes[i] = byteList.get(i).byteValue();
        }
        return bytes;

    }

    /**
     * get buffered image from inputstream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static BufferedImage getBufferedImage(InputStream inputStream) throws IOException {
        BufferedImage bi = ImageIO.read(inputStream);
        System.out.println(bi);
        return bi;
    }

    /**
     * 图片转base64
     *
     * @author ShawnShoper
     * @date 2017/1/18 0018 16:44
     */
    public static String toBase64(InputStream inputStream) throws IOException {
        BufferedImage bi = getBufferedImage(inputStream);
        return new BASE64Encoder().encode(toRGBBytes(bi));
    }
}
