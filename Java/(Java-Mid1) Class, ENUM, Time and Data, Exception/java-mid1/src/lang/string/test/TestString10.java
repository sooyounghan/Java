package lang.string.test;

public class TestString10 {
    public static void main(String[] args) {
        String fruits = "apple,banana,mango";

        String[] splited = fruits.split(",");

        for (String split : splited) {
            System.out.println(split);
        }

        String joinedString = String.join("->", splited);
        System.out.println("joinedString = " + joinedString);
    }
}
