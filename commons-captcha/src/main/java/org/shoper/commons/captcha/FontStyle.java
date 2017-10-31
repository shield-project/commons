package org.shoper.commons.captcha;

public enum FontStyle {
    PLAIN(0), BOLD(1),
    ITALIC(2);
    private int ordinal;

    public int getOrdinal() {
        return ordinal;
    }

    FontStyle(int ordinal) {
        this.ordinal = ordinal;
    }
}