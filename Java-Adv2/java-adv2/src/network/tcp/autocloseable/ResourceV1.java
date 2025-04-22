package network.tcp.autocloseable;

public class ResourceV1 {
    private String name;

    public ResourceV1(String name) {
        this.name = name;
    }

    public void call() { // 호출
        System.out.println(name + " call");
    }

    public void callEx() throws CallException { // CallException
        System.out.println(name + " call Ex");
        throw new CallException(name + " ex");
    }

    public void close() { // 자원 정리
        System.out.println(name + " close");
    }

    public void closeEx() throws CloseException { // CloseException
        System.out.println(name + " closeEx");
        throw new CloseException(name + " ex");
    }
}
