package nested.nested.ex1;

public class Network {
    public void sendMessage(String test) {
        NetworkMessage networkMessage = new NetworkMessage(test);
        networkMessage.print();
    }
}
