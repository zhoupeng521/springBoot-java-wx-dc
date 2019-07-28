package com.tts.logdome.common.queue;

/**
 * 直接选择排序
 */
public class DirectSelectSort {

    public static void main(String[] args) {

        int[] number = {1,34,12,24,22,55,33,11,8,2,2,7,3,2,5,6};

        for ( int i = 0;i < number.length; i ++ ){
            int min = number[i];
            int n = i;
            for (int j = i + 1;j < number.length;j++){
                if(min > number[j]){
                    min = number[j];
                    n = j;
                }
            }
            number[n] = number[i];
            number[i] = min;
        }

        for ( int i = 0; i < number.length; i ++ ){
            System.out.print(number[i]+",");
        }

    }

}
