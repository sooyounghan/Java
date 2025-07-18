-----
### 정리
-----
1. final은 매우 유용한 제약
2. 만약 특정 변수의 값을 할당한 이후에 변경하지 않아야 한다면 final을 사용
   - 예를 들어서 고객의 id를 변경하면 큰 문제가 발생한다면 final로 선언하고 생성자로 값을 할당
   - 만약 어디선가 실수로 id 값을 변경한다면 컴파일러가 문제를 찾아줄 것
3. Member (/final1/ex)
```java
package final1.ex;

public class Member {
    private final String id; // final 키워드 사용
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void changeData(String id, String name) {
        // this.id = id; // 컴파일 오류 발생
        this.name = name;
    }
    
    public void print() {
        System.out.println("id = " + id + ", name = " + name);
    }
}
```
  - changeData() 메서드에서 final인 id 값 변경을 시도하면 컴파일 오류가 발생

4. MemberMain
```java
package final1.ex;

public class MemberMain {
    public static void main(String[] args) {
        Member member = new Member("myId", "kim");
        member.print();
        member.changeData("myId2", "seo");
        member.print();
    }
}
```

5. 실행 결과
```
id = myId, name = kim
id = myId, name = seo
```
