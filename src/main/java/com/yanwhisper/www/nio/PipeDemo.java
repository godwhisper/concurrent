package com.yanwhisper.www.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeDemo {
    public static void main(String args[]) throws Exception {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String writeData = "new string to write sink...";
        ByteBuffer bf1 = ByteBuffer.allocate(48);
        bf1.clear();
        bf1.put(writeData.getBytes());
        bf1.flip();
        while (bf1.hasRemaining()) {
            sinkChannel.write(bf1);
        }

        System.out.println(new String(bf1.array()));

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer bf2 = ByteBuffer.allocate(48);
        sourceChannel.read(bf2);
        bf2.flip();
        while (bf2.hasRemaining()) {
            System.out.println(bf2.get());
        }
    }
}
