-----
### 접근 제어자 종류
-----
1. 자바는 4가지 종류의 접근 제어자를 제공
2. 접근 제어자의 종류
   - private : 모든 외부 호출을 막음
   - default (package-private): 같은 패키지안에서 호출은 허용
   - protected : 같은 패키지안에서 호출은 허용 (💡 패키지가 달라도 상속 관계의 호출은 허용)
   - public : 모든 외부 호출을 허용

3. 순서대로 private이 가장 많이 차단하고, public이 가장 많이 허용 (private → default → protected → public)
3. package-private
    - 접근 제어자를 명시하지 않으면 같은 패키지 안에서 호출을 허용하는 default 접근 제어자가 적용
    - default라는 용어는 해당 접근 제어자가 기본값으로 사용되기 때문에 붙여진 이름
    - 실제로는 package-private 이 더 정확한 표현
    - 해당 접근 제어자를 사용하는 멤버는 동일한 패키지 내의 다른 클래스에서만 접근이 가능하기 때문임
    - 참고로 두 용어를 함께 사용

4. 접근 제어자 사용 위치
   - 접근 제어자는 필드와 메서드, 생성자에 사용  
   - 추가로 클래스 레벨에도 일부 접근 제어자를 사용할 수 있음
   - 접근 제어자 예시
```java
public class Speaker { // 클래스 레벨
     private int volume; // 필드

     public Speaker(int volume) {} // 생성자

     public void volumeUp() {} // 메서드
     public void volumeDown() {}
     public void showVolume() {}
}
```
   - 접근 제어자의 핵심은 속성과 기능을 외부로부터 숨기는 것
   - private : 나의 클래스 안으로 속성과 기능을 숨길 때 사용, 외부 클래스에서 해당 기능을 호출할 수 없음
   - default : 나의 패키지 안으로 속성과 기능을 숨길 때 사용, 외부 패키지에서 해당 기능을 호출할 수 없음
   - protected : 상속 관계로 속성과 기능을 숨길 때 사용, 상속 관계가 아닌 곳에서 해당 기능을 호출할 수 없음
   - public : 기능을 숨기지 않고 어디서든 호출할 수 있게 공개
