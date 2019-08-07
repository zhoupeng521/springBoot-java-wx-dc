package com.tts.logdome.common.thread;

import java.util.concurrent.*;

/**
 * @创建人 zp
 * @创建时间 2019/8/6
 * @描述 创建线程的多种方式
 */
public class TreadCaseOne {

    private volatile static boolean on = false;

    public static void main(String[] args) {
        //方式一、Runable
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread的Runnable创建");
            }
        });

        threadA.start();
        //方式二、继承Thread
        Thread threadB = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread继承创建");
            }
        };

        threadB.start();
        //方式三、Callable
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "通过Callable实现创建线程";
            }
        });
        try {
            String s = future.get();
            System.out.println(s);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //方式三、FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "FutureTask封装，创建线程";
            }
        });
        try {
            futureTask.run();
            String s = futureTask.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //interrupt:中断操作
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //interrupt:中断操作
        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!on){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1");
                }
            }
        });

        threadC.start();
        threadD.start();
        threadC.interrupt();
        TreadCaseOne.on = true;//添加开关，中断线程
        threadD.interrupt();
        while (threadC.isInterrupted());
        System.out.println("threadC:"+threadC.isInterrupted());
        System.out.println("threadD:"+threadD.isInterrupted());
    }

}
