package com.daqsoft.commons.reponseEntity;

/**
 * Created by ShawnShoper on 2017/2/23.
 */
public class Test {
    public static void main(String[] args) {
        int totalCounts = 11;
        int pageSize = 2;
        int b = (int)Math.ceil((double)totalCounts/(double)pageSize);
        System.out.println(b);
    }
}
