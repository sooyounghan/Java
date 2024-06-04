-----
### instanceof 연산자
-----
1. 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위해 사용하는 연산자
2. 주로 조건문에서 사용
3. instanceof의 왼쪽에는 참조변수를 오른쪽에는 타입(클래스명)이 피연산자로 위치
4. 연산의 결과로는 true, false 중 하나를 반환
5. instanceof를 이용한 연산결과로 true를 얻었다는 것은 참조변수가 검사한 타입으로 형변환이 가능하다는 것을 의미
6. 값이 null인 참조변수에 대해 instanceof 연산을 수행하면 false의 결과를 얻음

```java
void doWork(Car c) {
    if(c instanceof FireEngine) {
        FireEngine fe = (FireEngine)c;
        fe.water();
    }
        ...
    else if(c instanceof Ambulance) {
        Ambulance a = (Ambulance)c;
        a.siren();
    }
    ...
}
```

7. 조상 타입의 참조변수로는 실제 인스턴스의 멤버들을 모두 사용할 수 없기 때문에, 실제 인스턴스와 같은 타입의 참조변수로 형변환을 해야만 인스턴스의 모든 멤버 사용 가능

```java
class InstanceofTest {
	public static void main(String args[]) {
		FireEngine fe = new FireEngine();

		if(fe instanceof FireEngine) {
			System.out.println("This is a FireEngine instance.");
		} 

		if(fe instanceof Car) {
			System.out.println("This is a Car instance.");
		} 

		if(fe instanceof Object) {
			System.out.println("This is an Object instance.");
		} 

		System.out.println(fe.getClass().getName()); // 클래스의 이름을 출력
	}
} // class
class Car {}
class FireEngine extends Car {}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/eff1afdf-33c8-4d97-8ef6-da249140e6f6">
</div>

  - FireEngine 클래스는 Object 클래스와 Car 클래스의 자손 클래스이므로, 조상의 멤버들을 상속받았기 때문에, 둘의 인스턴스를 포함하고 있는 셈
  - 즉, 실제 인스턴스와 같은 타입의 instanceof 연산 이외의 조상 타입의 instanceof 타입에도 true의 결과를 얻음
  - 즉, instanceof 연산의 결과가 true 라는 것은 검사한 타입으로의 형변환을 해도 아무런 문제가 없다는 뜻
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/53b0cdbe-08fa-4d49-89e1-c7d68927385a">
</div>

  - 참조변수.getClass().getName() : 참조변수가 가리키고 있는 인스턴스의 클래스 이름을 문자열(String)로 반환

