package com.tts.logdome.common.queue;

/**
 * @创建人 zp
 * @创建时间 2019/7/25
 * @描述 直接插入排序算法
 */
public class DirectInsertSort {

    public static void main(String[] args) {

        int[] number = new int[]{1,23,2,45,11,55,12,24,13,15,14};

        for (int i = 1; i < number.length ; i ++){
            //插入
            int temp = number[i];
            int j;
            for (j = i - 1; j > 0 ; j --){
                if(number[j] > temp){
                    number[j + 1] = number[j];
                }else{
                    break;
                }
            }
            number[j + 1] = temp;
        }

        int gg = 1 / 2;
        System.out.println(gg);

        for ( int i = 0; i < number.length; i ++ ){
            System.out.print(number[i]+",");
        }

    }

}
