package com.tts.logdome.common.queue;

/**
 * @创建人 zp
 * @创建时间 2019/7/31
 * @描述 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] number = {34,12,24,22,55,33,1,11,8,7,3,2,5,6,23};
        sort(number,0,number.length-1);

        for (int i = 0; i < number.length; i ++){
            System.out.print(number[i]+",");
        }
    }

    /**
     * 排序
     * @param number
     */
    public static void sort(int[] number,int start,int end){
        int i = start;
        int j = end;
        int temp = number[start];
        while (i < j){

            while (i < j && number[j]-temp >= 0){
                j --;
            }

            if(i < j){
                number[i] = number[j];
                i ++;
            }

            while (i < j && number[i]-temp <= 0){
                i ++ ;
            }

            if(i < j){
                number[j] = number[i];
                j --;
            }

        }
        //i == j
        number[i] = temp;

        if(i-1 > start){
            sort(number,start,i-1);
        }

        if(j+1 < end){
            sort(number,j+1,end);
        }

    }

}
