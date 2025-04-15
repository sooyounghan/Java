package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamEtcMain {
    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");
        PrintStream printStream = new PrintStream(fos);

        printStream.println("Hello Java!"); // 문자 -> 스트림
        printStream.println(10); // 숫자 -> 스트림
        printStream.println(true);
        printStream.printf("hello %s", "world");
        printStream.close();
    }
}
