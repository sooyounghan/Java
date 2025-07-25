-----
### 기본형과 참조형의 공유
-----
1. 자바의 데이터 타입을 가장 크게 보면 기본형(Primitive Type)과 참조형(Reference Type)으로 나눌 수 있음
   - 기본형 : 하나의 값을 여러 변수에서 절대로 공유하지 않음
   - 참조형: 하나의 객체를 참조값을 통해 여러 변수에서 공유할 수 있음

2. 기본형 예제 : 기본형은 하나의 값을 여러 변수에서 절대로 공유하지 않음
   - PrimitiveMain (/lang/immutable/address)
```java
package lang.immutable.address;

public class PrimitiveMain {
    public static void main(String[] args) {
        // 기본형은 절대로 같은 값을 공유하지 않음
        int a = 10;
        int b = a; // a -> b, 값 복사 후 대입
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        b = 20;
        System.out.println("20 -> b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```
   - 실행 결과
```
a = 10
b = 10
20 -> b
a = 10
b = 20
```
<div align="center">
<img src="https://github.com/user-attachments/assets/156eaa7d-0e75-49f7-bc2c-35af865ebc06">
</div>

   - 기본형 변수 a와 b는 절대로 하나의 값을 공유하지 않음
   - b = a라고 하면 자바는 항상 값을 복사해서 대입 : 이 경우 a에 있는 값 10을 복사해서 b에 전달
   - 결과적으로 a와 b는 둘다 10 이라는 똑같은 숫자의 값을 가짐
   - 하지만 a가 가지는 10과 b가 가지는 10은 복사된 완전히 다른 10 : 메모리 상에서도 a에 속하는 10과 b에 속하는 10이 각각 별도로 존재

<div align="center">
<img src="https://github.com/user-attachments/assets/5d9172ba-ecf1-4a2b-aaa8-26f0830b4b02">
</div>

   - b = 20이라고 하면 b의 값만 20으로 변경
   - a의 값은 10으로 그대로 유지
   - 기본형 변수는 하나의 값을 절대로 공유하지 않으므로, 따라서 값을 변경해도 변수 하나의 값만 변경됨 : 여기서는 변수 b의 값만 20으로 변경

3. 참조형 예제
   - Address
```java
package lang.immutable.address;

public class Address {
    private String value;

    public Address(String value) {
        this.value = value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Address{" +
                "value='" + value + '\'' +
                '}';
    }
}
```
   - 단순히 주소를 보관하는 객체
   - 객체의 값을 편하게 확인하기 위해 IDE의 도움을 받아서 toString()을 재정의

   - RefMain1_1
```java
package lang.immutable.address;

public class RefMain1_1 {
    public static void main(String[] args) {
        // 참조형 변수는 하나의 인스턴스 공유 가능
        Address a = new Address("서울");
        Address b = a;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        b.setValue("부산"); // b의 값을 부산으로 변경
        System.out.println("부산 -> b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
```
   - 처음에는 a, b 둘 다 서울이라는 주소를 가져야 한다고 가정 : 따라서 Address b = a 코드를 작성했고, 변수 a, b 둘다 서울이라는 주소를 가짐
   - 이후에 b의 주소를 부산으로 변경
   - 그런데 실행 결과를 보면 b뿐만 아니라 a의 주소도 함께 부산으로 변경되어 버림
   - 실행 결과
```
a = Address{value='서울'}
b = Address{value='서울'}
부산 -> b
a = Address{value='부산'}
b = Address{value='부산'}
```

   - 코드 분석
<div align="center">
<img src="https://github.com/user-attachments/assets/11610624-b20d-4d05-a43e-e8f39605e8c5">
</div>

   - 참조형 변수들은 같은 참조값을 통해 같은 인스턴스를 참조할 수 있음
   - b = a 라고 하면 a에 있는 참조값 x001을 복사해서 b에 전달
      + 자바에서 모든 값 대입은 변수가 가지고 있는 값을 복사해서 전달
      + 변수가 int 같은 숫자값을 가지고 있으면 숫자값을 복사해서 전달하고, 참조값을 가지고 있으면 참조값을 복사해서 전달
   - 참조값을 복사해서 전달하므로 결과적으로 a, b 는 같은 x001 인스턴스를 참조
   - 기본형 변수는 절대로 같은 값을 공유하지 않음
     + 예) a = 10 , b = 10 과 같이 같은 모양의 숫자 10 이라는 값을 가질 수는 있지만 같은 값을 공유하는 것은 아니며, 서로 다른 숫자 10 이 두 개 있는 것
   - 참조형 변수는 참조값을 통해 같은 객체(인스턴스)를 공유할 수 있음
