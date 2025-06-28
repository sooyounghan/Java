-----
### static 변수 3
-----
1. 용어 정리
```java
package static1;

public class Data3 {
    public String name;
    public static int count; // static

    public Data3(String name) {
        this.name = name;
        count++;
    }
}
```
   - 예제 코드에서 name, count는 둘다 멤버 변수
   - 멤버 변수(필드)는 static이 붙은 것과 아닌 것에 따라 다음과 같이 분류할 수 있음

2. 멤버 변수(필드)의 종류
   - 인스턴스 변수 : static이 붙지 않은 멤버 변수 (예) name)
     + static이 붙지 않은 멤버 변수는 인스턴스를 생성해야 사용할 수 있고, 인스턴스에 소속되어 있음
     + 따라서 인스턴스 변수라 함
     + 인스턴스 변수는 인스턴스를 만들 때 마다 새로 만들어짐

   - 클래스 변수 : static 이 붙은 멤버 변수 (예) count)
     + 클래스 변수, 정적 변수, static 변수 등으로 부름
     + static이 붙은 멤버 변수는 인스턴스와 무관하게 클래스에 바로 접근해서 사용할 수 있고, 클래스 자체에 소속되어 있음
     + 따라서 클래스 변수라 함

   - 클래스 변수는 자바 프로그램을 시작할 때 딱 1개가 만들어짐
   - 인스턴스와는 다르게 보통 여러곳에서 공유하는 목적으로 사용

3. 변수와 생명주기
   - 지역 변수(매개변수 포함) : 지역 변수는 스택 영역에 있는 스택 프레임 안에 보관
     + 메서드가 종료되면 스택 프레임도 제거 되는데 이때 해당 스택 프레임에 포함된 지역 변수도 함께 제거됨
     + 따라서 지역 변수는 생존 주기가 짧음

   - 인스턴스 변수 : 인스턴스에 있는 멤버 변수를 인스턴스 변수라 함
     + 인스턴스 변수는 힙 영역을 사용
     + 힙 영역은 GC(가비지 컬렉션)가 발생하기 전까지는 생존하기 때문에 보통 지역 변수보다 생존 주기가 김

   - 클래스 변수 : 클래스 변수는 메서드 영역의 static 영역에 보관되는 변수
     + 메서드 영역은 프로그램 전체에서 사용하는 공용 공간
     + 클래스 변수는 해당 클래스가 JVM에 로딩 되는 순간 생성
     + 그리고 JVM이 종료될 때 까지 생명주기가 이어짐
     + 따라서 가장 긴 생명주기를 가짐
       
4. static이 정적이라는 이유
   - 힙 영역에 생성되는 인스턴스 변수는 동적으로 생성되고, 제거
   - 반면에 static인 정적 변수는 거의 프로그램 실행 시점에 딱 만들어지고, 프로그램 종료 시점에 제거
   - 정적 변수는 이름 그대로 정적

5. 정적 변수 접근법
   - static 변수는 클래스를 통해 바로 접근할 수도 있고, 인스턴스를 통해서도 접근할 수 있음
   - DataCountMain3 마지막 코드에 다음 부분을 추가하고 실행
```java
package static1;

public class DataCountMain3 {
    public static void main(String[] args) {
        Data3 data1 = new Data3("A");
        System.out.println("A Count = " + Data3.count);

        Data3 data2 = new Data3("B");
        System.out.println("B Count = " + Data3.count);

        Data3 data3 = new Data3("C");
        System.out.println("C Count = " + Data3.count);

        // 추가 : 인스턴스를 통한 접근
        Data3 data4 = new Data3("D");
        System.out.println(data4.count);

        // 클래스를 통한 접근
        System.out.println(Data3.count);
    }
}
```
  - 실행 결과
```
A Count = 1
B Count = 2
C Count = 3
4
4
```
  - 둘의 차이는 없음 : 둘 다 결과적으로 정적 변수에 접근
  - 인스턴스를 통한 접근 : data4.count
     + 정적 변수의 경우 인스턴스를 통한 접근은 추천하지 않음
     + 왜냐하면 코드를 읽을 때 마치 인스턴스 변수에 접근하는 것 처럼 오해할 수 있기 때문임

  - 클래스를 통한 접근 : Data3.count
     + 정적 변수는 클래스에서 공용으로 관리하기 때문에 클래스를 통해서 접근하는 것이 더 명확함
     + 따라서 정적 변수에 접근할 때는 클래스를 통해서 접근
