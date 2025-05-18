package io.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NewFilesMain {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        // 1. exists(): 파일이나 디렉토리의 존재 여부를 확인
        System.out.println("File Exists = " + Files.exists(file));

        // 2. createFile(): 새 파일을 생성
        try {
            Files.createFile(file); // FileAlreadyExistsException 발생
            System.out.println("File Created");
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + "File already exists");
        }

        // 3. createDirectory(): 새 디렉토리를 생성
        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + "Directory already exists");
        }

        // 4. delete(): 파일이나 디렉토리를 삭제
        // Files.delete(file); // 파일 삭제
        // System.out.println("File Deleted");

        // 5. isRegularFile(): 일반 파일인지 확인
        System.out.println("Is Regular File = " + Files.isRegularFile(file));

        // 6. isDirectory(): 디렉토리인지 확인
        System.out.println("Is Directory = " + Files.isDirectory(directory));

        // 7. getFileName(): 파일이나 디렉토리의 이름을 반환
        System.out.println("File Name = " + file.getFileName());

        // 8. size(): 파일의 크기를 바이트 단위로 반환
        System.out.println("File Size = " + Files.size(file) + "bytes");

        // 9. move(): 파일의 이름을 변경하거나 이동
        Path newFile = Path.of("temp/newExample.txt");// = Paths.get()
        Files.move(file, newFile, StandardCopyOption.REPLACE_EXISTING); // 현재 있는 파일을 변경
        System.out.println("File Movde / Renamed");

        // 10. getLastModifiedTime(): 마지막으로 수정된 시간을 반환
        System.out.println("Las Modified = " + Files.getLastModifiedTime(newFile));

        // 추가: readAttributes(): 파일의 기본 속성들을 한 번에 읽기
        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);// 기본 파일 속성
        System.out.println("===== Attributes =====");
        System.out.println("Creation Time = " + attrs.creationTime());
        System.out.println("Is Directory = " + attrs.isDirectory());
        System.out.println("Is RegularFile = " + attrs.isRegularFile());
        System.out.println("Is Symbolic Link = " + attrs.isSymbolicLink());
    }
}
