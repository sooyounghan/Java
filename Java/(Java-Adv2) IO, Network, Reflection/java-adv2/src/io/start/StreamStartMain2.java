package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");// 자바를 기준으로 밖으로 보내 파일을 만드는 스트림 (파일이 없으면 생성, 파일이 존재하면 지우고, 다시 생성)

        fos.write(65); // byte 값 65 write
        fos.write(66); // byte 값 66 write
        fos.write(67); // byte 값 67 write

        fos.close(); // 외부와의 자원 닫기

        FileInputStream fis = new FileInputStream("temp/hello.dat");// 자바를 기준으로 외부에서 불러와 파일 읽기

        int data;

        while ((data = fis.read()) != -1) { // 파일의 끝 (EOF, -1)이 아니면,
            System.out.println(data); // 값 출력
        }

        fis.close(); // 자원 닫기
    }
}
