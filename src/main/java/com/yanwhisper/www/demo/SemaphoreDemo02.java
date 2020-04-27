package com.yanwhisper.www.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author little whisper
 * @date 2020/4/27
 */
public class SemaphoreDemo02 {
    public static void main(String []args) {
        Semaphore s1= new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    s1.tryAcquire(2000, TimeUnit.SECONDS);
                    System.out.println("A");
                    s2.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    s2.tryAcquire(2000, TimeUnit.SECONDS);
                    System.out.println("B");
                    s3.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    s3.tryAcquire(2000, TimeUnit.SECONDS);
                    System.out.println("C");
                    s1.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
