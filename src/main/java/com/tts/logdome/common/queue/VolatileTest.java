package com.tts.logdome.common.queue;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @创建人 zp
 * @创建时间 2019/6/17
 * @描述 volatile案例，只能保证可见性，不能保证原子性
 */
public class VolatileTest {

    //private static volatile int race = 0;

    //使用并发包Atomic
    private static AtomicInteger race = new AtomicInteger(0);

    /**
     * 1、方法加上synchronized修饰能解决原子性问题
     * 2、使用并发包也能解决原子性问题
     */
    public static void increase(){
        race.getAndIncrement();
    }

    /**
     * CAS操作
     * @return
     */
//    public static int increase(){
//        return Unsafe.getUnsafe().getAndAddInt(VolatileTest.class,race,1);
//    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0 ; i < THREADS_COUNT ; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000 ; j++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1){
            Thread.yield();
            System.out.println("race="+race);
        }
    }

}
