package com.tts.logdome.common.thread;

/**
 * @创建人 zp
 * @创建时间 2019/8/7
 * @描述 Volatile实现
 */
public class VolatileTest {

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                new VolatileExample().writer();
            }
        });


        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                new VolatileExample().reader();
            }
        });

        threadA.start();
        threadB.start();

    }

    static class VolatileExample {
        private int a = 0;
        //private volatile boolean flag = false;
        public void writer(){
            a = 1;          //1
            flag = true;   //2
            System.out.println(flag);
        }
        public void reader(){
            if(flag){      //3
                int i = a; //4
                System.out.println("I = "+ i);
            }
        }
    }
}
