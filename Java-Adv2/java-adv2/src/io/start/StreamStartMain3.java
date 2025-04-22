package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input); // byte 배열
        fos.close(); // 외부와의 자원 닫기

        FileInputStream fis = new FileInputStream("temp/hello.dat");

        byte[] buffer = new byte[10];
        int readCount = fis.read(buffer, 0, 10);// 해당 파일을 buffer 배열에 읽음, offset (시작 위치) : 0, 길이 : 10 > 반환값 : 읽은 byte의 수

        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));

        fis.close(); // 자원 닫기
    }
}
