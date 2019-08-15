package com.tts.logdome.common.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @创建人 zp
 * @创建时间 2019/8/8
 * @描述 final的使用
 */
public class FinalTest {

    static String as = "ewqweqeq";

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new ReentrantReadWriteLock();
        lock.lock();
        change();
        System.out.println(as);
        lock.unlock();
    }

    public static void change(){
        as = "dasdasd";
    }

}
