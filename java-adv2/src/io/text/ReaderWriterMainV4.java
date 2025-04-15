package io.text;

import java.io.*;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV4 {
    public static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("=== Write String ===");
        System.out.println("Write String = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, UTF_8); // FileWriter(File_name, Charset)
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);// BufferedWriter(FileWriter, Buffer_size)
        bw.write(writeString);
        bw.close();

        // 파일에서 읽기
        StringBuilder content = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;

        while((line = br.readLine()) != null) { // 파일의 끝에 도달하면 null 반환
            content.append(line).append("\n"); // 라인 단위로 받으면 개행 문자 제거되므로 추가
        }
        br.close();

        System.out.println("=== Read String ===");
        System.out.println("Read String = " + content.toString());
    }
}
