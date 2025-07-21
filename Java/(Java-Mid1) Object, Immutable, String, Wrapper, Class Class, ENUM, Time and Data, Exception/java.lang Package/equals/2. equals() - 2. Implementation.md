-----
### equals() - 2. 구현
-----
1. UserV2 예제
   - UserV2는 id (고객번호)가 같으면 논리적으로 같은 객체로 정의
   - UserV2
```java
package lang.object.equals;

import java.util.Objects;

public class UserV2 {
    private String id;

    public UserV2(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserV2 userV2 = (UserV2) o;
        return Objects.equals(id, userV2.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
```
   - Object의 equals() 메서드를 재정의
   - UserV2의 동등성은 id(고객번호)로 비교
   - equals()는 Object 타입을 매개변수로 사용 : 따라서 객체의 특정 값을 사용하려면 다운캐스팅이 필요
   - 여기서는 현재 인스턴스(this)에 있는 id 문자열과 비교 대상으로 넘어온 객체의 id 문자열을 비교
   - UserV2에 있는 id는 String : 문자열 비교는 ==이 아니라 equals()를 사용해야 함

   - EqualsMainV2
```java
package lang.object.equals;

public class EqualsMainV2 {
    public static void main(String[] args) {
        UserV2 user1 = new UserV2("id-100");
        UserV2 user2 = new UserV2("id-100");

        System.out.println("identity = " + (user1 == user2));
        System.out.println("eqaulity = " + (user1.equals(user2)));
    }
}
```
  - 실행 결과
```
identity = false
eqaulity = true
```

<div align="center">
<img src="https://github.com/user-attachments/assets/1281cab6-4680-41a2-908f-bbd3d8060e60">
</div>

   - 동일성(Identity) : 객체의 참조가 다르므로 동일성은 다름
   - 동등성(Equality) : user1, user2는 서로 다른 객체이지만 둘다 같은 id(고객번호)를 가지고 있으므로, 따라서 동등

2. 정확한 equals() 구현
   - 정확하게 동작하려면 다음과 같이 구현해야 함
   - IntelliJ를 포함한 대부분의 IDE는 정확한 equals() 코드를 자동으로 만들어줌
   - generator 단축키: ⌘ + N (macOS) / Alt + Insert (Windows / Linux)
```java
// 변경 - 정확한 equals 구현, IDE 자동 생성
@Override
public boolean equals(Object o) {
   if (this == o) return true;
   if (o == null || getClass() != o.getClass()) return false;
   User user = (User) o;
   return Objects.equals(id, user.id);
}
```
  - 참고 : 최신 IntellJ에서는 equals 구현에서 다음 첫 줄 코드가 제거
```java
if (this == o) return true;
```
  - 이유 : 내부 분석 결과, 대부분의 실무 코드에서는 자기 자신을 비교하는 경우가 거의 없으므로, 따라서 분기 예측 미스가 더 큰 비용을 유발한다는 리포트(IDEA-357686)가 반영

3. equals() 메서드를 구현할 때 지켜야 하는 규칙
   - 반사성 (Reflexivity) : 객체는 자기 자신과 동등해야 함 (x.equals(x) 는 항상 true)
   - 대칭성 (Symmetry) : 두 객체가 서로에 대해 동일하다고 판단하면, 이는 양방향으로 동일해야 함 (x.equals(y)가 true이면 y.equals(x)도 true)
   - 추이성 (Transitivity) : 만약 한 객체가 두 번째 객체와 동일하고, 두 번째 객체가 세 번째 객체와 동일하다면, 첫번째 객체는 세 번째 객체와도 동일해야 함
   - 일관성 (Consistency) : 두 객체의 상태가 변경되지 않는 한, equals() 메서드는 항상 동일한 값을 반환해야 함
   - null에 대한 비교 : 모든 객체는 null과 비교했을 때 false를 반환해야 함
   - 실무에서는 대부분 IDE가 만들어주는 equals()를 사용

4. 정리
  - 참고로 동등성 비교가 항상 필요한 것은 아님
  - 동등성 비교가 필요한 경우에만 equals()를 재정의하면 됨
  - equals()와 hashCode()는 보통 함께 사용
