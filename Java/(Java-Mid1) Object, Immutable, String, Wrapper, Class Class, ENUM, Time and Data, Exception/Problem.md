-----
### 문제와 풀이 1
-----
1. 문제 1 - parseInt()
  - 문제 설명 : 문자로 입력된 str1, str2 두 수의 합을 구할 것
  - WraaperTest1 (/lang/wrapper/test)
```java
package lang.wrapper.test;

public class WrapperTest1 {
    public static void main(String[] args) {
        String str1 = "10";
        String str2 = "20";

        int sum = Integer.parseInt(str1) + Integer.parseInt(str2);
        System.out.println("두수의 합 = " + sum);
    }
}
```
   - 실행 결과
```
두수의 합 = 30
```

2. 문제2 - parseDouble()
  - 문제 설명 : 배열에 입력된 모든 숫자의 합을 구할 것 (숫자는 double 형이 입력될 수 있음)
  - WraaperTest2
```java
package lang.wrapper.test;

public class WrapperTest2 {
    public static void main(String[] args) {
        String[] array = {"1.5", "2.5", "3.0"};

        double sum = 0.0;
        for (String s : array) {
            sum += Double.parseDouble(s);
        }

        System.out.println("sum = " + sum);
    }
}
```
   - 실행 결과
```
sum = 7.0
```

3. 문제3 - 박싱, 언박싱
  - 문제 설명
    + String str을 Integer로 변환해서 출력
    + Integer를 int로 변환해서 출력
    + int를 Integer로 변환해서 출력
    + 오토 박싱, 오토 언박싱을 사용하지 말고 직접 변환
  - WrapperTest3
```java
package lang.wrapper.test;

public class WrapperTest3 {
    public static void main(String[] args) {
        String str = "100";

        // String -> Integer
        Integer integer1 = Integer.valueOf(str);
        System.out.println("integer1 = " + integer1);
        
        // Integer -> int
        int intValue = integer1.intValue();
        System.out.println("intValue = " + intValue);
        
        // int -> Integer
        Integer integer2 = Integer.valueOf(intValue);
        System.out.println("integer2 = " + integer2);
    }
}
```
   - 실행 결과
```
integer1 = 100
intValue = 100
integer2 = 100
```

4. 문제4 - 오토 박싱, 오토 언박싱
  - 문제 설명
    + String str을 Integer로 변환해서 출력
    + Integer를 int로 변환해서 출력
    + int를 Integer로 변환해서 출력
    + 오토 박싱, 오토 언박싱을 사용해서 변환
  - WrapperTest4
```java
package lang.wrapper.test;

public class WrapperTest4 {
    public static void main(String[] args) {
        String str = "100";
        
        // String -> Integer
        Integer integer1 = Integer.valueOf(str);
        System.out.println("integer1 = " + integer1);

        // Integer -> int
        int intValue = integer1;
        System.out.println("intValue = " + intValue);

        // int -> Integer
        Integer integer2 = intValue;
        System.out.println("integer2 = " + integer2);

    }
}
```
   - 실행 결과
```
integer1 = 100
intValue = 100
integer2 = 100
```

-----
### 문제와 풀이2
-----
1. 문제 - 로또 번호 자동 생성기
   - 문제 설명 : 로또 번호를 자동으로 만들어주는 자동 생성기 
      + 로또 번호는 1 ~ 45 사이의 숫자를 6개 
      + 각 숫자는 중복되면 안 됨
      + 실행할 때 마다 결과가 달라야 함
   - 실행 결과 예시
```
로또 번호: 11 19 21 35 29 16
```

2. LottoGenerator (/lang/math/test)
```java
package lang.math;

import java.util.Random;

public class LottoGenerator {
    private final Random random = new Random();
    private int[] lottoNumbers;
    private int count;
    
    public int[] generate() {
        lottoNumbers = new int[6];
        count = 0;
        
        while(count < 6) {
            // 1부터 45까지 숫자 생성
            int number = random.nextInt(45) + 1;
            
            // 중복되지 않는 경우에만 배열 추가
            if (isUnique(number)) {
                lottoNumbers[count] = number;
                count++;
            }
        }
        
        return lottoNumbers;
    }
    
    // 이미 생성된 번호와 중복되는지 검사
    private boolean isUnique(int number) {
        for(int i = 0; i < count; i++) {
            if(lottoNumbers[i] == number) {
                return false;
            }
        }
        
        return true;
    }
}
```

3. LottoGeneratorMain
```java
package lang.math;

public class LottoGeneratorMain {
    public static void main(String[] args) {
        LottoGenerator generator = new LottoGenerator();
        int[] lottoNumbers = generator.generate();
        
        // 생성된 로또 번호 출력
        System.out.print("로또 번호 : ");
        for (int lottoNumber : lottoNumbers) {
            System.out.print(lottoNumber + " ");
        }
    }
}
```

4. 실행 결과
```
로또 번호 : 7 32 24 22 14 35 
```
