package com.yanwhisper.www.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterGatterDemo {
    public static void main(String args[]) throws Exception {
        RandomAccessFile rcf = new RandomAccessFile("", "rw");
        FileChannel fc = rcf.getChannel();

        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);
        // 从channel依次读数据到header、body
        fc.read(new ByteBuffer[] {header, body});

        // 将header1、body1中的数据依次写入到channel
        ByteBuffer header1 = ByteBuffer.allocate(128);
        ByteBuffer body1 = ByteBuffer.allocate(1024);
        fc.write(new ByteBuffer[] {header1, body1});
    }
}
