-----
### java.util.Random 클래스
-----
1. 난수를 얻는 방법은 Math.random()와 Random 클래스를 이용해 얻을 수 있음
2. 사실, Math.random() 또한 내부적으로 Random 클래스의 인스턴스를 생성해서 사용
```java
dobule randNum = Math.random();
double randNum = new Random().nextDouble(); // 위 문장과 동일
```

3. 예를 들어, 1 ~ 6사이의 정수로 난수를 얻고자 할 때 다음과 같음
```java
int num = (int)(Math.random() * 6) + 1;
int num = new Random().nextInt(6) + 1; // nextInt(6)는 0 ~ 6사이 정수 반환
```

4. Math.random()와 차이점은 '종자값(seed)' 설정 가능
   - 종자값이 같은 Random 인스턴스는 항상 같은 난수를 순서대로 반환
   - 종자값은 난수를 만드는 공식에 사용되는 값으로 같은 공식에 같은 값을 넣으면 같은 결과가 나오는 것 처럼 같은 종자값을 넣으면 같은 난수 발생
  
-----
### Random 클래스의 생성자와 메서드
-----
1. 생성자 Random()은 종자값을 System.currentTimeMillis()로 하기 때문에, 실행마다 얻는 난수가 달라짐
```java
public Random() {
    this(System.currentTimeMillis()); // Random(long seed) 호출
}
```

2. Random 클래스의 생성자와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/77b76731-b10b-4097-8a90-9fd3af322518">
<img src="https://github.com/sooyounghan/Java/assets/34672301/33184c54-7fd6-4889-85b4-3c23928c8681">
</div>

```java
import java.util.*;

class RandomEx1 {
	public static void main(String args[]) {
		Random rand  = new Random(1);
		Random rand2 = new Random(1);

		System.out.println("= rand =");
		for(int i=0; i < 5; i++)
			System.out.println(i + ":" + rand.nextInt());

		System.out.println();
		System.out.println("= rand2 =");
		for(int i=0; i < 5; i++)
			System.out.println(i + ":" + rand2.nextInt());
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/6a7a3695-c198-4934-9382-fbbb5aac76ab">
</div>

  - rand와 rand2가 같은 종자값 (seed)를 사용하므로 같은 값들을 같은 순서대로 확인
  - 같은 종자값을 갖는 Random 인스턴스는 시스템이나 실행 시간 등에 관계없이 항상 같은 값을 같은 순서로 반환할 것 보장

```java
import java.util.*;

class RandomEx2 { 
	public static void main(String[] args) { 
		Random rand = new Random();
		int[] number = new int[100]; 
		int[] counter = new int[10]; 

		for (int i=0; i < number.length ; i++ ) { 
		// System.out.print(number[i] = (int)(Math.random() * 10)); 
		// 0<=x<10 범위의 정수 x 반환
			System.out.print(number[i] = rand.nextInt(10));	  
		} 
		System.out.println(); 

		for (int i=0; i < number.length ; i++ ) { 
			counter[number[i]]++; 
		} 

		for (int i=0; i < counter.length ; i++ ) { 
			System.out.println( i +"의 개수 :"+ printGraph('#',counter[i]) + " " + counter[i]); 
		} 
	} 

	public static String printGraph(char ch, int value) { 
		char[] bar = new char[value]; 

		for(int i=0; i < bar.length; i++) { 
			bar[i] = ch; 
		} 
		return new String(bar); 
	} 
} 
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/370bbda1-5371-4238-84a8-125594e0bd5c">
</div>

  - nextInt(int n)은 0부터 n사이의 정수를 반환 (단, n은 범위에 포함되지 앟음)
