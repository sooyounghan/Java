-----
### 패키지 (Package)
-----
1. 클래스의 묶음을 의미 (패키지에는 클래스 또는 인터페이스를 포함시킬 수 있음)
2. 즉, 서로 관련된 클래스를 그룹 단위로 묶어 놓은 것
3. 클래스의 실제 이름은 패키지 명을 포함하는 것
   - String 클래스의 실제 이름 : java.lang.String (즉, java.lang 패키지에 속한 String 클래스)
   - 따라서, 같은 이름의 클래스일지라도 다른 패키지에 속하면 패키지명으로 구별 가능
4. 클래스가 물리적인 하나의 클래스파일(.class)인 것과 같이 패키지는 물리적으로 하나의 디렉토리
   - 따라서, 어떤 패키지에 속한 클래스는 해당 디렉토리에 존재하는 클래스파일(.class)이어야 함
   - java.lang.String 클래스는 물리적으로 java의 서브디렉토리인 lang에 속한 String.class 파일
5. 디렉토리가 하위 디렉토리를 가질 수 있는 것처럼, 패키지도 다른 패키지를 포함할 수 있으며, '.'으로 구분

-----
### 패키지 (Package)의 특징
-----
1. 하나의 소스파일에는 첫 번째 문장으로 단 한 번의 패키지 선언만을 허용
2. 모든 클래스는 반드시 하나의 패키지에 속해야 함
3. 패키지는 점(.)으로 구분자로하여 계층 구조 구성 가능
4. 패키지는 물리적으로 클래스 파일(.class)을 포함하는 하나의 디렉토리

-----
### 패키지 (Package)의 선언
-----
1. 클래스나 인터페이스의 소스 파일(.java)의 맨 위에 다음과 같이 선언
```java
package 패키지명;
```

2. 반드시 소스파일에서 주석과 공백을 제외한 첫 번째 문장이어야 하며, 하나의 소스파일에 단 한 번만 선언 가능
3. 패키지명은 대소문자 모두 사용 가능하지만, 클래스명과 구분을 위해 소문자로 쓰는 것이 관례
4. 소스파일에 자신이 속할 패키지를 지정하지 않은 클래스는 자동적으로 '이름없는 패키지(Unnamed Package)'에 속하게 됨
   - 즉, 패키지를 지정하지 않는 모든 클래스들은 같은 패키지에 속하는 것

-----
### import 문
-----
1. 소스코드를 작성할 때, 다른 패키지의 클래스를 사용하려면 패키지명이 포함된 클래스 이름 사용해야 함
2. import문은 사용하고자 하는 클래스의 패키지를 미리 명시해주면, 소스코드에서 사용되는 클래스 이름에서 패키지명 생략 가능
3. 즉, 컴파일러에게 소스파일에 사용된 클래스 패키지에 대한 정보를 제공
   - 컴파일러는 컴파일 시 import문을 통해 소스파일에 사용된 클래스들의 패키지를 알아낸 다음, 모든 클래스 이름 앞에 패키지 명을 붙여주는 것
   - 💡 import문은 프로그램 성능에 전혀 영향을 미치지 않음. 단, 많이 사용하면 컴파일 시간이 조금 더 걸림

-----
### import문 선언
-----
1. 일반적인 소스파일(*.java)의 구성
```
A. package 문
B. import 문 (package 문과 달리 한 소스파일에 여러 번 선언 가능)
C. 클래스 선언
```

2. import문 선언 방법
```java
import 패키지명.클래스명;
또는
import 패키지명.*;
```
  - 키워드 import와 패키지명을 생략하고자 하는 클래스의 이름을 패키지명에 함께 작성하면 됨
  - 같은 패키지에서 여러 클래스가 사용되면, '패키지명.*'을 이용해 지정된 패키지에 속한 모든 클래스를 패키지명 없이 사용 가능

3. 💡 주의할 점 : import문에서 클래스 이름 대신, '*'을 사용하는 것은 하위 패키지의 클래스까지 모두 포함하는 것이 아니라는 것임
```java
import java.util.*;
import java.text.*;
```
  - 위 두 문장을 다음과 같이 할 수 없음
```java
import java.*;
```

```java
import java.text.SimpleDateFormat;
import java.util.Date;
// java.text 패키지와 java.util은 서로 다른 패키지

class ImportTest 
{
	public static void main(String[] args) 
	{
		 Date today = new Date();
		 
		 SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		 SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

		 System.out.println("오늘 날짜는 " + date.format(today));
		 System.out.println("현재 시간은 " + time.format(today));
	}
}
```
  - import문을 지정하지 않으면 다음과 같음
```java
java.util.Date today = new java.util.Date();
 
java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyy/MM/dd");
java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("hh:mm:ss a");
```

4. 💡 java.lang 패키지는 묵시적으로 import문이 선언되어있음
```java
import java.lang.*;
```
  - 매우 빈번히 사용되는 중요한 클래스들이 속한 패키지이기 때문에 지정하지 않도록 설정되어있음

-----
### static import문
-----
: static import문을 사용하면 static 멤버들을 호출할 때 클래스 이름 생략 가능
```java
import static java.lang.Integer.*; // Integer 클래스의 모든 static 메서드
import static java.lang.Math.random; // Math.random()만. 괄호를 붙이지 않음
import static java.lang.System.out; // System.out을 out만으로 참조 가능
```
```java
import static java.lang.System.out;
import static java.lang.Math.*;

class StaticImportEx1 {
	public static void main(String[] args) {	
		// System.out.println(Math.random());
		out.println(random());

		// System.out.println("Math.PI :"+Math.PI);
		out.println("Math.PI :" + PI);
	}
}
```
