package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ReadTextFileV2 {
    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";

        System.out.println("=== Write String ===");
        System.out.println("writeString = " + writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8);

        // 파일에서 읽기
        System.out.println("=== Read String ===");

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8); // Files.readAllString(Path, Charset)
        // 한줄씩 접근 : Files.lines(Path, Charset) -> Stream<String> -> forEach
        // Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8);
        // lineStream.forEach(line -> System.out.println(line));

        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }
    }
}
