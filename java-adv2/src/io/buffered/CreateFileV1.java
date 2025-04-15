package io.buffered;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileV1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            fos.write(1); // 1 byte 씩 작성
        }

        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File Created : " + BufferedConst.FILE_NAME);
        System.out.println("File Size : " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("File Taken = " + (endTime - startTime) + "ms");
    }
}
