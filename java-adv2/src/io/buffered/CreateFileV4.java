package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[FILE_SIZE];

        for(int i = 0; i < FILE_SIZE; i++) {
            buffer[i] = 1; // 1 byte 씩 작성
        }

        fos.write(buffer);
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File Created : " + FILE_NAME);
        System.out.println("File Size : " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("File Taken = " + (endTime - startTime) + "ms");
    }
}
