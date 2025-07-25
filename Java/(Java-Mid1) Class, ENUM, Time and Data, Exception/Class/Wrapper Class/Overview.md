-----
### 래퍼 클래스 - 기본형의 한계 1
-----
1. 기본형의 한계
   - 자바는 객체 지향 언어이지만, 그런데 자바 안에 객체가 아닌 것이 있음 : int, double 같은 기본형(Primitive Type)
   - 기본형은 객체가 아니기 때문에 다음과 같은 한계가 존재
     + 객체가 아님 : 기본형 데이터는 객체가 아니기 때문에, 객체 지향 프로그래밍의 장점을 살릴 수 없음
       * 예를 들어 객체는 유용한 메서드를 제공할 수 있는데, 기본형은 객체가 아니므로 메서드를 제공할 수 없음
     + 추가로 객체 참조가 필요한 컬렉션 프레임워크를 사용할 수 없으므로, 제네릭도 사용할 수 없음
     + null 값을 가질 수 없음 : 기본형 데이터 타입은 null 값을 가질 수 없음 (때로는 데이터가 없음 이라는 상태를 나타내야 할 필요가 있는데, 기본형은 항상 값을 가지기 때문에 이런 표현을 할 수 없음)

2. MyIntegerMethodMain0 (/lang/wrapper)
   - 두 값을 비교하여 다음과 같은 결과를 출력하는 코드
   - 왼쪽의 값이 더 작음 : -1
   - 두 값이 같음 :  0
   - 왼쪽의 값이 더 큼 : 1
```java
package lang.wrapper;

public class MyIntegerMethodMain0 {
    public static void main(String[] args) {
        int value = 10;
        
        int i1 = compareTo(value, 5);
        int i2 = compareTo(value, 10);
        int i3 = compareTo(value, 20);

        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i3 = " + i3);
    }

    private static int compareTo(int value, int target) {
        if (value < target) {
            return -1;
        } else if (value > target) {
            return 1;
        } else {
            return 0;
        }
    }
}
```
   - 실행 결과
```
i1 = 1
i2 = 0
i3 = -1
```

   - 여기서는 value와 비교 대상 값을 compareTo()라는 외부 메서드를 사용해서 비교
   - 그런데 자기 자신인 value 와 다른 값을 연산하는 것이기 때문에 항상 자기 자신의 값인 value가 사용
   - 이런 경우 만약 value가 객체라면 value 객체 스스로 자기 자신의 값과 다른 값을 비교하는 메서드를 만드는 것이 더 유용할 것

3. 직접 만든 래퍼 클래스
   - int는 클래스가 아니지만, int 값을 가지고 클래스를 만들면 됨
   - 다음 코드는 마치 int를 클래스로 감싸서 만드는 것 처럼 보임
   - 특정 기본형을 감싸서(Wrap) 만드는 클래스 :  래퍼 클래스 (Wraaper Class)
   - MyInteger
```java
package lang.wrapper;

public class MyInteger {
    private final int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(int target) {
        if (value < target) {
            return -1;
        } else if (value > target) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value); // 숫자를 문자로 변경
    }
}
```
   - MyInteger는 int value라는 단순한 기본형 변수를 하나 가지고 있음
   - 그리고 이 기본형 변수를 편리하게 사용하도록 다양한 메서드를 제공
   - 앞에서 본 compareTo() 메서드를 클래스 내부로 캡슐화
   - 이 클래스는 불변으로 설계
   - MyInteger 클래스는 단순한 데이터 조각인 int를 내부에 품고, 메서드를 통해 다양한 기능을 추가
   - 덕분에 데이터 조각에 불과한 int를 MyInteger를 통해 객체로 다룰 수 있게 됨

   - MyIntegerMethodMain1
```java
package lang.wrapper;

public class MyIntegerMethodMain1 {
    public static void main(String[] args) {
        MyInteger myInteger = new MyInteger(10);

        int i1 = myInteger.compareTo(5);
        int i2 = myInteger.compareTo(10);
        int i3 = myInteger.compareTo(20);

        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i3 = " + i3);
    }
}
```
   - 실행 결과
```
i1 = 1
i2 = 0
i3 = -1
```
   - myInteger.compareTo()는 자기 자신의 값을 외부의 값과 비교
   - MyInteger는 객체이므로 자신이 가진 메서드를 편리하게 호출할 수 있음
   - 참고로 int는 기본형이기 때문에 스스로 메서드를 가질 수 없음

-----
### 래퍼 클래스 - 기본형의 한계 2
-----
1. 기본형과 null
   - 기본형은 항상 값을 가져야 하지만 때로는 데이터가 '없음'이라는 상태가 필요할 수 있음
   - MyIntegerNullMain0
```java
package lang.wrapper;

public class MyIntegerNullMain0 {
    public static void main(String[] args) {
        int[] intArr = {-1, 0, 1, 2, 3};

        System.out.println(findValue(intArr, -1)); // -1
        System.out.println(findValue(intArr, 0)); 
        System.out.println(findValue(intArr, 1)); 
        System.out.println(findValue(intArr, 100)); // -1
        
    }
    
    private static int findValue(int[] intArr, int target) {
        for (int value : intArr) {
            if(value == target) {
                return value;
            }
        }
        
        return -1;
    }
}
```
   - findValue()는 배열에 찾는 값이 있으면 해당 값을 반환하고, 찾는 값이 없으면 -1을 반환
   - findValue()는 결과로 int를 반환
     + int와 같은 기본형은 항상 값이 있어야 함
     + 여기서도 값을 반환할 때 값을 찾지 못하면 숫자 중에 하나를 반환해야 하는데 보통 -1 또는 0을 사용
   - 실행 결과
```
-1
0
1
-1
```
   - 실행 결과를 보면 입력값이 -1 일 때 -1을 반환
   - 그런데 배열에 없는 값인 100을 입력해도 같은 -1을 반환
   - 입력값이 -1 일 때를 생각해보면, 배열에 -1 값이 있어서 -1을 반환한 것인지, 아니면 -1 값이 없어서 -1을 반환한 것인지 명확하지 않음

2. 객체의 경우 데이터가 없다는 null 이라는 명확한 값이 존재
   - MyIntegerNullMain1
```java
package lang.wrapper;

public class MyIntegerNullMain1 {
    public static void main(String[] args) {
        MyInteger[] intArr = {new MyInteger(-1), new MyInteger(0), new MyInteger(1)};

        System.out.println(findValue(intArr, -1)); 
        System.out.println(findValue(intArr, 0));
        System.out.println(findValue(intArr, 1));
        System.out.println(findValue(intArr, 100));

    }

    private static MyInteger findValue(MyInteger[] intArr, int target) {
        for (MyInteger myInteger : intArr) {
            if(myInteger.getValue() == target) {
                return myInteger;
            }
        }

        return null;
    }
}
```
   - 실행 결과
```
-1
0
1
null
```
   - 앞서 만든 MyInteger 래퍼 클래스를 사용
   - 실행 결과를 보면 -1을 입력했을 때는 -1을 반환
   - 100을 입력했을 때는 값이 없다는 null을 반환

3. 기본형은 항상 값이 존재해야 함
   - 숫자의 경우 0, -1 같은 값이라도 항상 존재해야 함
   - 반면에 객체인 참조형은 값이 없다는 null을 사용할 수 있음
   - 물론 null 값을 반환하는 경우 잘못하면 NullPointerException이 발생할 수 있기 때문에 주의해서 사용해야 함
