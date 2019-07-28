package com.tts.logdome.common.queue;

/**
 * 二分直接插入排序
 */
public class BinaryInsertSort {

    public static void main(String[] args) {

        int[] number = {1,34,12,24,22,55,33,11,8,7,3,2,5,6};
        sort(number);

        System.out.println("排序之后：");
        for ( int i = 0;i < number.length;i ++ ){
            System.out.print(number[i]+",");
        }

    }


    /**
     * 排序方法
     * @param number
     */
    public static void sort(int[] number){

        for(int i = 0; i < number.length; i++){

            int temp = number[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            int j ;
            while (left <= right){
                mid = (left + right)/2;
                if(temp < number[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            for (j = i - 1;j >= left; j --){
                number[j + 1] = number[j];
            }
            if(left != i){
                number[left] = temp;
            }
        }

    }

}
