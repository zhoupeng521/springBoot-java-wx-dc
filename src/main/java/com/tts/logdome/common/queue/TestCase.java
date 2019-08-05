package com.tts.logdome.common.queue;

/**
 * 判断回力数
 */
public class TestCase {

    public static void main(String[] args) {
        isPalindrome(121);
    }

    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }

        int num = 0;
        int k = x,g = x;

        while(g != 0){
            int i = 0;

            int pop = g % 10;

            int gg = g;

            while(gg != 0){
                i++;
                gg = gg / 10;
            }

            g = g / 10;

            int n = 1;

            while(i > 1){
                i--;
                n = n * 10;
            }

            num = num + pop * n;

        }
        if(num == k){
            return true;
        }
        return false;
    }

}
