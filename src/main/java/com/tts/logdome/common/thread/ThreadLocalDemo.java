package com.tts.logdome.common.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @创建人 zp
 * @创建时间 2019/8/15
 * @描述 ThreadLocal
 */
public class ThreadLocalDemo {

    private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 20;i ++){
            executorService.submit(new DateUtil("2019-08-15 12:35:" + i % 60));
        }
        sdf.remove();
        executorService.shutdown();
    }

    static class DateUtil implements Runnable{

        private String date;

        DateUtil(String date){
            this.date = date;
        }

        @Override
        public void run() {
            if(sdf.get() == null){
                sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:ss:mm"));
            }else{
                try{
                    Date date = sdf.get().parse(this.date);
                    System.out.println(date);
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        }

    }

}
