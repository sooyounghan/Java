package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

    private static final String DELIMITER = "\\|";

    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        if(totalMessage.startsWith("/join")) { // 사용자 입장 (/join|{username})
            String[] split = totalMessage.split(DELIMITER);
            String username = split[1]; // username
            session.setUsername(username); // 클라이언트의 이름을 session에 등록
            sessionManager.sendAll(username + "님이 입장했습니다!"); // 전체 세션과 클라이언트에게 전달
        } else if (totalMessage.startsWith("/message")) { // /message|{내용}
            String[] split = totalMessage.split(DELIMITER);
            String message = split[1]; // 내용
            sessionManager.sendAll("[" + session.getUsername() + "]" + message); // [name] message
        } else if (totalMessage.startsWith("/change")) { // /change|{name}
            String[] split = totalMessage.split(DELIMITER);
            String changeName = split[1]; // name
            sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "으로 이름을 변경했습니다!");
            session.setUsername(changeName); // 클라이언트 이름 유저 변경
        } else if (totalMessage.startsWith("/users")) { // 전체 사용자 이름
            List<String> usernames = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자: ").append(usernames.size()).append("\n");

            for (String username : usernames) {
                sb.append(" - ").append(username).append("\n");
            }
            session.send(sb.toString()); // 이를 요청한 사용자에게만 전달
        } else if(totalMessage.startsWith("/exit")) { // exit
            throw new IOException("exit");
        } else { // 그 외의 경우
            session.send("처리할 수 없는 명령어입니다. " + totalMessage);
        }
    }
}
