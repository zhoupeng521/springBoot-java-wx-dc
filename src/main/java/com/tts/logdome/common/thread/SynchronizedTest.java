package com.tts.logdome.common.thread;

/**
 * @创建人 zp
 * @创建时间 2019/8/7
 * @描述 同步锁--Synchronized
 */
public class SynchronizedTest {

    public  static int count = 0;

    public static void main(String[] args) {
        for (int i = 0 ; i < 10; i ++){
            new ThreadTest().start();
        }
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = " + count);
    }

    static class ThreadTest extends Thread{

        @Override
        public void run(){
            for (int i = 0 ; i < 1000 ; i ++){
                addCount();
            }
        }
    }

    /**
     * 加上synchronized同步锁
     */
    public static synchronized void addCount(){
        count ++;
    }

}
