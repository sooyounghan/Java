-----
### 래퍼 클래스 (Wrapper Class)
-----
1. 자바에서는 8개의 기본형을 객체로 다루지 않는데, 이것이 바로 자바가 완전한 객체지향 언어가 아니라는 이유 (대신, 높은 성능을 가짐)
2. 기본형(Primitive Type) 변수도 객체로 다뤄야하는 경우에 사용되는 것이 래퍼 클래스(Wrapper Class)
3. 8개의 기본형을 대표하는 8개의 래퍼 클래스가 존재하는데, 이를 이용하면 기본형 값을 객체로 다룰 수 있음
4. char형과 int형을 제외한 나머지는 자료형을 이름의 첫글자를 대문자로 한 것이 래퍼 클래스의 이름 (char : Character, int : Integer)
5. 래퍼클래스의 생성자는 매개변수로 문자열이나 각 자료형의 값들을 인자로 받음
  - 단, 주의할 것은 생성자의 매개변수로 문자열을 제공할 때, 각 자료형에 알맞는 문자열을 사용해야함
  - 예) new Integer("1.0");과 같이하면, NumberFormatException 발생
6. 래퍼 클래스들은 객체 생성 시 생성자의 인자로 주어진 각 자료형에 알맞은 값을 내부적을 저장
  - 예) int형의 래퍼 클래스인 Integer 클래스의 실제 코드
```java
public final class Integer extends Number implements Comparable {
    ...
    private int value;
    ...
}
```

7. 래퍼 클래스 생성자
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/3c1528de-73f0-422a-842e-d2ac37d815e5">
</div>

```java
class WrapperEx1 {
	public static void main(String[] args) {
		Integer i  = new Integer(100);
		Integer i2 = new Integer(100);

		System.out.println("i==i2 ? "+(i==i2));
		System.out.println("i.equals(i2) ? "+i.equals(i2));
		System.out.println("i.compareTo(i2)="+i.compareTo(i2));
		System.out.println("i.toString()="+i.toString());

		System.out.println("MAX_VALUE="+Integer.MAX_VALUE);
		System.out.println("MIN_VALUE="+Integer.MIN_VALUE);
		System.out.println("SIZE=" +Integer.SIZE+" bits");
		System.out.println("BYTES="+Integer.BYTES+" bytes");
		System.out.println("TYPE=" +Integer.TYPE);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/cf0c9286-a485-4594-a402-5a036c7ca8a5">
</div>

  - 래퍼 클래스들은 모두 equals()가 오버라이딩되어 있어서 주소값이 아닌 객체가 가지고 있는 값 비교
  - 단, 오토박싱이 된다고 해도 Integer 객체에 비교연산자를 사용할 수 없으므로, compareTo() 제공
  - toString()도 오버라이딩 되어있어서 객체가 가지고 있는 값을 문자열로 변환
  - 이 외에도 래퍼 클래스는 MAX_VALUE, MIN_VALUE, SIZE, BYTES, TYPE 등 static 상수를 공통적으로 가짐

-----
### 문자열을 숫자로 변환하기
-----
```java
int i = new Integer("100").intValue(); // floatValue(), longValue(), ...
int i2 = Integer.parseInt("100");
int i3 = Integer.valueOf("100");
```

1. 문자열을 기본형 또는 래퍼 클래스로 변환하는 방법
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/2c8dc3ee-c814-4c2a-8007-0354affb7218">
</div>

   - 왼쪽은 반환값이 기본형(Primitive Type), 오른쪽은 래퍼 클래스 타입

2. JDK1.5부터 도입된 오토박싱(Auto-Boxing) 기능 떄문에 반환값이 기본형일 때와 래퍼 클래스일 때의 차이가 없어짐
   - 대신, 성능은 valueOf()가 조금 더 느림

3. 문자열이 10진수가 아닌 다른 진법(radix)의 숫자일 때도 변환이 가능하도록 메서드 제공
```java
static int parseInt(String s, int radix) // 문자열 s를 radix 진법으로 인식
static Integer valueOf(String s, int radix)
```

4. 문자열 "100"이 2진법 숫자라면, 10진수로 4이며, 8진법 숫자라면 10진수로 64, 16진법 숫자라면 10진수로 256
```java
int i4 = Integer.parseInt("100", 2); // 100(2) = 4
int i5 = Integer.parseInt("100", 8); // 100(8) = 64
int i6 = Integer.parseInt("100", 16); // 100(16) = 256
int i7 = Integer.parseInt("FF", 16); // FF(16) = 255
// int i8 = Integer.parseInt("FF"); // NumberFormatException 발생
```
  - 진법을 생략하면 10진수로 간주하므로 int i8 부분은 예외 발생

```java
class WrapperEx2 {
	public static void main(String[] args) {
		int		 i  = new Integer("100").intValue();
		int		 i2 = Integer.parseInt("100");
		Integer  i3 = Integer.valueOf("100");

		int i4 = Integer.parseInt("100",2);
		int i5 = Integer.parseInt("100",8);
		int i6 = Integer.parseInt("100",16);
		int i7 = Integer.parseInt("FF", 16);
//		int i8 = Integer.parseInt("FF");  // NumberFormatException 발생

		Integer i9 = Integer.valueOf("100",2);
		Integer i10 = Integer.valueOf("100",8);
		Integer i11 = Integer.valueOf("100",16);
		Integer i12 = Integer.valueOf("FF",16);
//		Integer i13 = Integer.valueOf("FF"); // NumberFormatException 발생

		System.out.println(i);
		System.out.println(i2);
		System.out.println(i3);
		System.out.println("100(2) -> "+i4);
		System.out.println("100(8) -> "+i5);
		System.out.println("100(16)-> "+i6);
		System.out.println("FF(16) -> "+i7);

		System.out.println("100(2) -> "+i9);
		System.out.println("100(8) -> "+i10);
		System.out.println("100(16)-> "+i11);
		System.out.println("FF(16) -> "+i12);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/44f07bcc-f68b-4c3f-912e-a49538f98d94">
</div>

-----
### 오토박싱 & 언박싱 (Auto-Boxing & UnBoxing)
-----
1. JDK1.5이전에는 기본형과 참조형 간이 연산이 불가능했으므로, 래퍼 클래스로 기본형을 객체로 만들어서 연산
2. 하지만, JDK1.5이후로는 기본형과 참조형 간의 덧셈이 가능하게 됨 (컴파일러가 자동으로 변환해주는 코드를 삽입)
3. 다음과 같이 Integer 객체를 int 타입의 값을 변환해주는 intValue() 추가
```java
int i = 5;
Integer iObj = new Integer(7);

int sum = i + iObj; // int sum = i + iObj.intValue();
```

4. 내부적으로 객체 배열을 가지고 있는 Vector나 ArrayList 클래스의 기본형 값을 저장해야할 때나 형변환이 필요할 때도 컴파일러가 자동적으로 코드 추가
5. 오토 박싱 (Auto-Boxing) : 기본형 값을 래퍼 클래스 객체로 자동 변환
6. 언박싱 (Unboxing) : 래퍼 클래스의 객체를 기본형 값으로 자동 변환

```java
ArrayList<Integer> list = new ArrayList<Integer>();

list.add(10); // Auto-Boxing. 10 → new Integer(10)
int value = list.get(0); // Unboxing. new Integer(10) → 10
```

```java
class WrapperEx3 {
	public static void main(String[] args) {
		int i = 10;

    // 기본형을 참조형으로 형변환 (형변환 생략 가능)
		Integer intg = (Integer)i; // Integer intg = Integer.valueOf(i);
		Object   obj = (Object)i;  // Object obj = (Object)Integer.valueOf(i);

		Long     lng = 100L;  // Long lng = new Long(100L);

		int i2 = intg + 10;   // 참조형과 기본형 간 연산 가능
		long l = intg + lng;  // 참조형 간 연산 가능

		Integer intg2 = new Integer(20);
		int i3 = (int)intg2;  // 참조형을 기본형으로 형변환도 가능 (형변환 생략 가능)

		Integer intg3 = intg2 + i3; 

		System.out.println("i     ="+i);
		System.out.println("intg  ="+intg);
		System.out.println("obj   ="+obj);
		System.out.println("lng   ="+lng);
		System.out.println("intg + 10  ="+i2);
		System.out.println("intg + lng ="+l);
		System.out.println("intg2 ="+intg2);
		System.out.println("i3    ="+i3);
		System.out.println("intg2 + i3 ="+intg3);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/90fa92d2-48f4-46ab-8be3-8ad08573c49d">
</div>



