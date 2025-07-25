-----
### 자바 예외 처리 2 - 예외 기본 규칙
-----
1. 예외가 발생하면 잡아서 처리하거나, 처리할 수 없으면 밖으로 던져야 함
2. 예외 처리
<div align="center">
<img src="https://github.com/user-attachments/assets/b081b953-fcbf-4d22-99f2-531dd050883a">
</div>

   - Main은 Service를 호출
   - Service는 Client를 호출
   - Client에서 예외가 발생
   - Client에서 예외를 처리하지 못하고 밖으로 던짐
     + 여기서 Client의 밖은 Client를 호출한 Service를 뜻함
   - Service에 예외가 전달
     + Service에서 예외를 처리
     + 이후에는 애플리케이션 로직이 정상 흐름으로 동작
   - 정상 흐름을 반환

3. 예외 던짐
<div align="center">
<img src="https://github.com/user-attachments/assets/329a5a46-3b97-42e2-8f69-f2d1d2bfbbb5">
</div>

   - 예외를 처리하지 못하면 자신을 호출한 곳으로 예외를 던져야 함
   - 예외에 대해서는 2가지 기본 규칙
     + 예외는 잡아서 처리하거나 밖으로 던져야 함
     + 예외를 잡거나 던질 때 지정한 예외뿐만 아니라 그 예외의 자식들도 함께 처리할 수 있음
       * 예를 들어서 Exception을 catch 로 잡으면 그 하위 예외들도 모두 잡을 수 있음
       * 예를 들어서 Exception을 throws 로 던지면 그 하위 예외들도 모두 던질 수 있음

4. 참고
   - 예외를 처리하지 못하고 계속 던지면, 즉, 자바 main() 밖으로 예외를 던지면 예외 로그를 출력하면서 시스템이 종료
