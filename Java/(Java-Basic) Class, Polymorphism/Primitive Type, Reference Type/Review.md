-----
### 💡 정리
-----
1. 대원칙 : 자바는 항상 변수의 값을 복사해서 대입
   - 자바에서 변수에 값을 대입하는 것은 변수에 들어 있는 값을 복사해서 대입하는 것
   - 기본형, 참조형 모두 항상 변수에 있는 값을 복사해서 대입
     + 기본형 : 변수에 들어 있는 실제 사용하는 값을 복사해서 대입
     + 참조형 : 변수에 들어 있는 참조값을 복사해서 대입

    - 기본형이든 참조형이든 변수의 값을 대입하는 방식은 같음
    - 하지만 기본형과 참조형에 따라 동작하는 방식이 달라짐

2. 기본형 vs 참조형 - 기본
    - 자바의 데이터 타입을 가장 크게 보면 기본형과 참조형으로 나눌 수 있음
    - 기본형을 제외한 나머지 변수는 모두 참조형
    - 클래스와 배열을 다루는 변수는 참조형
    - 기본형 변수는 값을 직접 저장하지만, 참조형 변수는 참조(주소)를 저장
    - 기본형 변수는 산술 연산을 수행할 수 있지만, 참조형 변수는 산술 연산을 수행할 수 없음
    - 기본형 변수는 null을 할당할 수 없지만, 참조형 변수는 null을 할당할 수 있음
     
3. 기본형 vs 참조형 - 대입
   - 기본형과 참조형 모두 대입시 변수 안에 있는 값을 읽고 복사해서 전달
   - 💡 기본형은 사용하는 값을 복사해서 전달하고, 참조형은 참조값을 복사해서 전달 (실제 인스턴스가 복사되는 것이 아님)
   - 💡 인스턴스를 가리키는 참조값을 복사해서 전달하는 것 : 따라서 하나의 인스턴스를 여러곳에서 참조할 수 있음

4. 기본형 vs 참조형 - 메서드 호출
   - 메서드 호출시 기본형은 메서드 내부에서 매개변수(파라미터)의 값을 변경해도 호출자의 변수 값에는 영향이 없음
   - 💡 메서드 호출시 참조형은 메서드 내부에서 매개변수(파라미터)로 전달된 객체의 멤버 변수를 변경하면, 호출자의 객체도 변경
