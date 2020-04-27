package com.yanwhisper.www.demo;


/**
 * 三个线程依次打印 A B C，可以扩展到n个线程
 * @author little whisper
 * @date 2020/4/27
 */
public class ThreadDemo02 {
    private volatile static int num = 1;
    private static Object object = new Object();
    public static void main(String []args) {
        new Thread(() -> {
            try {
                for(int i = 0; i < 5; i++) {
                    synchronized (object) {
                        if (num != 1) {
                            // 设置wait时间，让程序结束
                            object.wait(2000);
                        }
                        System.out.println("A");
                        num = 2;
                        object.notifyAll();
                        object.wait(2000);
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
                        if (num != 2) {
                            object.wait(2000);
                        }
                        System.out.println("B");
                        num = 3;
                        object.notifyAll();
                        object.wait(2000);
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
                        if (num != 3) {
                            object.wait(2000);
                        }
                        System.out.println("C");
                        num = 1;
                        object.notifyAll();
                        object.wait(2000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
