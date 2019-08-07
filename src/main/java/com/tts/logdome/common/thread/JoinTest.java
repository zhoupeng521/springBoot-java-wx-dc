package com.tts.logdome.common.thread;

/**
 * @创建人 zp
 * @创建时间 2019/8/6
 * @描述 并发编程--join的使用
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread previousThread  = Thread.currentThread();
        for (int i = 1; i <= 10; i ++){
            Thread joinThread = new JoinThread(previousThread);
            joinThread.start();
            previousThread = joinThread;
        }
    }

    static class JoinThread extends Thread{

        private Thread thread;

        public JoinThread(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run(){
            try {
                thread.join();
                System.out.println(thread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
