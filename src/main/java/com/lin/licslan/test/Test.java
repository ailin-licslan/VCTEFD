package com.lin.licslan.test;

public class Test {


    public static void main(String[] args) {
        System.out.println("hello world");


        int[] freq = new int[100];
        for (int i = 1; i <= 22; i++) {
            int coupon = i;
            int sum = 0;
            while (coupon > 0) {
                sum += coupon % 10;
                coupon /= 10;
            }
            freq[sum]++;
        }

        int maxFreq = 0;
        for (int i = 1; i <= 81; i++) {
            maxFreq = Math.max(maxFreq, freq[i]);
        }
        int count = 0;
        for (int i = 1; i <= 81; i++) {
            if (freq[i] == maxFreq) {
                count++;
            }
        }
        System.out.println(count);

    }

}
