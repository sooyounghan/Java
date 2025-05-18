-----
### JVM의 메모리 구조
-----
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/45115aae-78cf-4c0c-8563-696337091c01">
</div>

        cv : 클래스 변수 / lv : 지역 변수 / iv : 인스턴스 변수
        
1. 메서드 영역(Method Area)
   - 프로그램 실행 중 어떤 클래스가 사용되면, JVM은 해당 클래스의 클래스 파일(*.class)을 읽어서 분석해 클래스에 대한 정보(클래스 데이터)를 저장
   - 클래스 변수(Class Variable)도 이 곳에 저장

2. 힙(Heap Area)
   - 인스턴스가 생성되는 공간, 즉 프로그 실행 중 생성되는 인스턴스는 모두 이 곳에 생성
   - 즉, 인스턴스 변수(Instance Variable)들이 생성되는 공간

3. 호출 스택 (Call Stack 또는 Execution Stack)
   - 메서드의 작업에 필요한 메모리 공간 제공
   - 메서드가 호출되면, 호출 스택에 호출된 메서드를 위한 메모리 할당
   - 메서드가 작업을 수행하는 동안 지역변수(매개변수 포함)들과 연산의 중간결과 등을 저장하는 데 사용
   - 메서드가 작업을 마치면 할당되었던 메모리 공간은 반환되어 비워짐

-----
### 호출 스택 (Call Stack)
-----
1. 각 메서드를 위한 메모리 상의 작업공간은 서로 구별
   - 첫 번째로 호출된 메서드를 위한 작업 공간이 호출 스택이 가장 맨 밑에 마련
   - 첫 번째 메서드 수행 중 다른 메서드를 호출하면, 이 메서드 바로 위에 두 번째 호출 메서드를 위한 공간 마련
   - 이 때, 첫 번째 메서드는 수행을 멈추고, 두 번째 메서드가 수행되기 시작
   - 두 번째로 호출된 메서드가 수행을 모두 마치면, 제공된 호출스택 메모리 공간은 반환되고, 첫 번째 메서드는 다시 수행을 지속
   - 첫 번째 메서드 또한 수행을 마치면, 제공된 메모리 공간이 호출 스택에서 제거되며, 호출 스택은 완전히 비워지게 됨

2. 호출 스택의 제일 상위에 위치하는 메서드는 현재 실행 중인 메서드
3. 나머지 메서드는 대기 상태에 존재
4. 호출 스택의 특징
   - 메서드가 호출되면 수행에 필요한 만큼 메모리를 스택에 할당
   - 메서드가 수행을 마치고나면, 사용한 메모리를 반환하고 스택에서 제거
   - 호출 스택 제일 위에 있는 메서드가 현재 실행 중인 메서드
   - 아래에 있는 메서드가 바로 위의 메서드를 호출한 메서드

5. 반환타입이 있는 메서드는 종료되면서 결과값을 자신이 호출한 메서드(Caller)에게 반환

6. 예제
```java
class CallStackTest {
	public static void main(String[] args) {
		firstMethod();
	}

	static void firstMethod() {
		secondMethod();
	}

	static void secondMethod() {
		System.out.println("secondMethod()");		
	}
}
```
  - main() 메서드는 firstMethod()를 호출
  - firstMethod()는 secondMethod()를 호출
  - 여기서, 객체를 생성하지 않고도 메서드를 호출 (이는 static method이기 때문임)

<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/c129b077-e4d5-4df9-856c-3891cda9c2c1">
</div>

  - 실행 흐름 정리

          1-2. JVM에 의해 main메서드가 호출되므로 프로그램이 시작. 이 때, 호출 스택에서는 main메서드를 위한 메모리 공간이 할당되고, main 메서드의 코드가 수행
          3. main 메서드에서는 firstMethod()를 호출한 상태. 아직 main 메서드는 끝나지 않았으므로 호출 스택에 대기 상태로 남으며, firstMethod() 수행 시작
          4. firstMethod()에서 다시 secondMethod() 호출. firstMethod()는 secondMethod()가 수행을 마칠 때 까지 대기 상태에 존재.
          5. secondMethod()가 println()을 호출. println()에 의해 secondMethod()가 화면에 출력
          6. println() 메서드 수행이 완료되어 호출 스택에서 사라지고, 자신을 호출한 secondMethod()로 되돌아감. 대기 중이던 secondMethod()는 println() 이후부터 수행 재개
          7. secondMethod()는 더 이상 수행할 코드가 없으므로 종료되고, 자신을 호출한 firstMethod()로 돌아감
          8. firstMethod()도 더 이상 수행할 코드가 없으므로 자신을 호출한 main 메서드로 돌아감
          9. main메서드 에서도 더 이상 수행할 코드가 없으므로 종료되고, 호출 스택은 완전히 비워지게 되므로 프로그램 종료

```java
class CallStackTest2 {
	public static void main(String[] args) {
		System.out.println("main(String[] args)이 시작되었음.");
		firstMethod();
		System.out.println("main(String[] args)이 끝났음.");
	}

	static void firstMethod() {
		System.out.println("firstMethod()이 시작되었음.");
		secondMethod();
		System.out.println("firstMethod()이 끝났음.");		
	}

	static void secondMethod() {
		System.out.println("secondMethod()이 시작되었음.");
		System.out.println("secondMethod()이 끝났음.");		
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/b335c94a-87a6-450b-8c9b-eb3800e78c6f">
</div>
