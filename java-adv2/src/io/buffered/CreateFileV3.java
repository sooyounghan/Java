package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);// BufferedOutputStream(OutputStream, BUFFER_SIZE)

        long startTime = System.currentTimeMillis();

        for(int i = 0; i < FILE_SIZE; i++) {
            bos.write(1); // 1 byte 씩 작성
        }

        bos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File Created : " + FILE_NAME);
        System.out.println("File Size : " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("File Taken = " + (endTime - startTime) + "ms");
    }
}
