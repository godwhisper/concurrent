package com.yanwhisper.www.demo;

import java.util.concurrent.SynchronousQueue;

/**
 * 阻塞队列：put/take会阻塞；offer/poll不会阻塞，立即返回
 * @author little whisper
 * @date 2020/4/26
 */
public class SynchronousQueueDemo {
    public static void main(String []args) throws Exception {
        // 两种模式，公平模式（TransferQueue实现）和非公平模式（TransferStack实现）
        SynchronousQueue<Integer> sq = new SynchronousQueue<>(true);
        new Thread(() -> {
            try {
                int value = sq.take();
                System.out.println(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            int value = i + 1;
            new Thread(() -> {
                try {
                    sq.offer(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
