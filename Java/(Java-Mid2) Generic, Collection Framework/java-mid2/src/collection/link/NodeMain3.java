package collection.link;

public class NodeMain3 {
    public static void main(String[] args) {
        // 노드 생성하고 연결 : A -> B -> C
        Node first = new Node("A");
        first.next = new Node("B");
        first.next.next = new Node("C");

        System.out.println(first);

        // 모든 노드 탐색하기
        System.out.println("== 모든 노드 탐색하기 ==");
        printAll(first);

        // 마지막 노드 조회하기
        System.out.println("== 마지막 노드 조회하기 ==");
        Node lastNode = getLastNode(first);
        System.out.println("lastNode = " + lastNode.item);

        // 특정 Index의 노드 조회하기
        System.out.println("== 특정 Index의 노드 조회하기 ==");
        int index = 2;
        Node indexNode = getNode(first, index);
        System.out.println("indexNode = " + indexNode.item);

        // 데이터 추가하기
        System.out.println("== 데이터 추가하기 ==");
        add(first, "D");
        System.out.println(first);
        add(first, "E");
        System.out.println(first);
        add(first, "F");
        System.out.println(first);
    }

    private static void printAll(Node node) {
        Node x = node;

        while(x != null) {
            System.out.println(x.item);
            x = x.next;
        }
    }

    private static Node getLastNode(Node node) {
        Node x = node;

        while(x.next != null) { // 마지막 노드에 접근하면 반복문 탈출
            x = x.next;
        }

        return x;
    }

    private static Node getNode(Node node, int index) {
        Node x = node;

        for(int i = 0; i < index; i++) { // index - 1까지 이동하면,
            x = x.next; // x에는 결국 index 노드
        }

        return x;
    }

    private static void add(Node node, Object param) {
        Node lastNode = getLastNode(node);
        lastNode.next = new Node(param);
    }
}
