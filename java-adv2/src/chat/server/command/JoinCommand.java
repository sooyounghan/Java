package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;

public class JoinCommand implements Command {
    private final SessionManager sessionManager;

    public JoinCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException { // args는 파싱된 결과
        String username = args[1]; // username
        session.setUsername(username); // 클라이언트의 이름을 session에 등록
        sessionManager.sendAll(username + "님이 입장했습니다!"); // 전체 세션과 클라이언트에게 전달
    }
}
