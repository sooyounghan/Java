-----
### 문제와 풀이
-----
1. 문제 1 : 구매한 자동차 수
   - 다음 코드를 참고해서 생성한 차량 수를 출력하는 프로그램을 작성
   - Car 클래스를 작성
```java
package static2.ex;

public class Car {
    private String name;
    private static int count;

    public Car(String name) {
        System.out.println("차량 구입, 이름 : " + name);
        this.name = name;
        count++;
    }

    public static void showTotalCars() {
        System.out.println("구매한 차량 수 : " + count);
    }
}
```

   - CarMain (/static2/ex)
```java
package static2.ex;

public class CarMain {
    public static void main(String[] args) {
        Car car1 = new Car("K3");
        Car car2 = new Car("G80");
        Car car3 = new Car("Model Y");

        Car.showTotalCars(); // 구매한 차량 수를 출력하는 static 메서드
    }
}
```

  - 실행 결과
```
차량 구입, 이름 : K3
차량 구입, 이름 : G80
차량 구입, 이름 : Model Y
구매한 차량 수 : 3
```

2. 문제 2 : 수학 유틸리티 클래스
  - 다음 기능을 제공하는 배열용 수학 유틸리티 클래스(MathArrayUtils)를 생성
    + sum(int[] array) : 배열의 모든 요소를 더하여 합계를 반환
    + average(int[] array) : 배열의 모든 요소의 평균값을 계산
    + min(int[] array) : 배열에서 최소값을 찾음
    + max(int[] array) : 배열에서 최대값을 찾음

  - 요구사항
    + MathArrayUtils은 객체를 생성하지 않고 사용
    + 누군가 실수로 MathArrayUtils 의 인스턴스를 생성하지 못하게 방지
    + 실행 코드에 static import 를 사용해도 무방

  - MathArrayUtils
```java
package static2.ex;

public class MathArrayUtils {
    public static int sum(int[] values) {
        int total = 0;

        for (int value : values) {
            total += value;
        }

        return total;
    }

    public static double average(int[] values) {
        return (double) sum(values) / values.length;
    }

    public static int min(int[] values) {
        int minValue = values[0];

        for (int value : values) {
            if(value < minValue) {
                minValue = value;
            }
        }

        return minValue;
    }

    public static int max(int[] values) {
        int maxValue = values[0];

        for (int value : values) {
            if(value > maxValue) {
                maxValue = value;
            }
        }

        return maxValue;
    }
}
```

  - 실행 코드 : MathArrayUtilsMain
```java
package static2.ex;

public class MathArrayUtilsMain {
    public static void main(String[] args) {
        int[] values = { 1, 2, 3, 4, 5 };

        System.out.println("sum = " + MathArrayUtils.sum(values));
        System.out.println("average = " + MathArrayUtils.average(values));
        System.out.println("min = " + MathArrayUtils.min(values));
        System.out.println("max = " + MathArrayUtils.max(values));
    }
}
```

  - 실행 결과
```
sum = 15
average = 3.0
min = 1
max = 5
```

