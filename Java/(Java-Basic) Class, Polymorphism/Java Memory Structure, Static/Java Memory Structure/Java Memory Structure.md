-----
### 자바 메모리 구조
-----
1. 자바 메모리 구조 - 비유
<div align="center">
<img src="https://github.com/user-attachments/assets/6707b010-e54e-4103-a5ff-680c91ba2d19">
</div>

   - 자바의 메모리 구조는 크게 메서드 영역, 스택 영역, 힙 영역 3개로 나눌 수 있음
     + 메서드 영역 : 클래스 정보를 보관 (이 클래스 정보가 붕어빵 틀)
     + 스택 영역 : 실제 프로그램이 실행되는 영역 (메서드를 실행할 때 마다 하나씩 쌓임)
     + 힙 영역 : 객체(인스턴스)가 생성되는 영역 (new 명령어를 사용하면 이 영역을 사용, 쉽게 이야기해서 붕어빵 틀로부터 생성된 붕어빵이 존재하는 공간. 참고로 배열도 이 영역에 생성)

2. 실제 구조
<div align="center">
<img src="https://github.com/user-attachments/assets/709c15ef-e924-4099-9942-9a6090f82be5">
</div>

   - 메서드 영역(Method Area) : 메서드 영역은 프로그램을 실행하는데 필요한 공통 데이터를 관리
     + 이 영역은 프로그램의 모든 영역에서 공유
     + 클래스 정보 : 클래스의 실행 코드(바이트 코드), 필드, 메서드와 생성자 코드등 모든 실행 코드가 존재
       * static 영역 : static 변수들을 보관
       * 런타임 상수 풀 : 프로그램을 실행하는데 필요한 공통 리터럴 상수를 보관 (예를 들어서 프로그램에 "hello" 라는 리터럴 문자가 있으면 이런 문자를 공통으로 묶어서 관리하며, 이 외에도 프로그램을 효율적으로 관리하기 위한 상수들을 관리 (참고로 문자열을 다루는 문자열 풀은 자바 7부터 힙 영역으로 이동))

   - 스택 영역(Stack Area) : 자바 실행 시, 하나의 실행 스택이 생성되며, 각 스택 프레임은 지역 변수, 중간 연산 결과, 메서드 호출 정보 등을 포함
     + 스택 프레임 : 스택 영역에 쌓이는 네모 박스가 하나의 스택 프레임
       * 메서드를 호출할 때 마다 하나의 스택 프레임이 쌓이고, 메서드가 종료되면 해당 스택 프레임이 제거

   - 힙 영역(Heap Area) : 객체(인스턴스)와 배열이 생성되는 영역
     + 가비지 컬렉션(GC)이 이루어지는 주요 영역이며, 더 이상 참조되지 않는 객체는 GC에 의해 제거

3. 참고
   - 스택 영역은 더 정확히는 각 쓰레드별로 하나의 실행 스택이 생성
   - 따라서 쓰레드 수 만큼 스택 영역이 생성
   - 지금은 쓰레드를 1개만 사용하므로 스택 영역도 하나
  
4. 메서드 코드는 메서드 영역
<div align="center">
<img src="https://github.com/user-attachments/assets/beeed429-cd54-4d1e-b3c2-2e93bd917d23">
</div>

   - 자바에서 특정 클래스로 100개의 인스턴스를 생성하면, 힙 메모리에 100개의 인스턴스가 생김
   - 각각의 인스턴스는 내부에 변수와 메서드를 가짐
   - 같은 클래스로 부터 생성된 객체라도, 인스턴스 내부의 변수 값은 서로 다를 수 있지만, 메서드는 공통된 코드를 공유
   - 따라서 객체가 생성될 때, 인스턴스 변수에는 메모리가 할당되지만, 메서드에 대한 새로운 메모리 할당은 없음
   - 메서드는 메서드 영역에서 공통으로 관리되고 실행
   - 정리하면 인스턴스의 메서드를 호출하면 실제로는 메서드 영역에 있는 코드를 불러서 수행

