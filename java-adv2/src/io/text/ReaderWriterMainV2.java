package io.text;

import java.io.*;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV2 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("Write String = " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        // writeStirng을 UTF_8로 인코딩하여 fos에서 읽어들여 osw가 처리
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);// OutputStreamWriter(OutputStream, Charset)
        osw.write(writeString);
        osw.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);

        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        StringBuilder content = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) { // isr.read() 반환 : char 반환 (문자 반환)
            content.append((char) ch); // ch는 int형 (ASCII) -> char형으로 Casting 필요
        }

        System.out.println("Read String = " + content);
    }
}
