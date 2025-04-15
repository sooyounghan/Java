package was.v3;

import was.v2.HttpServerV2;

import java.io.IOException;

public class ServerMainV3 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        // HttpServerV1 server = new HttpServerV1(PORT);
        HttpServerV3 server = new HttpServerV3(PORT);
        server.start();
    }
}
