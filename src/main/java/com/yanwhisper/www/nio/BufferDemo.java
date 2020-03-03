package com.yanwhisper.www.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
    public static void main(String args[]) throws Exception {
        // 写数据到buffer的两种方式：1.通过buffer的put方法；2.从channel写到buffer，通过channel的read
        // 主要变量：position、limit、mark、capacity，capacity表示bf的容量
        // 写模式下，position表示当前的写入位置，limit表示能写入的大小，等于capacity；读模式下，position表示当前读数据的位置，limit表示能读取的最大数据量，等于写模式下的position
        ByteBuffer bf = ByteBuffer.allocate(48);
        bf.put((byte)1);
        bf.put((byte)2);

        RandomAccessFile acf = new RandomAccessFile("", "rw");
        FileChannel fc = acf.getChannel();
        fc.read(bf);

        // 从buffer中读取数据的两种方式：1.通过bf的get方法；2.读取数据到channel，使用channel的write方法
        System.out.println(bf.get());

        fc.write(bf);

        // 将position置为0
        bf.rewind();

        // mark可以标记position
        bf.mark();
        // 将position置为mark
        bf.reset();
    }
}
