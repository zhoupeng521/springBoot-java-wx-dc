package com.tts.logdome.common.queue;

/**
 * 希尔算法
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] number = {1,34,12,24,22,55,33,11,8,2,2,7,3,2,5,6};
        int d = number.length;
        while (true){

            for (int x = 0; x < d;x ++){
                 d =  d / 2;
                 for (int i = x + d;i < number.length; i = i + d){
                     int temp = number[i];
                     int j ;
                     for (j = i - d; j >= 0 && number[j] > temp; j = j - d){
                         number[j + d] = number[j];
                     }
                     number[j + d] = temp;
                 }
            }
            if(d == 1){
                break;
            }
        }

        for ( int i = 0; i < number.length; i ++ ){
            System.out.print(number[i]+",");
        }
    }

}
