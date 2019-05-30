package com.tts.logdome.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 主键ID生成器
 */
public class KeyGenerateUtils {

    /**
     * UUID生成
     * @return
     */
    public static String UUIDGenerate(){
        return UUID.randomUUID().toString();
    }

}
