package com.yanwhisper.www.demo;

import java.util.concurrent.Semaphore;

/**
 * 信号量（Semaphore）实现两个线程依次打印A B
 * @author little whisper
 * @date 2020/4/27
 */
public class ThreadDemo03 {
    public static void main(String []args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        new Thread(() -> {
            try {
               for (int i = 0; i < 5; i++) {
                   s1.acquire();
                   System.out.println("A");
                   s2.release();
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    s2.acquire();
                    System.out.println("B");
                    s1.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
