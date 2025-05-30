-----
### 클래스 도입
-----
1. 클래스를 사용해서 학생이라는 개념을 만들고, 각각의 학생 별로 본인의 이름, 나이, 성적을 관리하는 것
2. Class (/class1)
```java
package class1;

public class Student {
    String name;
    int age;
    int grade;
}
```

2. class 키워드를 사용해서 학생 클래스(Student)를 정의
   - 학생 클래스는 내부에 이름(name), 나이(age), 성적(grade) 변수를 가짐
   - 클래스에 정의한 변수 : 멤버 변수, 또는 필드
      + 멤버 변수(Member Variable) : 이 변수들은 특정 클래스에 소속된 멤버
      + 필드(Field) : 데이터 항목을 가리키는 전통적인 용어 (데이터베이스, 엑셀 등에서 데이터 각각의 항목을 필드)
   - 자바에서 멤버 변수, 필드는 같은 뜻으로, 클래스에 소속된 변수를 뜻함
   - 클래스는 관례상 대문자로 시작하고 낙타 표기법을 사용 (예): Student, User, MemberService)

3. ClassStart3
```java
package class1;

public class ClassStart3 {
    public static void main(String[] args) {
        Student student1 = new Student();

        student1.name = "학생1";
        student1.age = 15;
        student1.grade = 90;

        Student student2 = new Student();

        student2.name = "학생2";
        student2.age = 16;
        student2.grade = 80;

        System.out.println("이름 : " + student1.name + ", 나이 : " + student1.age + ", 성적 : " + student1.grade);        System.out.println("이름 : " + student1.name + ", 나이 : " + student1.age + ", 성적 : " + student1.grade);
        System.out.println("이름 : " + student2.name + ", 나이 : " + student2.age + ", 성적 : " + student2.grade);
    }
}
```
  - 실행 결과

```
이름 : 학생1, 나이 : 15, 성적 : 90
이름 : 학생2, 나이 : 16, 성적 : 80
```

4. 클래스와 사용자 정의 타입
  - 타입은 데이터의 종류나 형태를 나타냄
    + int라고 하면 정수 타입, String이라고 하면 문자 타입
  - 클래스를 사용하면 int, String 과 같은 타입을 직접 만들 수 있음
    + 사용자가 직접 정의하는 사용자 정의 타입을 만들려면 설계도가 필요 : 설계도가 바로 클래스
  - 설계도인 클래스를 사용해서 실제 메모리에 만들어진 실체 : 객체 또는 인스턴스
    + 클래스를 통해서 사용자가 원하는 종류의 데이터 타입을 마음껏 정의 가능

5. 용어 : 클래스, 객체, 인스턴스
   - 클래스는 설계도이고, 이 설계도를 기반으로 실제 메모리에 만들어진 실체를 객체 또는 인스턴스 (둘 다 같은 의미로 사용)
   - 학생(Student) 클래스를 기반으로 학생1(student1), 학생2(student2) 객체 또는 인스턴스를 생성

6. 코드 작동 과정
   - 변수 선언
<div align="center">
<img src="https://github.com/user-attachments/assets/675d2659-d59c-4e40-bebe-0d74d93109c7">
</div>

   - Student student1
      + Student 타입을 받을 수 있는 변수를 선언
      + int는 정수를, String은 문자를 담을 수 있듯이 Student는 Student 타입의 객체(인스턴스)를 받을 수 있음
    
   - 객체 생성
<div align="center">
<img src="https://github.com/user-attachments/assets/91eef954-2055-4004-ac47-b76c83c666b1">
</div>

   - student1 = new Student() 코드를 나누어 분석
      + 객체를 사용하려면 먼저 설계도인 클래스를 기반으로 객체(인스턴스)를 생성해야 함
      + new Student() : new 는 새로 생성한다는 뜻
      + new Student()는 Student 클래스 정보를 기반으로 새로운 객체를 생성하라는 뜻
      + 메모리에 실제 Student 객체(인스턴스)를 생성
   - 객체를 생성할 때는 new 클래스명()을 사용 (마지막에 ()도 추가해야 함)
   - Student 클래스는 String name, int age, int grade 멤버 변수를 가지고 있음
   - 이 변수를 사용하는데 필요한 메모리 공간도 함께 확보

   - 참조값 보관
<div align="center">
<img src="https://github.com/user-attachments/assets/a158c196-2e90-4b25-aeed-3bc5562f4dba">
</div>

   - 객체를 생성하면 자바는 메모리 어딘가에 있는 이 객체에 접근할 수 있는 참조값(주소) (x001)을 반환
      + x001 이라고 표현한 것 : 참조값
      + new 키워드를 통해 객체가 생성되고 나면 참조값을 반환
      + 앞서 선언한 변수인 Student student1 에 생성된 객체의 참조값(x001)을 보관
   - Student student1 변수는 이제 메모리에 존재하는 실제 Student 객체(인스턴스)의 참조값을 가지고 있음
   - student1 변수는 방금 만든 객체에 접근할 수 있는 참조값을 가지고 있음
     + 따라서 이 변수를 통해서 객체를 접근(참조)할 수 있음
     + student1 변수를 통해 메모리에 있는 실제 객체를 접근하고 사용할 수 있음

7. 참조값을 변수에 보관해야 하는 이유
   - 객체를 생성하는 new Student() 코드 자체에는 아무런 이름이 없음
     + 단순히 Student 클래스를 기반으로 메모리에 실제 객체를 만드는 것
     + 따라서 생성한 객체에 접근할 수 있는 방법이 필요
  - 이런 이유로 객체를 생성할 때 반환되는 참조값을 어딘가에 보관해두어야 함
  - 앞서 Student student1 변수에 참조값(x001)을 저장해두었으므로 저장한 참조값(x001)을 통해서 실제 메모리에 존재하는 객체에 접근할 수 있음

8. 정리
```java
Student student1 = new Student(); // 1. Student 객체 생성
Student student1 = x001; // 2. new Student()의 결과로 x001 참조값 반환
student1 = x001; // 3. 최종 결과
```
  - 이후에 학생2 (student2)까지 생성하면 다음과 같이 Student 객체(인스턴스)가 메모리에 2개 생성
  - 각각 참조값이 다르므로 서로 구분할 수 있음

<div align="center">
<img src="https://github.com/user-attachments/assets/a0d8efcd-bf84-4557-93e2-6b5bb7f00628">
</div>

9. 참조값 확인하고 싶다면, 객체를 담고 있는 변수 출력
```java
System.out.println("student1 = " + student1);
System.out.println("student2 = " + student2);
```
  - 출력 결과
```
student1 = class1.Student@2f4d3709
student2 = class1.Student@7291c18f
```
  - @앞은 패키지 + 클래스 정보를 뜻함
  - @뒤에 16진수는 참조값을 뜻함
