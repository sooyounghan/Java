package collection.set;

public class JavaHashCodeMain {
    public static void main(String[] args) {
        // Object의 기본 hashCode : 객체의 참조값 기반으로 생성
        Object obj1 = new Object();
        Object obj2 = new Object();

        System.out.println("obj1.hashCode() = " + obj1.hashCode());
        System.out.println("obj2.hashCode() = " + obj2.hashCode());

        // 각 클래스마다 hashCode는 이미 오버라이딩 되어 있음
        Integer i = 10;
        String strA = "A";
        String strAB = "AB";

        System.out.println("10.hashCode() = " + i.hashCode());
        System.out.println("strA.hashCode() = " + strA.hashCode());
        System.out.println("strAB.hashCode() = " + strAB.hashCode());

        // hashCode의 값은 마이너스 값이 될 수 있음 : 정수이므로
        System.out.println("-1.hashCode() = " + (Integer.valueOf(-1)).hashCode());

        // 인스턴스는 다르지만, equals는 같음 (eqauls를 id가 일치하면 동일하도록 재정의)
        Member member1 = new Member("idA");
        Member member2 = new Member("idA");

        // equals, hashCode를 오버라이딩 하지 않은 경우와, 한 경우 비교
        System.out.println("(member1 == member2) = " + (member1 == member2)); // false
        System.out.println("member1.equals(member2) = " + member1.equals(member2)); // true

        System.out.println("member1.hashCode() = " + member1.hashCode());
        System.out.println("member2.hashCode() = " + member2.hashCode());

    }
}
