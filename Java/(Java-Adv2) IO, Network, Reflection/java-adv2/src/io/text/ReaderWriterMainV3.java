package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("Write String = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8); // FileWriter(File_name, Charset)
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fs = new FileReader(FILE_NAME, UTF_8);

        int ch;
        while((ch = fs.read()) != -1) {
            content.append((char)ch);
        }
        fs.close();

        String string = content.toString();
        System.out.println("Read String = " + string);
    }
}
