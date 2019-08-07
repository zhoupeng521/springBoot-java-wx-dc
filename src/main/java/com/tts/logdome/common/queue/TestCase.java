package com.tts.logdome.common.queue;

/**
 * 算法题练习
 */
public class TestCase {

    public static void main(String[] args) {
        //isPalindrome(121);
        int romanToInt = romanToInt("MMCDIII");
        System.out.println(romanToInt);
    }

    /**
     * 判断回力数
     */
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

    /**
     * 罗马数字转成阿拉伯数字
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0;i <chars.length;i ++){
            switch (chars[i]){
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
            }
        }

        for (int i = 0;i <chars.length - 1;i ++){
            switch (""+chars[i] + chars[i + 1]){
                case "IV":
                case "IX":
                    result -= 2;
                    break;
                case "XL":
                case "XC":
                    result -= 20;
                    break;
                case "CD":
                case "CM":
                    result -= 200;
                    break;
            }
        }
        return result;
    }



}
