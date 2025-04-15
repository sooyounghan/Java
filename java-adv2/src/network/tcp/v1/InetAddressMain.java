package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");// 호스트 이름으로 해당 IP 주소 획득
        System.out.println("localhost = " + localhost);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println("google = " + google);
    }
}
