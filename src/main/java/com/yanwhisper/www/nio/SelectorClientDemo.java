package com.yanwhisper.www.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SelectorClientDemo {
    public static void main(String args[]) throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(8888));
        ByteBuffer bf = ByteBuffer.allocate(48);
        bf.put((byte)1);
        sc.write(bf);
    }
}
