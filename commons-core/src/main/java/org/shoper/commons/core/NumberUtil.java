package org.shoper.commons.core;

import java.util.Arrays;

/**
 * Created by ShawnShoper on 2017/3/3.
 */
public class NumberUtil {
    public static boolean isNumber(String... num) {
        if (StringUtil.isNull(num)) return false;

        return Arrays.stream(num).allMatch(e -> {
            try {
                Integer.parseInt(e);
            } catch (NumberFormatException ex) {
                return false;
            }
            return true;
        });
    }

    public static Integer[] parseNumber(String... num) {
        Integer[] nums = new Integer[num.length];
        int i = 0;
        for (String n : num)
            nums[i++] = parseNumber(n);
        return nums;
    }

    public static Integer parseNumber(String num) {
        if (!isNumber(num)) return null;
        Integer i = Integer.parseInt(num);
        return i;
    }
}
