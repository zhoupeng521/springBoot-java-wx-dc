package com.tts.logdome.common.utils;

/**
 * Double 丢失精度比较
 */
public class MathUtil {

    private static final Double MONEY_REANG = 0.01;

    public static Boolean equals(Double d1 ,Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < MONEY_REANG){
            return true;
        }else{
            return false;
        }
    }

}
