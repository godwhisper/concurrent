package com.yanwhisper.www.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelDemo {
    public static void main(String args[]) throws Exception {
        RandomAccessFile rcf = new RandomAccessFile("C:\\Users\\laish\\Desktop\\s50345_player_2020-02-22.log", "rw");
        // 获取和该文件关联的通道
        FileChannel fileChannel = rcf.getChannel();
        // 申请一个新的ByteBuffer
        ByteBuffer bf = ByteBuffer.allocate(48);
        // 从通道中读取文件到bf，返回读取的字节数
        int data = fileChannel.read(bf);
        while (data != -1) {
            System.out.println("Read " + data + "：");
            System.out.println(bf.capacity() + "-" + bf.position() + "-" + bf.limit());
            // 切换bf的读写模式
            bf.flip();
            System.out.println(bf.capacity() + "-" + bf.position() + "-" + bf.limit());
            while (bf.hasRemaining()) {
                System.out.print((char)bf.get());
            }
            // 清空bf
            bf.clear();
            System.out.println();
            data = fileChannel.read(bf);
        }
        rcf.close();
    }
}
