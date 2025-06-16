-----
### 스택과 큐 자료 구조
-----
1. 스택 구조
   - 다음과 같은 1, 2, 3 이름표가 붙은 블럭이 있다고 가정
<div align="center">
<img src="https://github.com/user-attachments/assets/dd303b15-f5fa-4c65-87d8-914d45befa15">
</div>

   - 이 블럭을 다음과 같이 생긴 통에 넣는다고 생각
   - 위쪽만 열려있기 때문에 위쪽으로 블럭을 넣고, 위쪽으로 블럭을 빼야함
   - 쉽게 이야기해서 넣는 곳과 빼는 곳이 같음

<div align="center">
<img src="https://github.com/user-attachments/assets/e2b1f5c7-49df-4fb6-b09d-9f661f6e53f9">
</div>

   - 블럭은 1 → 2 → 3 순서대로 넣음
<div align="center">
<img src="https://github.com/user-attachments/assets/d59158ee-07c2-4603-a072-8e3599138670">
</div>


   - 블럭을 빼려면 위에서부터 순서대로 빼야함 : 3 → 2 → 1 순으로 뺄 수 있음
   - 정리 : 1(넣기) → 2(넣기) → 3(넣기) → 3(빼기) → 2(빼기) → 1(빼기)

   - 후입 선출(LIFO, Last In First Out) :  여기서 가장 마지막에 넣은 3번이 가장 먼저 나오는데, 이렇게 나중에 넣은 것이 가장 먼저 나오는 것을 후입 선출이라 하고, 이런 자료 구조를 스택
   - 선입 선출(FIFO, First In First Out) : 후입 선출과 반대로 가장 먼저 넣은 것이 가장 먼저 나오는 것을 선입 선출이라 한다. 이런 자료 구조를 큐(Queue)

2. 큐 자료 구조
<div align="center">
<img src="https://github.com/user-attachments/assets/22542d33-16e8-4dca-8855-6e6da510baf1">
</div>

  - 정리 : 1(넣기) → 2(넣기) → 3(넣기) → 1(빼기) → 2(빼기) → 3(빼기)
  - 이런 자료 구조는 각자 필요한 영역이 있음 : 예를 들어서 선착순 이벤트를 하는데 고객이 대기해야 한다면 큐 자료 구조를 사용해야 함
