package thread.bounded;

public interface BoundedQueue {
    void put(String data); // 데이터 생성
    String take(); // 데이터를 가져감
}
