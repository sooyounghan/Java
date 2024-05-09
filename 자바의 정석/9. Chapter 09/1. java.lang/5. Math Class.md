-----
### Math Class
-----
1. 기본적인 수학 계산에 유용한 메서드로 구성
2. Math 클래스의 생성자는 접근 제어자가 private이므로 다른 클래스에서 Math 인스턴스를 생성할 수 없도록 되어있음
   - 클래스 내 인스턴스 변수가 하나도 없어서 인스턴스를 생성할 필요가 없음
3. Math 클래스의 메서드는 모두 static이며, 2개의 상수만 정의
```java
public static final double E = 2.7182818284590452354; // 자연로그 밑
public static final double PI = 3.14159265358979323846; // 원주율
```

-----
### 올림, 버림, 반올림
-----
1. 소수점 n번째 자리에서 반올림한 값을 얻기 위해서는 round()를 사용해야 하는데, 이 메서드는 항상 소수점 첫째자리에서 반올림을 해서 정수값(long)을 결과로 반환
2. 원하는 자리 수 반올림된 값을 얻기 위해서 간단히 10의 n제곱으로 곱한 후, 다시 곱한 수로 나눠주기만 하면 됨
```
A. 원래 값에 100을 곱함 (90.7552 * 100 = 9075.52)
B. 위의 결과에 Math.round()를 사용 (Math.round(9075.52) = 9076)
C. 위 결과를 다시 100.0으로 나눔 (9076/100.0 = 90.76)
```

```java
import static java.lang.Math.*;
import static java.lang.System.*;

class MathEx1 {
	public static void main(String args[]) {
		double val = 90.7552;
		out.println("round("+ val +")=" + round(val));  // 반올림

		val *= 100;
		out.println("round("+ val +")=" + round(val));  // 반올림

		out.println("round("+ val +")/100  =" + round(val)/100);  // 반올림
		out.println("round("+ val +")/100.0=" + round(val)/100.0);  // 반올림
		out.println();
		out.printf("ceil(%3.1f)=%3.1f%n",  1.1, ceil(1.1));   // 올림
		out.printf("floor(%3.1f)=%3.1f%n", 1.5, floor(1.5));  // 버림	
		out.printf("round(%3.1f)=%d%n",    1.1, round(1.1));  // 반올림
		out.printf("round(%3.1f)=%d%n",    1.5, round(1.5));  // 반올림
		out.printf("rint(%3.1f)=%f%n",     1.5, rint(1.5));   // 반올림
		out.printf("round(%3.1f)=%d%n",   -1.5, round(-1.5)); // 반올림
		out.printf("rint(%3.1f)=%f%n",    -1.5, rint(-1.5));  // 반올림
		out.printf("ceil(%3.1f)=%f%n",    -1.5, ceil(-1.5));  // 올림
		out.printf("floor(%3.1f)=%f%n",   -1.5, floor(-1.5)); // 버림
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/9e430f0a-e420-4ba7-9fde-8b097c715dde">
</div>

  - rint()도 round()처럼 소수첫 첫째 자리에서 반올림하지만, 반환값이 double이며, 두 정수의 정가운데 있는 값은 가장 가까운 짝수 정수를 반환
```java
		out.printf("round(%3.1f)=%d%n",    1.5, round(1.5));  // 반환값 : int
		out.printf("rint(%3.1f)=%f%n",     1.5, rint(1.5));   // 반환값 : double
```

  - round()는 소수점 첫째자리가 5일 때, 더 큰 값으로 반올림하므로 -1이지만, rint() -1.5와 같이 홀수(-1.0)와 짝수(-2.0)의 중간의 있는 경우 짝수(-2.0)를 결과로 반환

-----
### 예외를 발생시키는 메서드
-----
1. 메서드 이름에 'Exact'가 포함된 메서드들은 JDK1.8부터 새롭게 추가되었으며, 이들은 정수형 간 연산에서 발생할 수 있는 오버플로우(Overflow)를 감지하기 위한 것
```java
int addExact(int x, int y) // x + y
int subtractExact(int x, int y) // x - y
int multiplyExact(int x, int y) // x * y
int incrementExact(int a) // a++
int decrementExact(int a) // a--
int negateExact(int a) // -a
int toIntExact(long value) // (int)value - int로 형변환
```

2. 연산자는 단지 결과를 반환할 뿐, 오버플로우 발생 여부에 대해 알려주지 않으므로, 해당 메서드들을 통해 오버플로우가 발생하면 예외(ArithmeticException) 발생

```java
import static java.lang.Math.*;
import static java.lang.System.*;

class MathEx2 {
	public static void main(String args[]) {
		int i = Integer.MIN_VALUE;

		out.println("i ="+i);
		out.println("-i="+(-i));

		try {
			out.printf("negateExact(%d)= %d%n",  10, negateExact(10));
			out.printf("negateExact(%d)= %d%n", -10, negateExact(-10));

			out.printf("negateExact(%d)= %d%n", i, negateExact(i)); // 예외 발생
		} catch(ArithmeticException e) {
			// i를 long타입으로 형변환 한 다음에 negateExact(long i) 호출
		     out.printf("negateExact(%d)= %d%n",(long)i,negateExact((long)i));
		}
	} // main
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/1c6f1c81-bcb6-4f32-af7b-92dee50a08ae">
</div>

  - 변수 i에 int타입 최소값인 Integer.MIN_VALUE를 저장 후, 부호 연산자로 i의 부호를 반대로 바꿈
  - -i의 값을 보면, 부호가 바뀌지 않고 i 값 그대로 출력
  - 정수형의 최소값에 비트전환연산자 ~를 적용하면, 최대값이 되는데 여기에 1을 더해서 오버플로우 발생
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/5b8b9965-b314-4d51-a047-4937beefc1e4">
</div>

-----
### 삼각함수, 지수, 로그
-----
```java
import static java.lang.Math.*;
import static java.lang.System.*;

class MathEx3 {
	public static void main(String args[]) {
		int x1=1, y1=1;  // (1, 1)
		int x2=2, y2=2;  // (2, 2)

		double c = sqrt(pow(x2-x1, 2) + pow(y2-y1, 2));
		double a = c * sin(PI/4);  // PI/4 rad = 45 degree
		double b = c * cos(PI/4);
//		double b = c * cos(toRadians(45));

		out.printf("a=%f%n", a);   
		out.printf("b=%f%n", b);  
		out.printf("c=%f%n", c);  
		out.printf("angle=%f rad%n", atan2(a,b));	
		out.printf("angle=%f degree%n%n", atan2(a,b) * 180 / PI);	
//		out.printf("angle=%f degree%n%n", toDegrees(atan2(a,b)));	

		out.printf("24 * log10(2)=%f%n",   24 * log10(2));  // 7.224720
		out.printf("53 * log10(2)=%f%n%n", 53 * log10(2));  // 15.954590
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/0fb824f7-ed71-4d5b-a6f6-26ac58cf7604">
</div>

1. 두 점 간의 거리
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/01bf24c8-2449-4762-b200-4aa9d8968a8b">
</div>

2. 제곱근 계산 : sqrt() / n제곱 계산 : pow()
```java
double c = sqrt(pow(x2-x1, 2) + pow(y2-y1, 2));
```

3. 삼각함수
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/f1717ba2-bcf1-4654-b3cf-60fb72537856">
</div>

```java
double a = c * sin(PI/4); // PI/4 radian = 45 degree
double b = c * cos(PI/4);
double b = c * cos(toRadians(45)); // 각도를 Radian으로 변환
```

   - 삼각함수는 매개변수 단위가 라디안(radian)이므로 45도를 라디안(Radian) 단위의 값으로 변환해야 함
   - 180˚ = π rad이므로, 45˚ = π/4 rad 이며, 다른 방법으로는 toRadians(double angdeg)를 이용 가능 (이 메서드의 반환값은 dobule)

```java
out.printf("angle=%f rad%n", atan2(a,b));	
out.printf("angle=%f degree%n%n", atan2(a,b) * 180 / PI);	
out.printf("angle=%f degree%n%n", toDegrees(atan2(a,b)));	
```

   - atan2 메서드는 직각 삼각형에서 두 변의 길이 a, b를 알면 끼인각 θ를 구해주며, 반환값은 역시 라디안 (radian)
   - 이를 degree 단위로 변환하려면 180 / PI를 곱하거나 toDegrees(double argrand)를 이용

-----
### StrictMath 클래스
-----
1. Math 클래스는 최대한의 성능을 얻기 위해 JVM이 설치된 OS의 메서드를 호출해서 사용 (즉, OS에 의존적인 계산을 하고 있음)
2. 예를 들어, 부동 소수점의 경우에는 반올림 처리방법 설정이 OS마다 다를 수 있으므로 자바로 작성된 프로그램에도 불구하고, 컴퓨터마다 다른 값이 나올 수 있음
3. 이러한 차이를 없애기 위해 성능을 다소 포기하는 대신, 어떤 OS에서 실행되어도 항상 같은 결과를 얻도록 Math클래스를 새로 작성한 것이 StrictMath 클래스

-----
### Math 클래스 메서드
-----
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/604c1d7a-0e75-4609-b07d-58665825d6a6">
</div>
