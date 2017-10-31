package org.shoper.commons.captcha;

import java.awt.*;

/**
 * Created by ShawnShoper on 2017/3/7.
 */
public class BackGroundColor {
    private int r;
    private int g;
    private int b;
    private int a;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public BackGroundColor(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public BackGroundColor() {

    }

    public Color build() {
        return new Color(r, g, b, a);
    }
}
