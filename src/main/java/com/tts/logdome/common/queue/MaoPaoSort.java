package com.tts.logdome.common.queue;

/**
 * @创建人 zp
 * @创建时间 2019/7/31
 * @描述 冒泡排序
 */
public class MaoPaoSort {

    public static void main(String[] args) {

        int[] number = {1,34,12,24,22,55,33,11,8,7,3,2,5,6,23};
        sort(number);

        for (int i = 0; i < number.length; i ++){
            System.out.print(number[i]+",");
        }

    }

    /**
     * 排序
     * @param number
     */
    public static void sort(int[] number){
        for( int i = 0;i < number.length; i ++ ){
            for (int j = i + 1; j < number.length; j++){
                if(number[i] > number[j]){
                    int temp = number[i];
                    number[i] = number[j];
                    number[j] = temp;
                }
            }
        }
    }

}
