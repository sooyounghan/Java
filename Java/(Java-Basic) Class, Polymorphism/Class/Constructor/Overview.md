-----
### 생성자 - 필요한 이유
-----
1. 객체를 생성하는 시점에 어떤 작업을 하고 싶다면 생성자(Constructor)를 이용
2. MemberInit (/construct)
```java
package construct;

public class MemberInit {
    String name;
    int age;
    int grade;
}
```
3. MemberInitMain1
```java
package construct;

public class MemberInitMain1 {
    public static void main(String[] args) {
        MemberInit member1 = new MemberInit();

        member1.name = "user1";
        member1.age = 15;
        member1.grade = 90;

        MemberInit member2 = new MemberInit();

        member2.name = "user2";
        member2.age = 16;
        member2.grade = 80;

        MemberInit[] members = {member1, member2};

        for (MemberInit member : members) {
            System.out.println("이름 : " + member.name + ", 나이 : " + member.age + ", 성적 : " + member.grade);
        }
    }
}
```
  - 실행 결과
```
이름 : user1, 나이 : 15, 성적 : 90
이름 : user2, 나이 : 16, 성적 : 80
```
  - 회원 객체를 생성하고 나면 name, age, grade같은 변수에 초기값을 설정
  - 아마도 회원 객체를 제대로 사용하기 위해서는 객체를 생성하자 마자 이런 초기값을 설정해야 할 것
  - 이 코드에는 회원의 초기값을 설정하는 부분이 계속 반복되므로, 메서드를 사용해서 반복을 제거
  - MemberInitMain2
```java
package construct;

public class MemberInitMain2 {
    public static void main(String[] args) {
        MemberInit member1 = new MemberInit();
        initMember(member1, "user1", 15, 90);

        MemberInit member2 = new MemberInit();
        initMember(member2, "user2", 16, 80);

        MemberInit[] members = {member1, member2};

        for (MemberInit member : members) {
            System.out.println("이름 : " + member.name + ", 나이 : " + member.age + ", 성적 : " + member.grade);
        }
    }

    static void initMember(MemberInit member, String name, int age, int grade) {
        member.name = name;
        member.age = age;
        member.grade = grade;
    }
}
```
  - initMember(...) 메서드를 사용해서 반복을 제거
  - 그런데 이 메서드는 대부분 MemberInit 객체의 멤버 변수를 사용
  - 이런 경우 속성과 기능을 한 곳에 두는 것이 더 나은 방법
  - 쉽게 이야기해서 MemberInit 이 자기 자신의 데이터를 변경하는 기능(메서드)을 제공하는 것이 좋음
    
