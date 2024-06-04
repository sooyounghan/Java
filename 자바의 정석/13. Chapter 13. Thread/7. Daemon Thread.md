-----
### 데몬 쓰레드 (Daemon Thread)
-----
1. 다른 일반 쓰레드(데몬 쓰레드가 아닌 쓰레드)의 작업을 돕는 보조적 역할을 수행하는 쓰레드
2. 일반 쓰레드가 모두 종료되면, 데몬 쓰레드는 강제적으로 자동 종료
   - 데몬 쓰레드는 일반 쓰레드의 보조역할을 수행하므로 일반 쓰레드가 모두 종료되고 나면 데몬 쓰레드의 존재가 없기 떄문임
3. 예) Garbage Collector, 워드프로세서 자동 저장, 화면 자동 갱신 등
4. 무한루프와 조건문을 이용해 실행 후 대기하고 있다가 특정 조건이 만족되면 작업을 수행하면 다시 대기하도록 작성
5. 쓰레드를 생성한 다음 실행하기 전 setDaemon(true)를 호출하기만 하면 됨
6. 데몬 쓰레드가 생성한 쓰레드는 자동적으로 데몬 쓰레드가 됨

```java
boolean isDaemon() // 쓰레드가 데몬 쓰레드인지 확인. 데몬 쓰레드이면, true
void setDaemon(boolean on) // 쓰레드를 데몬 쓰레드 또는 사용자 쓰레드로 변경, 매개변수 on의 값을 true로 설정하면 데몬 쓰레드
```

```java
 class ThreadEx10 implements Runnable  {
	static boolean autoSave = false;

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadEx10());
		t.setDaemon(true);		// 이 부분이 없으면 종료되지 않음
		t.start();

		for(int i=1; i <= 10; i++) {
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
			System.out.println(i);
			
			if(i==5)
				autoSave = true;
		}

		System.out.println("프로그램을 종료합니다.");
	}

	public void run() {
		while(true) {
			try { 
				Thread.sleep(3 * 1000);	// 3초마다
			} catch(InterruptedException e) {}	

			// autoSave의 값이 true이면, autoSave() 호출
			if(autoSave) {
				autoSave();
			}
		}
	}

	public void autoSave() {
		System.out.println("작업파일이 자동저장되었습니다.");
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/07c9fed3-aea3-4e4f-8520-a00918287cc2">
</div>

  - 3초마다 변수 autoSave의 값을 확인해 그 값이 true이면, autoSave() 호출하는 일을 무한 반복
  - 만약, 데몬 쓰레드로 이 쓰레드를 설정하지 않았으면, 이 프로그램은 강제 종료되지 않음
  - setDaemon() 메서드는 반드시 start() 호출하기 전에 실행되어야 하며, 그렇지 않으면 IllegalThreadStateException 발생

```java
 import java.util.*;

class ThreadEx11 {
	public static void main(String args[]) throws Exception {
		ThreadEx11_1 t1 = new ThreadEx11_1("Thread1");
		ThreadEx11_2 t2 = new ThreadEx11_2("Thread2");
		t1.start();
		t2.start();
	}
}

class ThreadEx11_1 extends Thread {
	ThreadEx11_1(String name) {
		super(name);
	}

	public void run() {
		try {
			sleep(5 * 1000);	// 5초 동안 기다림
		} catch(InterruptedException e) {}
	}
}

class ThreadEx11_2 extends Thread {
	ThreadEx11_2(String name) {
		super(name);
	}

	public void run() {
		Map map = getAllStackTraces();
		Iterator it = map.keySet().iterator();

		int x=0;
		while(it.hasNext()) {
			Object obj = it.next();
			Thread t = (Thread)obj;
			StackTraceElement[] ste = (StackTraceElement[])(map.get(obj));

			System.out.println( "["+ ++x + "] name : " + t.getName() 
                                       + ", group : "  + t.getThreadGroup().getName() 
                                       + ", daemon : " + t.isDaemon());

			for(int i=0; i < ste.length; i++) {
				System.out.println(ste[i]);
			}

			System.out.println();
		}
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/9079f520-0cfa-42c2-b6cc-2477a3954d49">
</div>

  - getAllStackTraces() : 실행 중 또는 대기 상태, 즉 작업이 완료되지 않은 모든 쓰레드의 호출 스택 출력
  - 새로 생성한 Thread1, Thread2를 포함해 총 9개의 Thread가 실행 중 또는 대기 상태에 존재
  - 프로그램을 실행하면 JVM은 Garbage Collection, Event 처리, 그래픽 처리와 같은 프로그램이 실행되는 데 필요한 보조작업을 수행하는 데몬 쓰레드들 자동적 생성
  - 이들은 'system 쓰레드 그룹' 또는 'main 쓰레드 그룹'에 속함
  - GUI를 가진 프로그램을 실행하는 경우에는 이벤트와 그래픽처리를 위해 더 많은 데몬 쓰레드 생성
  - main 쓰레드는 이미 종료되었으므로 결과에 포함되지 않음
