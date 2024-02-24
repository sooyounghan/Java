-----
### String Class
-----
1. 동일성 (Identity) : 두 참조변수가 동일 주소를 가리키고 있으면, 두 객체는 동일

       == : 주소 비교(주소가 일치하면 true)  

2. 동등성(Equality) ; 두 객체의 내용이 같다면, 두 객체는 동등

       - equals() : 문자열 값 자체를 비교하는 것으로 Object에서 상속받아 오버라이딩
       - Object의 equals() : 객체의 주소 동일여부 확인

-----
### hashCode()
-----
: Object Class : 객체를 식별하는 하나의 정수 형태의 값 (객체마다 다른 값을 가짐)

-----
### toString() 
-----
: Object Class  : 객체에 대한 정보를 문자열로 Return

       클래스.패키지명@16진수형태HashCode

```java
< 예제 >
/*
 * String Class - equals()
 */
public class String_Ex {
	public static void main(String[] args) {
		String str1 = "A";
		String str2 = "A";
		System.out.println("str1 = " + str1); 
		System.out.println("str2 = " + str2);
		
		System.out.print("str1 == str2 ? ");
		if(str1 == str2) {
			System.out.print("Same");
		}
		else {
			System.out.print("Different");
		}
		
		System.out.println();
		System.out.print("str1.equals(str2) ? ");		
		if(str1.equals(str2)) {
			System.out.print("Same");
		}
		else {
			System.out.print("Different");
		}
		
		System.out.println();
		System.out.println("---------------");
		
		str1 = new String("A");
		str2 = new String("A");
		System.out.println("str1 = " + str1);
		System.out.println("str2 = " + str2);
		
		System.out.print("str1 == str2 ? ");
		if(str1 == str2) {
			System.out.print("Same");
		}
		else {
			System.out.print("Different");
		}
		
		System.out.println();
		System.out.print("str1.equals(str2) ? ");
		if(str1.equals(str2)) {
			System.out.print("Same");
		}
		else {
			System.out.print("Different");
		}
	}
}
```

-----
### Math Class
-----
1. Math.lang Package 내 클래스
2. 수학 계산에 필요한 필드와 메소드 제공 (Static Field, Method로 객체 생성 불필요)
3. Static Field, Method이므로

       Field : 클래스명.필드명; (필드 : PI, E만 존재)
       Method : 클래스명.메소드명();

4. Math.random() : 난수 발생
5. Math.abs(정수 : int, long / 실수 : float. double) : 절댓값 반환 (타입 변환 없음)
6. Math.ceil(double) : 가장 가까운 큰 정수로 올림값 반환 (double 형)
7. Math.floor(double) : 가장 가까운 작은 정수로 내림값 반환 (double 형)
8. Math.round(float, double) : 반올림값 반환 (float -> int / double -> long) ⇒ 소수점 첫번째 자리에서 올림/내림/반올림

-----
### 난수 발생 (Math Class)
-----
1. java의 Math Class의 random() 이용 (범위 : 0.0 <= Math.random() < 1.0) → 반환값 : double
2. start부터 n개의 정수 중 정수 난수 발생

        1. 0.0 <= Math.random() < 1.0
        2. 0.0 <= Math.random() * n < n.0
        3. 0 <= (int)(Math.random() * n) < n
        : start <= (int)(Math.random() * n) + start < start + n → start ~ start + n – 1 까지의 난수 발생

3. 1 ~ n까지의 난수 정수 발생 : (int)(Math.random() * n) + 1

       1. 0.0 <= Math.random() < 1.0
       2. 0.0 <= Math.random() * n < n.0
       3. 0 <= (int)(Math.random() * n) < n
        : 1 <= (int)(Math.random() * n) + 1 < n + 1 → 1 ~ n 까지의 난수

```java
/*
 * Math Class
 *  - 수학 계산에 필요한 필드와 메소드(Static) 제공
 *    * Static : 객체 생성 불필요
 *      -> 클래스명.필드명; (예) Math.PI)
 *      -> 클래스명.메소드명(); (예) Math.random())
 *  - Field : PI, E
 */
public class MathEx01_p504 {
	public static void main(String[] args) {
		/*
		 * Field : PI, E
		 */
		double pi = Math.PI;
		System.out.println(pi);
		System.out.println(Math.E);
		
		/*
		 * Method
		 * Math.random() : 0.0 <= Math.random() [double] < 1.0
		 * start에서부터 n개의 정수 중 임의 정수 : (int)(Math.random() * n) + 1 
		 */
		double random = Math.random();
		System.out.println(random);
		
		int random_number = (int)Math.random(); // 0 <= (int)Math.random() < 1
		System.out.println(random_number);
		
		System.out.println((int)(Math.random() * 45) + 1); // 1 ~ 45의 난수 발생

		// Math.abs() : 절댓값 반환
		System.out.println("Math.abs(-100) : " + Math.abs(-100));
		System.out.println("Math.abs(100) : " + Math.abs(100));
		System.out.println("Math.abs(pi) : " + Math.abs(pi));
		
		// Math.ceil() : 올림값 반환
		System.out.println("Math.ceil(pi) : " + Math.ceil(pi));
		System.out.println("Math.ceil(-3.914) : " + Math.ceil(-3.914)); // -3.0
		
		// Math.floor() : 내림값 반환
		System.out.println("Math.floor(pi) : " + Math.floor(pi));
		System.out.println("Math.floor(-3.914) : " + Math.floor(-3.914)); // -4.0
		
		// Math.round() : 반올림값 반환
		System.out.println("Math.round(pi) : " + Math.round(pi));
		System.out.println("Math.round(3.5) : " + Math.round(3.5));
	}
}
```

-----
### 난수 발생 (Random Class)
-----
1. (Package : java.util.Random)
2. Random Class의 인스턴스는 난수 스트림을 생성에 사용 (Random.random()) → 클래스의 인스턴스를 사용해 난수 발생
3. Math.random()보다 다양한 데이터 타입, 세밀한 작업 가능
4. 동일한 종자값(Seed)로 두 개의 Random 인스턴스 생성  → 각각에 대해 종자값이 같으므로 Random 인스턴스들은 항상 같은 난수를 순서대로 반환

        * 종자값은 난수를 만드는 공식에 사용되는 값
        * Seed 값을 다르게 제공한다면, 서로 다른 숫자 시퀀스가 반환

5. 종자값(Seed)을 System.currentTimeMillis()를 하면 실행할 때마다 다른 난수가 발생

<div align = "center">
<img width="499" alt="img" src="https://github.com/sooyounghan/JAVA/assets/34672301/aeb313d8-05b0-473d-bfd0-1b18ae032136">
</div>

```java
import java.util.Random; // Random Class Import

/*
 * Random Class
 */

public class RandomEx {
	public static void main(String[] args) {
		System.out.println("-----Random Class-----");
		
		Random random = new Random(); // Random Class의 객체 생성
		
		for(int i = 0; i < 3; i++) {
			boolean r = random.nextBoolean(); // boolean형 난수 발생
			System.out.print(r + ", ");
		}
		
		System.out.println();
		
		for(int i = 0; i < 3; i++) {
			double d = random.nextDouble(); // double형 난수 발생 (0.0 <= x < 1.0)
			System.out.print(d + ", ");
		}
		
		System.out.println();
		
		for(int i = 0; i < 3; i++) {
			float f = random.nextFloat(); // float형 난수 발생 (0.0 <= x < 1.0)
			System.out.print(f + ", ");
		}
		
		System.out.println();
		
		for(int i = 0; i < 3; i++) {
			int num = random.nextInt(); // int형 난수 발생
			System.out.print(num + ", ");
		}

		System.out.println();
		
		for(int i = 0; i < 3; i++) {
			int num = random.nextInt(6); // int형 난수를 발생하되 1 ~ 5까지 난수 발생
			System.out.print(num + ", ");
		}
	}
}
```
