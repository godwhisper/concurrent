package com.yanwhisper.www.demo;

import java.util.concurrent.Exchanger;

/**
 * 用于两个线程之间交换数据：如果多于两个以上的线程交换数据，那么结果是未知的
 * @author little whisper
 * @date 2020/4/26
 */
public class ExchangerDemo {
    public static void main(String []args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                String data = exchanger.exchange("one");
                System.out.println("线程1交换后的数据：" + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                String data = exchanger.exchange("two");
                System.out.println("线程2交换后的数据：" + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
