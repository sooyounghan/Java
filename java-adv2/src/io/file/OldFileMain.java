package io.file;

import java.io.File;
import java.io.IOException;

public class OldFileMain {
    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt"); // File
        File directory = new File("temp/exampleDir");// Directory

        System.out.println("File Exists : " + file.exists()); // 파일이나 디렉토리 존재 여부 확인

        boolean create = file.createNewFile(); // 새 파일 생성
        System.out.println("File Created : " + create);

        boolean dirCreated = directory.mkdir(); // 새 디렉토리 생성
        System.out.println("Directory Created : " + dirCreated);

        /*
            boolean deleted = file.delete();// 파일이나 디렉토리 삭제
            System.out.println("deleted = " + deleted);
        */

        System.out.println("Is File = " + file.isFile()); // 파일 여부 확인
        System.out.println("Is Directory = " + directory.isDirectory()); // 디렉토리 여부 확인
        System.out.println("File Name = " + file.getName()); // 파일이나 디렉토리 이름 확인
        System.out.println("File Size = " + file.length()); // 파일 크기를 바이트 단위 반환

        // 파일 이름 변경 및 이동
        File newFile = new File("temp/newExample.txt");
        boolean renamed = file.renameTo(newFile); // 파일 이름 변경
        System.out.println("File Renamed : " + renamed);

        // 마지막 수정된 시간 반환
        long lastModified = newFile.lastModified();
        System.out.println("Last Modified : " + lastModified);
    }
}