-----
### StringBuffer Class
-----
1. String 클래스는 인스턴스는 생성할 때 지정된 문자열을 변경할 수 없지만, StringBuffer 클래스는 변경이 가능
2. 내부적으로 문자열 편집을 위한 버퍼(Buffer)를 가지고있으며, StringBuffer 인스턴스를 생성할 때 그 크기를 지정할 수 있음
3. StringBuffer 클래스는 String 클래스와 같이 문자열을 저장하기 위한 char형 배열의 참조변수를 인스턴스 변수로 선언
   - StringBuffer 인스턴스는 생성될 때, char형 배열이 생성되며 이 때 생성된 char형 배열을 인스턴스 변수 value가 참조
```java
public final class StringBuffer implements java.io.Serializable {
    private char[] value;
    ...
}
```

-----
### StringBuffer 생성자
-----
1. StringBuffer 클래스의 인스턴스를 생성할 때, 적절한 길이의 char형 배열이 생성되고, 이 배열을 문자열을 저장하고 편집하기 위한 공간(buffer)으로 사용
2. StringBuffer 인스턴스를 생성할 때는 생성자 StringBuffer(int length)를 사용해, 인스턴스에 저장될 문자열 길이를 고려해 충분히 여유있는 크기로 지정
3. StringBuffer 인스턴스를 생성할 때, 버퍼 크기를 지정해주지 않으면 16개의 문자를 저장할 수 있는 크기의 버퍼 생성
```java
public StringBuffer(int length) {
    value = new char[length];
    shared = false;
}

public StringBuilder() {
    this(16); // 버퍼의 크기를 지정하지 않으면 버퍼의 크기는 16
}

public StringBuilder(String str) {
    this(str.length() + 16); // 지정한 문자열의 길이보다 16이 더 크게 버퍼 생성
}
```

4. StringBuffer 인스턴스로 문자열을 다루는 작업을 할 때, 버퍼 크기가 작업하려는 문자열의 길이보다 작을 때는 내부적으로 버퍼 크기를 증가시키는 작업 수행
   - 배열의 길이는 변경될 수 없으므로 새로운 길이 배열 생성 후 이전 배열의 값 복사
```java
...
// 새로운 길이 (newCapacity)의 배열 생성. newCapacity는 정수값
char newValue[] = new char[newCapacity];

// 배열 value의 내용을 배열 newValue로 복사
System.arraycopy(value, 0, newValue, 0, count); // count는 문자열 길이
value = newValue; // 새로 생성된 배열의 주소를 참조변수 value에 저장하여, 길이가증가된 새로운 배열 참조
```

-----
### StringBuffer의 변경
-----
1. StringBuffer는 String과 달리 내용 변경이 가능
2. 아래와 같이 StringBuffer를 생성했다고 가정
```java
StringBuffer sb = new StringBuffer("abc");
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/d6942131-ae4c-4d70-bd69-4ad9c4c4fe3f">
</div>

3. sb에 문자열 "123"을 추가하면,
```java
sb.append("123"); // sb 내용 뒤 "123" 추가
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/b8eeb5e1-b1ad-4b66-8df2-24dfd1145d0c">
</div>
 
4. append()는 반환타입이 StringBuffer : 자신의 주소를 반환
   - 따라서, 다음과 같이 문장이 수행되면, sb에 새로운 문자열이 추가되고 sb 자신의 주소를 반환하여 sb2에는 sb의 주소가 저장
```java
StringBuffer sb2 = sb.append("ZZ");
System.out.println(sb); // abc123ZZ
System.out.println(sb2); // abc123ZZ
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/f75fefd1-a2af-4b5e-bdb7-0f57477e74bd">
</div>

5. 즉, sb와 sb2가 모두 같은 StringBuffer인스턴스를 가리키고 있으므로 같은 내용 출력
6. 따라서, 하나의 StringBuffer인스턴스에 대해 아래와 같이 연속적으로 append() 호출 가능
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/319e825d-5053-48f6-b58c-c571115b5f9b">
</div>

-----
### StringBuffer의 비교
-----
1. StringBuffer 클래스는 equals() 메서드를 오버라이딩 하지 않았음
2. 따라서, StringBuffer의 equals() 메서드를 사용해도 등가비교연산자(==)로 비교한 것과 같은 결과
```java
StringBuffer sb = new StringBuffer("abc");
StringBuffer sb2 = new StringBuffer("abc");

System.out.println(sb == sb2); // false
System.out.println(sb.equals(sb2); // false
```

3. 반면에, toString()은 오버라이딩 되어있어서 StringBuffer인스턴스에 toString()을 호출하면, 담고있는 문자열을 String으로 반환
4. 따라서, StringBuffer인스턴스에 담긴 문자열 비교를 위해서는 toString()을 호출해서, String 인스턴스를 얻고, 여기에 equals() 메서드 사용하여 비교
```java
String s = sb.toString();
String s2 = sb2.toString();

System.out.println(sb.equals(sb2)); // true
```

```java
class StringBufferEx1 {
	public static void main(String[] args) {
		StringBuffer sb  = new StringBuffer("abc");
		StringBuffer sb2 = new StringBuffer("abc");

		System.out.println("sb == sb2 ? " + (sb == sb2));
		System.out.println("sb.equals(sb2) ? " + sb.equals(sb2));
		
		// StringBuffer의 내용을 String으로 변환
		String s  = sb.toString();	// String s = new String(sb);와 동일
		String s2 = sb2.toString();

		System.out.println("s.equals(s2) ? " + s.equals(s2));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/55d9f48f-4107-4e69-8b4b-cc82bc1a7a9a">
</div>

-----
### StringBuffer 클래스 생성자와 메서드
-----
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/0fbce89c-a323-40a8-8e3f-48f412a09c6f">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/def5614b-1207-4cb3-bf38-8fd4f2e74322">
</div>

```java
class  StringBufferEx2 {
	public static void main(String[] args) {
		StringBuffer sb  = new StringBuffer("01");
		StringBuffer sb2 = sb.append(23);
		sb.append('4').append(56);
		
		StringBuffer sb3 = sb.append(78);			
		sb3.append(9.0);

		System.out.println("sb ="+sb);
		System.out.println("sb2="+sb2);
		System.out.println("sb3="+sb3);

		System.out.println("sb ="+sb.deleteCharAt(10));
		System.out.println("sb ="+sb.delete(3,6));
		System.out.println("sb ="+sb.insert(3,"abc"));
		System.out.println("sb ="+sb.replace(6, sb.length(), "END"));

		System.out.println("capacity="+sb.capacity());
		System.out.println("length="+sb.length());
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/0da89022-da92-4f14-82f9-100edcb7b8c8">
</div>

-----
### StringBuilder 클래스
-----
1. StringBuffer 클래스는 Multi-Thread에 안전(Thread Safe)하도록 동기화 (즉, StringBuffer의 성능을 떨어트림)
2. 따라서, StringBuffer에서 Thread의 동기화를 뺀 StringBuilder가 새로 추가
3. StringBuilder는 StringBuffer와 완전히 똑같은 기능으로 작성
```java
StringBuilder sb;
sb = new StringBuilder();
sb.append("abc");
```
4. StringBuffer도 충분히 성능이 좋기 때문에 성능향상이 필요한 경우를 제외하고 굳이 바꿀 필요 없음


