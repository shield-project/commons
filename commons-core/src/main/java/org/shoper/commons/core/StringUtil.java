package org.shoper.commons.core;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ShawnShoper
 * @project Analyzer
 * @date Feb 27, 2015
 * @description 字符串操作的工具
 */
public final class StringUtil {
    public final static String EMPTY = "";
    public static final String SPLIT = "/";

    /**
     * 检查字符串中指定字符出现次数
     *
     * @param origin
     * @param target
     * @return
     */
    public static int wordCount(String origin, String target) {
        if (StringUtil.isAnyEmpty(origin, target))
            throw new IllegalArgumentException("Origin or Target can not be empty");
        return origin.split(target).length - 1;
    }


    /**
     * 字符串反转
     *
     * @param s
     * @return
     */
    public final static String reverse(String s) {
        char[] value = s.toCharArray();
        int count = s.length();
        boolean hasSurrogates = false;
        int n = count - 1;
        for (int j = (n - 1) >> 1; j >= 0; j--) {
            int k = n - j;
            char cj = value[j];
            char ck = value[k];
            value[j] = ck;
            value[k] = cj;
            if (Character.isSurrogate(cj) ||
                    Character.isSurrogate(ck)) {
                hasSurrogates = true;
            }
        }
        if (hasSurrogates) {
            reverseSurrogatePairs(count, value);
        }
        return String.copyValueOf(value);
    }

    private static void reverseSurrogatePairs(int count, char[] value) {
        for (int i = 0; i < count - 1; i++) {
            char c2 = value[i];
            if (Character.isLowSurrogate(c2)) {
                char c1 = value[i + 1];
                if (Character.isHighSurrogate(c1)) {
                    value[i++] = c1;
                    value[i] = c2;
                }
            }
        }
    }

    /**
     * 字符串转unicode码
     *
     * @param str
     * @return
     */
    public static String toUnicode(String str) {
        return StringEscapeUtils.escapeJava(str);
    }

    /**
     * unicode码转字符串
     *
     * @param str
     * @return
     */
    public static String unicodeToStr(String str) {
        return StringEscapeUtils.unescapeJava(str);
    }

    /**
     * url decode
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     * @author ShawnShoper
     * @date May 13, 2015
     * @description
     */
    public static String urlDecode(String url)
            throws UnsupportedEncodingException {
        return URLDecoder.decode(url, "utf-8");
    }

    /**
     * url encode
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     * @author ShawnShoper
     * @date May 13, 2015
     * @description
     */
    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String trimString(String origin, String... split) {
        for (String s : split) {
            if (origin.startsWith(s))
                origin = origin.substring(1);
            if (origin.endsWith(s))
                origin = origin.substring(0, origin.length() - 1);
            if (s.equals(origin))
                origin = "";
        }
        return origin;
    }

    public static String cutFL(String source, String prefix, String suffix) {
        if (prefix != null && !prefix.isEmpty() && suffix != null
                && !suffix.isEmpty())
            source = source.substring(
                    source.indexOf(prefix) + prefix.length(),
                    source.lastIndexOf(suffix)
            );
        return source;
    }

    /**
     * 验证一批字符串是否为 null 或者 "" 字符串<br>
     * Created by ShawnShoper 2016年5月20日
     *
     * @param contents
     * @return
     */
    public static boolean isAnyEmpty(String... contents) {
        return Arrays.stream(contents).parallel().anyMatch(StringUtil::isEmpty);
    }

    /**
     * 判断字符串是否为空字符串""<br>
     * Created by ShawnShoper 2016年6月2日
     *
     * @param content
     * @return
     */

    public static boolean isEmpty(String content) {
        return isNull(content) || content.trim().isEmpty();
    }


    public static boolean nonEmpty(String content) {
        return !isEmpty(content);
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    /**
     * fix the url ,clear ./ and ../ http://www.xxx.com/a/./../b.html to
     * http://www.xxx.com/b.html
     *
     * @param url
     * @return
     */
    public static String fixURL(String url) {
        if (isEmpty(url))
            throw new RuntimeException(
                    "The input url must not be null or empty");
        while (url.contains("/./")) {
            url = url.replace("/./", "/");
        }
        Pattern pattern = Pattern.compile("(?<=[^:])//");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            url = url.replaceAll("(?<=[^:])//", "/");
        }
        int index = 0;
        while ((index = url.indexOf("/../")) >= 0) {
            String prefix_nq = url.substring(0, index);
            String prefix = prefix_nq.substring(
                    0,
                    prefix_nq.lastIndexOf("/") + 1
            );
            String stufix = url.substring(index + 4);
            url = prefix + stufix;
        }
        return url;
    }

    /**
     * Check the string is digit
     *
     * @param digit
     * @return
     */
    public static boolean isDigit(String digit) {
        try {
            Long.valueOf(digit);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * split string by specified regular not container repeat word<br>
     * Created by ShawnShoper Apr 7, 2016
     *
     * @param str
     * @param regx
     * @return
     */
    public static Set<String> splitStrNoneRepeat(String str, String regx) {
        Set<String> sens = new HashSet<>();
        if (!isEmpty(str))
            for (String sen : str.split(regx))
                if (!isEmpty(sen))
                    sens.add(sen);
        return sens;
    }

    /**
     * split string by specified regular<br>
     * Created by ShawnShoper Apr 7, 2016
     *
     * @param str
     * @param regx
     * @return
     */
    public static List<String> splitStr(String str, String regx) {
        List<String> sens = new ArrayList<>();
        for (String sen : str.split(regx)) {
            if (!isEmpty(sen))
                sens.add(sen);
        }
        return sens;
    }
}
