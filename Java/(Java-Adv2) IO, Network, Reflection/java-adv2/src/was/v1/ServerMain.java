package was.v1;

import java.io.IOException;

public class ServerMain {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV1 serverV1 = new HttpServerV1(PORT);
        serverV1.start();
    }
}
