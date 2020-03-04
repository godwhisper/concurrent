package com.yanwhisper.www.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorServerDemo {
    public static void main(String args[]) throws Exception {
        // 创建一个选择器，并将channel注册到其上
        Selector selector = Selector.open();

        // 创建一个ServerSocketChannel，并绑定到8888端口
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8888));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT, 1);

        // 创建一个ServerSocketChannel，并绑定到8889端口
        ServerSocketChannel ssc2 = ServerSocketChannel.open();
        ssc2.socket().bind(new InetSocketAddress(8889));
        ssc2.configureBlocking(false);
        ssc2.register(selector, SelectionKey.OP_ACCEPT, 2);

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    System.out.println(selectionKey.attachment() + "：accept.");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                } else if (selectionKey.isConnectable()) {
                    System.out.println(selectionKey.attachment() + "：connect.");
                } else if (selectionKey.isReadable()) {
                    System.out.println(selectionKey.attachment() + "：read.");
                } else if (selectionKey.isWritable()) {
                    System.out.println(selectionKey.attachment() + "：write.");
                }
            }
        }
    }
}
