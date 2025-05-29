-----
### 클래스가 필요한 이유
-----
1. 문제 : 학생 정보 출력 프로그램 만들기
   - 두 명의 학생 정보를 출력하는 프로그램을 작성
   - 각 학생은 이름, 나이, 성적을 가지고 있음
   - 요구 사항
      + 첫 번째 학생의 이름 : "학생1", 나이는 15, 성적은 90
      + 두 번째 학생의 이름 : "학생2", 나이는 16, 성적은 80
      + 각 학생의 정보를 다음과 같은 형식으로 출력  이름 : [이름], 나이 : [나이], 성적 : [성적]
      + 변수를 사용해서 학생 정보를 저장하고 변수를 사용해서 학생 정보를 출력

   - 예시 출력
```
이름 : 학생1, 나이 : 15, 성적 : 90
이름 : 학생2, 나이 : 16, 성적 : 80
```

2. ClassStart1 (/class1) - 변수 사용
```java
package class1;

public class ClassStart1 {
    public static void main(String[] args) {
        String student1Name = "학생1";
        int student1Age = 15;
        int student1Grade = 90;

        String student2Name = "학생2";
        int student2Age = 16;
        int student2Grade = 80;

        System.out.println("이름 : " + student1Name + ", 나이 : " + student1Age + ", 성적 : " + student1Grade);
        System.out.println("이름 : " + student2Name + ", 나이 : " + student2Age + ", 성적 : " + student2Grade);
    }
}
```
  - 학생 2명을 다루어야 하기 때문에 각각 다른 변수를 사용
  - 이 코드의 문제는 학생이 늘어날 때 마다 변수를 추가로 선언해야 하고, 또 출력하는 코드도 추가해야 함

3. ClassStart2 : 배열 사용
```java
package class1;

public class ClassStart2 {
    public static void main(String[] args) {
        String[] studentNames = { "학생1", "학생2" };
        int[] studentAges = { 15, 16 };
        int[] studentGrades = { 90, 80 };
        
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println("이름 : " + studentNames[i] + ", 나이 : " + studentAges[i] + ", 성적 : " + studentGrades[i]);
        }
    }
}
```
  - 배열을 사용한 덕분에 학생이 추가되어도 배열에 학생의 데이터만 추가하면 됨
  - 이제 변수를 더 추가하지 않아도 되고, 출력 부분의 코드도 그대로 유지 가능

  - 학생 추가 전
```java
String[] studentNames = {"학생1", "학생2"};
int[] studentAges = {15, 16};
int[] studentGrades = {90, 80};
```

  - 학생 추가 후
```java
String[] studentNames = {"학생1", "학생2", "학생3", "학생4", "학생5"};
int[] studentAges = {15, 16, 17, 10, 16};
int[] studentGrades = {90, 80, 100, 80, 50};
```

4. 배열 사용의 한계
   - 배열을 사용해서 코드 변경을 최소화하는데는 성공했지만, 한 학생의 데이터가 studentNames[], studentAges[], studentGrades[] 라는 3개의 배열에 나누어져 있음
   - 따라서 데이터를 변경할 때 매우 조심해서 작업해야 함
   - 예를 들어서 학생 2의 데이터를 제거하려면 각각의 배열마다 학생2의 요소를 정확하게 찾아서 제거 해주어야 함
      + 학생 2 제거
```java
String[] studentNames = {"학생1", "학생3", "학생4", "학생5"};
int[] studentAges = {15, 17, 10, 16};
int[] studentGrades = {90, 100, 80, 50};
```
   - 한 학생의 데이터가 3개의 배열에 나누어져 있기 때문에 3개의 배열을 각각 변경해야 함
   - 그리고 한 학생의 데이터를 관리하기 위해 3개 배열의 인덱스 순서를 항상 정확하게 맞추어야 함
   - 이렇게 하면 특정 학생의 데이터를 변경할 때 실수할 가능성이 매우 높음
