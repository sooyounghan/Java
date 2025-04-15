package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

// 동시성 처리 필요
public class SessionManagerV6 {
    private List<SessionV6> sessions = new ArrayList<>(); // 세션 객체를 담을 ArrayList

    public synchronized void add(SessionV6 session) { // 세션 추가 (동기화 필요)
        sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) { // 세션 제거 (동기화 필요)
        sessions.remove(session);
    }

    // 동기화 필요
    public synchronized void closeAll() { // 세션 모두 정리 (서버 종료되면, 모든 세션 종료) > 즉, 모든 세션이 사용하는 자원(Socket, InputStream, OutputStream 정리
        for (SessionV6 session : sessions) {
            session.close();
        }

        sessions.clear();
    }
}
