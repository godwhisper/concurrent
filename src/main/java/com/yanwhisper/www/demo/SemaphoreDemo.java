package com.yanwhisper.www.demo;

import java.util.concurrent.Semaphore;

/**
 * 信号灯：可以设置允许访问共享资源的线程数
 * @author little whisper
 * @date 2020/3/10
 */
public class SemaphoreDemo {
    public static void main(String args[]) {
        Semaphore semaphore = new Semaphore(1);
        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("1111");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                // 释放
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("3333");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t3.start();
    }
}
