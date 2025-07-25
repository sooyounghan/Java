package nested.nested.ex2;

public class Network {
    public void sendMessage(String test) {
        NetworkMessage networkMessage = new NetworkMessage(test);
        networkMessage.print();
    }

    private static class NetworkMessage {
        private String content;

        public NetworkMessage(String content) {
            this.content = content;
        }

        public void print() {
            System.out.println(content);
        }
    }
}
