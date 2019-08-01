package com.tts.logdome.common.queue;

/**
 * @创建人 zp
 * @创建时间 2019/7/31
 * @描述 选择排序-堆排序
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] number = {1,34,12,24,22,55,33,11,8,7,3,2,5,6,23};

        heapSort(number);

        for (int i = 0; i < number.length; i ++){
            System.out.print(number[i]+",");
        }

    }


    /**
     * 堆排序
     * @param number
     */
    public static void heapSort(int[] number){

        //堆建
        for (int i = number.length / 2 - 1;i >= 0; i--){
            ajustHeap(number,i,number.length);
        }

        for (int i = number.length - 1;i > 0;i --){
            swarp(number,0,i);

            ajustHeap(number,0,i);
        }
    }

    /**
     * 整个堆排序最关键的地方
     * @param number
     * @param i
     * @param length
     */
    public static void ajustHeap(int[] number,int i,int length){
        //先把当前元素取出来，因为当前元素可能要一直移动
        int temp = number[i];
        for(int k = 2*i+1;k < length;k = 2*k+1){

            if(k + 1 < length && number[k] < number[k + 1]){
                k ++;
            }

            if(number[k] > temp){
                swarp(number,i,k);
                i = k;
            }else{
                break;
            }

        }

    }

    /**
     * 交换位置
     * @param number
     * @param a
     * @param b
     */
    public static void swarp(int[] number,int a,int b){
        int temp = number[a];
        number[a] = number[b];
        number[b] = temp;
    }

}
