-----
### NullPointerException
-----
1. 만약 참조값 없이 객체에 접근할 경우, NullPointerException 이라는 예외가 발생
   - NullPointerException은 이름 그대로 null을 가리키다(Pointer)인데, 이 때 발생하는 예외(Exception)
   - null은 없다는 뜻이므로 결국 주소가 없는 곳을 찾아갈 때 발생하는 예외

2. 객체를 참조할 때는 .(dot)을 사용
   - 이렇게 하면 참조값을 사용해서 해당 객체를 찾아갈 수 있음
   - 그런데 참조값이 null이라면 값이 없다는 뜻이므로, 찾아갈 수 있는 객체(인스턴스)가 없음
   - NullPointerException은 이처럼 null 에 .(dot)을 찍었을 때 발생

3. NullMain2
```java
package ref;

public class NullMain2 {
    public static void main(String[] args) {
        Data data = null;
        data.value = 10; // NullPointerException 예외 발생
        System.out.println("data = " + data.value);
    }
}
```
  - data 참조형 변수에는 null값이 들어가 있음
```java
data.value = 10
null.value = 10 // data에는 null 값이 들어있음
```
   - 결과적으로 null 값은 참조할 주소가 존재하지 않는다는 뜻
   - 따라서 참조할 객체 인스턴스가 존재하지 않으므로 다음과 같이 java.lang.NullPointerException이 발생하고, 프로그램이 종료
   - 참고로 예외가 발생했기 때문에 그 다음 로직은 수행되지 않음
   - 실행 결과
```
Exception in thread "main" java.lang.NullPointerException: Cannot assign field "value" because "data" is null
  at ref.NullMain2.main(NullMain2.java:6)
```
4. 멤버 변수와 Null
   - Data
```java
package ref;

public class Data {
     int value;
}
```
  - BigData 클래스 : Data data, int count 두 변수를 가짐
```java
package ref;

public class BigData {
    Data data;
    int count;
}
```
  - NullMain3
```java
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
```
  - 실행 결과
```
bigData.count = 0
bigData.data = null
Exception in thread "main" java.lang.NullPointerException: Cannot read field "value" because "bigData.data" is null
	at ref.NullMain3.main(NullMain3.java:11)
```
<div align="center">
<img src="https://github.com/user-attachments/assets/2d675ad6-bdb0-446f-84a7-059945817d73">
</div>

  - BigData를 생성하면 BigData의 인스턴스가 생성
  - 이 때 BigData 인스턴스의 멤버 변수에 초기화가 일어나는데, BigData의 data 멤버 변수는 참조형이므로 null로 초기화
  - count 멤버 변수는 숫자이므로 0으로 초기화

   - bigData.count를 출력하면 0이 출력됨
   - bigData.data를 출력하면 참조값인 null이 출력
     + 이 변수는 아직 아무것도 참조하고 있지 않음
     + bigData.data.value를 출력하면 data의 값이 null이므로 null에 .(dot)을 찍게 되고, 따라서 참조할 곳이 없으므로 NullPointerException 예외가 발생
   - 예외 발생 과정
```java
bigData.data.value

x001.data.value // bigData는 x001 참조값을 가짐
null.value // x001.data는 null 값을 가짐
NullPointerException // null 값에 .(dot)을 찍으면 예외가 발생
```

   - 이 문제를 해결하려면 Data 인스턴스를 만들고 BigData.data 멤버 변수에 참조값을 할당
```java
package ref;

public class NullMain4 {
    public static void main(String[] args) {
        BigData bigData = new BigData();
        bigData.data = new Data();
        System.out.println("bigData.count = " + bigData.count);
        System.out.println("bigData.data = " + bigData.data);
        System.out.println("bigData.data = " + bigData.data.value);
    }
}
```
  - 실행 결과
```
bigData.count = 0
bigData.data = ref.Data@x002
bigData.data.value = 0
```
<div align="center">
<img src="https://github.com/user-attachments/assets/169992b8-25b8-40f4-aff1-1405d679d5b1">
</div>

  - 실행 과정
```
bigData.data.value

x001.data.value // bigData는 x001 참조값을 가짐

x002.value // x001.data는 x002 값을 가짐

0 // 최종 결과
```

  - 정리 : NullPointerException이 발생하면 null 값에 .(dot)을 찍었다고 생각하면 문제를 쉽게 찾을 수 있음
