    package network.tcp.v2;

    import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.net.ServerSocket;
    import java.net.Socket;

    import static util.MyLogger.log;

    public class ServerV2 {
        private static final int PORT = 12345;

        public static void main(String[] args) throws IOException {
            log(" 서버 시작"); // MyLogger Static Import

            ServerSocket serverSocket = new ServerSocket(PORT); // 서버 생성 (PORT 개방)

            log("서버 소켓 시작 - Listening Port: " + PORT);

            Socket socket = serverSocket.accept(); // PORT를 열어둔 뒤, 이 PORT에 클라이언트가 접속하게 되면, 이를 통해 Socket 생성하여 통신하는데, 그 때까지 기다림

            log("소켓 연결: " + socket);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF(); // 서버 입장에서는 클라이언트의 요청 메세지를 받음
                log("client -> server: "  + received);

                // 만약, 받은 메세지가 exit라면 종료
                if(received.equals("exit")) {
                    break;
                }

                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }

            // 자원 정리
            log("연결 종료: " + socket);
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
        }
    }
