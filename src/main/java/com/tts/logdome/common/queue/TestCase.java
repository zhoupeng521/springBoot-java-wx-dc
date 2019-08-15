package com.tts.logdome.common.queue;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 算法题练习
 */
public class TestCase {

    public static void main(String[] args) {
        //isPalindrome(121);
//        int romanToInt = romanToInt("MMCDIII");
//        System.out.println(romanToInt);
       /* String s = longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println("s = "+s);*/
        TestCase testCase = new TestCase();
        System.out.println(testCase.isValid2("{}[]{}"));
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

    /**
     * 最长公共前缀
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * @param strs
     * @return
     */
    /*public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        String temp = strs[0];
        for (int i = 1;i < strs.length;i ++){

            while (strs[i].indexOf(temp) != 0){
                temp = temp.substring(0,temp.length() - 1);
                if(temp.isEmpty()){
                    return "";
                }
            }
        }
        return temp;
    }*/

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    static String  commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) )
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }

    /**
     *  有效的括号--错误
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if("".equals(s)){
            return true;
        }
        int len = s.length();
        if(len % 2 != 0){
            return false;
        }
        char c;
        for(int i = 0;i < len / 2; i ++){
            switch (s.charAt(i)){
                case '{':
                    c = '}';
                    break;
                case '[':
                    c = ']';
                    break;
                case '(':
                    c = ')';
                    break;
                default:
                    c = s.charAt(i);

            }
            if(c == s.charAt(len - 1 - i)){
                continue;
            }
            if(s.charAt(i + 1) == c){
                i ++;
                continue;
            }
            return false;
        }

        return true;
    }


    private Map<Character,Character> map;

    public TestCase(){

        map = new HashMap<>();
        this.map.put(')','(');
        this.map.put('}','{');
        this.map.put(']','[');
    }
    /**
     *  有效的括号--正确
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        //创建栈
        Stack<Character> stack = new Stack<>();

        for (int i = 0;i < s.length();i ++){

            char c = s.charAt(i);

            if(this.map.containsKey(c)){

                char toElement = stack.empty() ? '#' : stack.pop();

                if(toElement != this.map.get(c)){
                    return false;
                }
            }else{
                stack.push(c);
            }

        }
        return stack.isEmpty();

    }

}
