package ref;

public class NullMain3 {
    public static void main(String[] args) {
        BigData bigData = new BigData();

        System.out.println("bigData.count = " + bigData.count);
        System.out.println("bigData.data = " + bigData.data);

        // NullPointeerException
        System.out.println("bigData.data = " + bigData.data.value);
    }
}
