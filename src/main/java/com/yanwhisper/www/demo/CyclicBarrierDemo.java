package com.yanwhisper.www.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：让所有的线程都完成后才会进行下一步；可以重用所有线程完成后，调用reset方法
 * @author little whisper
 * @date 2020/3/19
 */
public class CyclicBarrierDemo {

    public static void main(String args[]) {
        Map<Long, Integer> ts = new ConcurrentHashMap<>();
        // 将线程数设置为5，并让最后一个完成的线程做合并计算
        CyclicBarrier cb = new CyclicBarrier(5, () -> {
            int sum = 0;
            for (int value : ts.values()) {
                sum = sum + value;
            }
            System.out.println("sum=" + sum);
        });
        TaskThread tt = new TaskThread(cb, ts);
        for (int i = 0; i < 5; i++) {
            new Thread(tt).start();
        }
        int sum = 0;
        for (int value : ts.values()) {
            sum = sum + value;
        }
    }

    static class TaskThread extends Thread {

        public TaskThread(CyclicBarrier cb, Map<Long, Integer> map) {
            this.cb = cb;
            this.map = map;
        }

        private CyclicBarrier cb;

        private Map<Long, Integer> map;

        @Override
        public void run() {
            try {
                int sum = 0;
                for (int i = 0; i < 100; i ++) {
                    sum = sum + i;
                }
                map.put(Thread.currentThread().getId(), sum);
                System.out.println(Thread.currentThread().getId() + ":" + sum);
                // 等待其他线程完成
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
