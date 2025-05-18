package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input); // byte 배열
        fos.close(); // 외부와의 자원 닫기

        FileInputStream fis = new FileInputStream("temp/hello.dat");

        byte[] readBytes = fis.readAllBytes();
        System.out.println(Arrays.toString(readBytes));

        fis.close(); // 자원 닫기
    }
}
