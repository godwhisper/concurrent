package com.yanwhisper.www.demo;

/**
 * 两个线程依次打印A B
 * @author little whisper
 * @date 2020/4/27
 */
public class ThreadDemo {
    private static Object object = new Object();
    public static void main(String []args) {
        new Thread(() -> {
            try {
                for(int i = 0; i < 5; i++) {
                    synchronized (object) {
                        System.out.println("A");
                        object.notify();
                        object.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for(int i = 0; i < 5; i++) {
                    synchronized (object) {
                        System.out.println("B");
                        object.notify();
                        object.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
