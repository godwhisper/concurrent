package com.yanwhisper.www.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String args[]) {
        Path path = Paths.get("C:\\software\\ideaIU-2018.2.4.exe");
        System.out.println(path.getFileName() + "-" + path.getFileSystem()+ "-" + path.getParent());
    }
}
