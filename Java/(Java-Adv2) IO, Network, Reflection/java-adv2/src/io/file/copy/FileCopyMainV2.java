package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyMainV2 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");

        fis.transferTo(fos); // InputStream을 읽어서 OutputStream으로 전송

        fis.close();
        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("Time Taken = " + (endTime - startTime) + "ms");
    }
}
