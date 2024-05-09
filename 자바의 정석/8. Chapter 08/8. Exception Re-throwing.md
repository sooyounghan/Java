-----
### 에외 되던지기 (Exception Re-throwing)
-----
1. 한 메서드에서 발생할 수 있는 예외가 여럿인 경우, 일부는 try-catch문을 통해서 메서드 내에서 자체적으로 처리, 나머지는 선언부에 지정하여 호출한 메서드에서 처리하도록 함으로, 양쪽에서 나눠서 처리할 수 있음
2. 단 하나의 예외에 대해서도 예외가 발생한 메서드와 호출한 메서드, 양쪽에서 처리 가능
3. 예외를 처리한 후에 인위적으로 다시 발생시키는 방법이 있는데, 이를 예외 되던지기(Exception Re-Throwing)
4. 우선, 예외가 발생할 가능성이 있는 메서드에서 try-catch문을 사용해 예외를 처리해주고, catch문에서 필요한 작업을 행한 후 throw문을 사용해 예외를 다시 발생
5. 다시 발생한 예외는 이 메서드를 호출한 메서드에게 전달되고, 호출한 메서드의 try-catch문에서 예외를 또다시 처리
6. 이러한 방법은 하나의 예외에 대해 예외가 발생한 메서드와 이를 호출한 메서드 양쪽 모두에서 처리해줘야 할 작업이 있을 때 사용
7. 주의할 점
   - 예외가 발생할 메서드에서는 try-catch문을 사용해 예외처리를 해줌
   - 메서드의 선언부에 발생할 예외를 throws로 지정해줘야 함

```java
class ExceptionEx17 {
	public static void main(String[] args) 
	{
		try  {
			method1();		
		} catch (Exception e)	{
			System.out.println("main메서드에서 예외가 처리되었습니다.");
		}
	}	// main

	static void method1() throws Exception {
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("method1메서드에서 예외가 처리되었습니다.");
			throw e;			// 다시 예외를 발생시킴
		}
	}	// method1
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/591801f1-2bf2-415a-bade-24e911ec1919">
</div>

8. 💡 반환값이 있는 return문의 경우에는 catch블럭에도 return문이 있어야함 (예외가 발생했을 경우에도 값을 반환해야하기 때문임)
```java
static int method1() {
    try {
        System.out.println("method1()이 호출되었습니다.");
        return 0;
    } catch(Exception e) {
        e.printStackTrace();
        return 1; // catch 블럭 내에도 return 문 필요
    } finally {
        System.out.println("method1()의 finally 블럭이 실행되었습니다.");
    }
}
```

9. 또는, catch 블럭에서 예외 되던지기를 해서 메서드를 예외를 전달하면, return문은 없어도 됨
    - 그래서, 검증에서도 assert문 대신 AssertError를 생성해서 던짐
```java
static int method1() throws Exception { // 예외 선언
    try {
        System.out.println("method1()이 호출되었습니다.");
        return 0;
    } catch(Exception e) {
        e.printStackTrace();
        throw new Exception(); // return문 대신 예외를 호출한 메서드로 전달
    } finally {
        System.out.println("method1()의 finally 블럭이 실행되었습니다.");
    }
}
```

10. finally블럭 내에도 return문 사용 가능하며, try블럭이나 catch블럭의 return문 다음에 수행
    - 💡 최종적으로는 finally 블럭 내 return문의 값이 반환

