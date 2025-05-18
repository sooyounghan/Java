package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateFileV2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(BufferedConst.FILE_NAME);

        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BufferedConst.BUFFER_SIZE];
        int bufferIndex = 0;

        for(int i = 0; i < BufferedConst.FILE_SIZE; i++) {
            buffer[bufferIndex++] = 1;

            // 버퍼가 가득차면 쓰고, 버퍼를 지움
            if (bufferIndex == BufferedConst.BUFFER_SIZE) {
                fos.write(buffer);
                bufferIndex = 0;
            }
        }

        // 끝 부분에 오면 버퍼가 가득차지 않고, 남아 있을 수 있는데, 버퍼 남은 부분 쓰기
        if(bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File Created : " + BufferedConst.FILE_NAME);
        System.out.println("File Size : " + BufferedConst.FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("File Taken = " + (endTime - startTime) + "ms");
    }
}
