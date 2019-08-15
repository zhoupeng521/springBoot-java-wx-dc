package com.tts.logdome.common.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 zp
 * @创建时间 2019/8/13
 * @描述 Condition--await与notify的例子
 */
public class AwaitSignal {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        Thread thread = new Thread(new Waiter());
        thread.start();
        Thread thread1 = new Thread(new Signaler());
        thread1.start();

    }

    static class Waiter implements Runnable{

        @Override
        public void run() {
            reentrantLock.lock();
            try {
                while (!flag){
                    System.out.println(Thread.currentThread().getName() + "当前条件不满足等待");
                    try{
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "接收到通知条件满足");
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    static class Signaler implements Runnable{

        @Override
        public void run() {
            reentrantLock.lock();
            try{
                flag = true;
                condition.signalAll();
            }finally {
                reentrantLock.unlock();
            }
        }
    }

}
