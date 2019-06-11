package com.tts.logdome.common.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 阻塞队列--例子
 * ArrayBlockingQueue
 *
 */
public class Basket {

    ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

    /**
     * 生产苹果
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {

        arrayBlockingQueue.put("an apple");

    }

    /**
     * 拿走
     */
    public void consume() throws InterruptedException {

        arrayBlockingQueue.take();

    }

    public int getAppleSum(){
       return arrayBlockingQueue.size();
    }

    /**
     * 测试方法
     */
    public static void testBasket(){

        final Basket basket = new Basket();

        class Producer implements Runnable{

            @Override
            public void run() {

                try {
                    while (true){
                        System.out.println("生产者准备苹果"+System.currentTimeMillis());
                        basket.produce();
                        System.out.println("生产者准备苹果完毕"+System.currentTimeMillis());
                        System.out.println("生产了"+basket.getAppleSum()+"个苹果");
                        // 休眠300ms
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        class Consumer implements Runnable{

            @Override
            public void run() {

                try {
                    while (true){
                        System.out.println("消费者准备消费苹果"+System.currentTimeMillis());
                        basket.consume();
                        System.out.println("消费者准备消费苹果完毕"+System.currentTimeMillis());
                        System.out.println("消费完还剩下"+basket.getAppleSum()+"个苹果");
                        // 休眠300ms
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Producer());
        executorService.submit(new Consumer());
        try{
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //停止所有任务
        executorService.shutdownNow();
    }

    public static void main(String[] args) {
        testBasket();
    }

}
