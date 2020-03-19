package com.yanwhisper.www.demo;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：与CyclicBarrier类似，不同的是，CountDownLatch不可以重用
 * @author little whisper
 * @date 2020/3/19
 */
public class CountDownLatchDemo {

    public static void main(String args[]) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            new Thread(new TaskThread(cdl, i * 1000 + 2000)).start();
        }
        System.out.println("等待所有线程完成...");
        // 等待所有线程执行完毕
        cdl.await();
        System.out.println("所有线程已完成.");
    }


    static class TaskThread extends Thread {

        private CountDownLatch cdl;

        private long sleepTimeMilis;

        public TaskThread(CountDownLatch cdl, long sleepTimeMilis) {
            this.cdl = cdl;
            this.sleepTimeMilis = sleepTimeMilis;
        }

        @Override
        public void run() {
            try {
                System.out.println("id=" + Thread.currentThread().getId());
                Thread.sleep(sleepTimeMilis);
                System.out.println(Thread.currentThread().getId() + "执行完毕.");
                // 子线程执行完毕，计数减一
                cdl.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
