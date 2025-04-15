package chat.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;

public class SessionManager {
    private List<Session> sessions = new ArrayList<>(); // 세션 객체를 담을 ArrayList

    public synchronized void add(Session session) { // 세션 추가 (동기화 필요)
        sessions.add(session);
    }

    public synchronized void remove(Session session) { // 세션 제거 (동기화 필요)
        sessions.remove(session);
    }

    // 동기화 필요
    public synchronized void closeAll() { // 세션 모두 정리 (서버 종료되면, 모든 세션 종료) > 즉, 모든 세션이 사용하는 자원(Socket, InputStream, OutputStream 정리
        for (Session session : sessions) {
            session.close();
        }

        sessions.clear();
    }

    // 전체에게 메세지 전달
    public void sendAll(String message) {
        for (Session session : sessions) { // 각 세션에 대해
            try {
                session.send(message); // 메세지 전달
            } catch (IOException e) {
                log(e);
            }
        }
    }

    // 전체 이용자 이름
    public synchronized List<String> getAllUsername() {
        List<String> usernames = new ArrayList<>();

        for (Session session : sessions) {
            if(session.getUsername() != null) {
                usernames.add(session.getUsername()); // session에 저장된 사용자 이름 추가
            }
        }

        return usernames;
    }
}
