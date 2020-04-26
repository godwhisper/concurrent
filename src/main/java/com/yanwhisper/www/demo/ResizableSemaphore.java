package com.yanwhisper.www.demo;

import java.util.concurrent.Semaphore;

/**
 * @author little whisper
 * @date 2020/4/26
 */
public class ResizableSemaphore extends Semaphore {
    public ResizableSemaphore() {
        super(0);
    }

    public ResizableSemaphore(int permits) {
        super(permits);
    }

    @Override
    protected void reducePermits(int reduction) {
        super.reducePermits(reduction);
    }

    public static void main(String []args) {
        ResizableSemaphore rs = new ResizableSemaphore(5);
        rs.reducePermits(2);
        System.out.println(rs.availablePermits());
    }
}
