package network.tcp.autocloseable;

public class ResourceV2 implements AutoCloseable {
    private String name;

    public ResourceV2(String name) {
        this.name = name;
    }

    public void call() { // 호출
        System.out.println(name + " call");
    }

    public void callEx() throws CallException { // CallException
        System.out.println(name + " call Ex");
        throw new CallException(name + " ex");
    }

    @Override
    public void close() throws CloseException { // 자원 정리하면서 CloseException 발생
        System.out.println(name + " close");
        throw new CloseException(name + " ex");
    }
}
