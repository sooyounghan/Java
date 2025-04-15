package chat.server;

import java.io.IOException;

public interface CommandManager {
    // 클라이언트가 명령어를 보내면, 이를 서버에서 실행
    void execute(String totalMessage, Session session) throws IOException;
}
