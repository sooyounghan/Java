package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriteHandler implements Runnable {
    private static String DELIMITER = "|"; // Delimiter : | 구분자

    private final DataOutputStream output;
    private final Client client;

    private boolean closed = false; // 닫힘 여부 확인

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            String username = inputUsername(scanner);

            // 연결 후, 입력받은 사용자 이름을 서버에 전송
            output.writeUTF("/join" + DELIMITER + username);

            // 채팅 입력
            while (true) {
                String toSend = scanner.nextLine(); // Blocking (System.in을 Close하면, 예외 발생 -> NoSuchElementException)

                // 아무런 문자를 입력하지 않으면, 다시 입력하도록 설정
                if(toSend.isEmpty()) {
                    continue;
                }

                // /exit 명령어 입력 시, 서버에 전송 후 반복문 탈출 (입력 종료)
                if(toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                // "/"로 시작하면 명령어, 나머지는 일반 메세지로 구분
                if(toSend.startsWith("/")) {
                    // 예) "/users", "/change|{name}" ... : 명령어는 서버로 그대로 보냄
                    output.writeUTF(toSend);
                } else {
                    // hello, hi 등 전체에게 보내는 메세지
                    output.writeUTF("/message" + DELIMITER + toSend); // /message|hello
                }


            }
        } catch (IOException | NoSuchElementException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    private static String inputUsername(Scanner scanner) {
        // 사용자 이름 입력
        System.out.println("이름을 입력하세요. : ");
        String username;

        do {
            username = scanner.nextLine();
        } while (username.isEmpty());
        return username;
    }

    public synchronized void close() {
        if(closed) {
            return;
        }

        try {
            System.in.close(); // Scanner 입력 중지 (사용자의 입력을 닫음) (Scanner를 닫아서 종료 불가)
        } catch (IOException e) {
            log(e);
        }

        closed = true;
        log("WriteHandler 종료");
    }
}
