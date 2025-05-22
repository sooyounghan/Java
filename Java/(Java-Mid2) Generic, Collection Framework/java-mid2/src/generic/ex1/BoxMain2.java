package generic.ex1;

public class BoxMain2 {
    public static void main(String[] args) {
        ObjectBox integerBox = new ObjectBox();
        integerBox.set(10);
        Integer integer = (Integer) integerBox.get(); // Object -> Integer Casting
        System.out.println("integer = " + integer);

        ObjectBox stringBox = new ObjectBox();
        stringBox.set("hello");
        String str = (String) stringBox.get(); // Object -> String Casting
        System.out.println("str = " + str);

        // 잘못된 타입의 인수 전달 시
        integerBox.set("문자100");
        Integer result = (Integer) integerBox.get(); // String -> Integer Casting Error
        System.out.println("result = " + result);
    }
}
