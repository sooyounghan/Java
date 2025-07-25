-----
### 불변 객체 - 예제
-----
1. 조금 더 복잡하고 의미있는 예제를 통해서 불변 객체의 사용 예를 확인
2. 변경 클래스 사용 - MemberV1
```java
package lang.immutable.address;

public class MemberV1 {
    private String name;
    private Address address;

    public MemberV1(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberV1{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
```
   - MemberV1은 변경 가능한 Address 클래스를 사용

3. MemberMainV1
```java
package lang.immutable.address;

public class MemberMainV1 {
    public static void main(String[] args) {
        Address address = new Address("서울");
        
        MemberV1 memberA = new MemberV1("회원A", address);
        MemberV1 memberB = new MemberV1("회원B", address);
        
        // 회원 A, 회원 B의 처음 주소는 모두 서울
        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
        
        // 회원 B의 주소를 부산으로 변경
        memberB.getAddress().setValue("부산");
        System.out.println("부산 -> memberB.address");
        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
    }
}
```
   - 회원A와 회원B는 둘다 서울에 살고 있음
   - 중간에 회원B 의 주소를 부산으로 변경해야 함
   - 그런데 회원A와 회원B는 같은 Address 인스턴스를 참조하고 있음
   - 회원B의 주소를 부산으로 변경하는 순간 회원A의 주소도 부산으로 변경됨
   - 실행 결과
```
memberA = MemberV1{name='회원A', address=Address{value='서울'}}
memberB = MemberV1{name='회원B', address=Address{value='서울'}}
부산 -> memberB.address
memberA = MemberV1{name='회원A', address=Address{value='부산'}}
memberB = MemberV1{name='회원B', address=Address{value='부산'}}
```
   - 사이드 이펙트가 발생해서 회원B 뿐만 아니라 회원A의 주소도 부산으로 변경

4. 불변 클래스 사용 - MemberV2
```java
package lang.immutable.address;

public class MemberV2 {
    private String name;
    private ImmutableAddress address;

    public MemberV2(String name, ImmutableAddress address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public ImmutableAddress getAddress() {
        return address;
    }

    public void setAddress(ImmutableAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberV2{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
```
   - MemberV2 는 주소를 변경할 수 없는, 불변인 ImmutableAddress 를 사용

   - MemberMainV2
```java
package lang.immutable.address;

public class MemberMainV2 {
    public static void main(String[] args) {
        ImmutableAddress address = new ImmutableAddress("서울");

        MemberV2 memberA = new MemberV2("회원A", address);
        MemberV2 memberB = new MemberV2("회원B", address);

        // 회원 A, 회원 B의 처음 주소는 모두 서울
        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);

        // 회원 B의 주소를 부산으로 변경
        // memberB.getAddress().setValue("부산"); // 컴파일 오류
        memberB.setAddress(new ImmutableAddress("부산"));
        System.out.println("부산 -> memberB.address");
        System.out.println("memberA = " + memberA);
        System.out.println("memberB = " + memberB);
    }
}
```
   - 회원B의 주소를 중간에 부산으로 변경하려고 시도
   - 하지만 ImmutableAddress에는 값을 변경할 수 있는 메서드가 없으므로, 따라서 컴파일 오류가 발생
   - 결국 memberB.setAddress(new ImmutableAddress("부산"))와 같이 새로운 주소 객체를 만들어서 전달

   - 실행 결과
```
memberA = MemberV2{name='회원A', address=ImmutableAddress{value='서울'}}
memberB = MemberV2{name='회원B', address=ImmutableAddress{value='서울'}}
부산 -> memberB.address
memberA = MemberV2{name='회원A', address=ImmutableAddress{value='서울'}}
memberB = MemberV2{name='회원B', address=ImmutableAddress{value='부산'}}
```
   - 사이드 이펙트가 발생하지 않고, 회원A 는 기존 주소를 그대로 유지
