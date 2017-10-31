package org.shoper.commons.core;

import org.junit.Test;

import java.net.MalformedURLException;

public class URLParserTests {
    @Test
    public void parameterTest() throws MalformedURLException {
        URLParser urlParser = URLParser.newInstance("http://www.baidu.com?as=wq1&redirect_url=http%3a%2f%2fwww.baidu.com");
        urlParser.parseParameter(false, true).getParameters().forEach((e, b) -> {
            System.out.println("e->>");
            b.forEach(c -> {
                System.out.print(c);
                System.out.print(" ");
            });
            System.out.println();
        });
    }

    @Test
    public void hashTest() throws MalformedURLException {
        URLParser urlParser = URLParser.newInstance("http://www.baidu.com#as=wq1&redirect_url=http%3a%2f%2fwww.baidu.com");
        urlParser.parseParameter(true, true).getParameters().forEach((e, b) -> {
            System.out.println("e->>");
            b.forEach(c -> {
                System.out.print(c);
                System.out.print(" ");
            });
            System.out.println();
        });
    }
}
