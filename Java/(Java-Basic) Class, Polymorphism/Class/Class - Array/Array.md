-----
### 배열 도입 - 시작
-----
1. 클래스와 객체 덕분에 학생 데이터를 구조적으로 이해하기 쉽게 변경할 수 있었음
2. 각각의 학생 별로 객체를 생성하고, 해당 객체에 학생의 데이터를 관리하면 됨
3. 문제점
```java
System.out.println("이름 :" + student1.name + " 나이 :" + student1.age + ...);
System.out.println("이름 :" + student2.name + " 나이 :" + student2.age + ...);
```
   - 새로운 학생이 추가될 때 마다 출력하는 부분도 함께 추가해야 함
   - 배열을 사용하면 특정 타입을 연속한 데이터 구조로 묶어서 편리하게 관리할 수 있음
   - Student 클래스를 사용한 변수들도 Student 타입이기 때문에 학생도 배열을 사용해서 하나의 데이터 구조로 묶어서 관리할 수 있음
   - Student 타입을 사용하는 배열을 도입

4. ClassStart4
```java
package class1;

public class ClassStart4 {
    public static void main(String[] args) {
        Student student1 = new Student();

        // 객체 값 대입
        student1.name = "학생1";
        student1.age = 15;
        student1.grade = 90;

        Student student2 = new Student();

        student2.name = "학생2";
        student2.age = 16;
        student2.grade = 80;

        System.out.println("student1 = " + student1);
        System.out.println("student2 = " + student2);

        Student[] students = new Student[2];
        students[0] = student1;
        students[1] = student2;

        // 객체 값 사용
        System.out.println("이름 : " + students[0].name + ", 나이 : " + students[0].age + ", 성적 : " + students[0].grade);
        System.out.println("이름 : " + students[0].name + ", 나이 : " + students[0].age + ", 성적 : " + students[0].grade);
    }
}
```
5. 코드 분석
```java
// 객체 값 대입
Student student1 = new Student();

student1.name = "학생1";
student1.age = 15;
student1.grade = 90;

Student student2 = new Student();

student2.name = "학생2";
student2.age = 16;
student2.grade = 80;
```
  - Student 클래스를 기반으로 student1, student2 인스턴스를 생성
  - 필요한 값을 저장
<div align="center">
<img src="https://github.com/user-attachments/assets/f3b4f1fe-a424-4706-9e27-453e6a50a317">
</div>

   - 배열에 참조값 대입 : Student 를 담을 수 있는 배열을 생성하고, 해당 배열에 student1, student2 인스턴스를 보관
```java
Student[] students = new Student[2];
```
<div align="center">
<img src="https://github.com/user-attachments/assets/47a4d70f-7656-4106-9c84-92612002faf8">
</div>

  - Student 변수를 2개 보관할 수 있는 사이즈 2의 배열을 생성
  - Student 타입의 변수는 Student 인스턴스의 참조값을 보관
  - Student 배열의 각각의 항목도 Student 타입의 변수일 뿐이므로, 따라서 Student 타입의 참조값을 보관
    + student1, student2 변수 : Student 타입의 참조값을 보관
  - 배열에는 아직 참조값을 대입하지 않았기 때문에 참조값이 없다는 의미의 null 값으로 초기화

  - 배열에 객체 보관
```java
students[0] = student1;
students[1] = student2;

// 자바에서 대입은 항상 변수에 들어 있는 값을 복사
students[0] = x001;
students[1] = x002;
```
   - 자바에서 대입은 항상 변수에 들어 있는 값을 복사
   - student1, student2 에는 참조값이 보관 : 따라서 이 참조값이 배열에 저장
   - 또는 student1, student2에 보관된 참조값을 읽고 복사해서 배열에 대입한다고 표현

   - 배열에 참조값을 대입한 이후 배열 그림
<div align="center">
<img src="https://github.com/user-attachments/assets/18a4acd0-e7f8-4a51-96b6-4110eb1866b7">
</div>

   - 배열은 x001, x002 의 참조값을 가짐
   - 참조값을 가지고 있기 때문에 x001 (학생1), x002 (학생2) Student 인스턴스에 모두 접근할 수 있음

   - 배열에 참조값을 대입한 이후 최종
<div align="center">
<img src="https://github.com/user-attachments/assets/9545100d-1af7-484f-b9a6-9a25c99e866c">
</div>

   - 자바에서 대입은 항상 변수에 들어 있는 값을 복사해서 전달
```java
students[0] = student1;
students[1] = student2;

// 자바에서 대입은 항상 변수에 들어 있는 값을 복사
students[0] = x001;
```

   - 자바에서 변수의 대입(=) : 모두 변수에 들어있는 값을 복사해서 전달하는 것
   - 이 경우 오른쪽 변수인 student1, student2 에는 참조값이 존재
   - 그래서 이 값을 복사해서 왼쪽에 있는 배열에 전달
   - 따라서 기존 student1 , student2 에 들어있던 참조값은 당연히 그대로 유지

   - 💡 주의 : 변수에는 인스턴스 자체가 들어있는 것이 아니며, 인스턴스의 위치를 가리키는 참조값이 들어있는 것
     + 따라서 대입(=) 시에 인스턴스가 복사되는 것이 아니라 참조값만 복사

6. 배열에 들어있는 객체 사용
   - 배열에 들어있는 객체를 사용하려면 먼저 배열에 접근하고, 그 다음에 객체에 접근하면 됨
   - 학생 1 예제
```java
System.out.println(students[0].name); // 배열 접근 시작
System.out.println(x005[0].name); // [0]를 사용해서 x005 배열의 0번 요소에 접근
System.out.println(x001.name); // .(dot)을 사용해서 참조값으로 객체에 접근
System.out.println("학생1");
```
   - 학생 2 예제
```java
System.out.println(students[1].name); // 배열 접근 시작
System.out.println(x005[1].name); // [1]를 사용해서 x005 배열의 1번 요소에 접근
System.out.println(x002.name); // .(dot)을 사용해서 참조값으로 객체에 접근
System.out.println("학생2");
```
