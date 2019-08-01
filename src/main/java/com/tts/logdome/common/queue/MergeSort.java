package com.tts.logdome.common.queue;

/**
 * @创建人 zp
 * @创建时间 2019/8/1
 * @描述 归并排序
 */
public class MergeSort {

    public static int[] mergeSort(int[] nums, int l, int h) {
        if (l == h)
            return new int[] { nums[l] };

        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(nums, l, mid); //左有序数组
        int[] rightArr = mergeSort(nums, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        return newNum;
    }

    public static void main(String[] args) {

        int[] number = {34,12,24,22,55,33,1,11,8,7,3,2,5,6,23};
        number = mergeSort(number,0,number.length-1);

        for (int i = 0; i < number.length; i ++){
            System.out.print(number[i]+",");
        }

    }

}
