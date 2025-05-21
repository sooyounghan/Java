-----
### 문제와 풀이 4 - Queue
-----
1. 문제1 - 프린터 대기
  - 프린터에 여러 문서의 출력을 요청하면 한번에 모든 문서를 출력할 수 없으므로, 따라서 순서대로 출력해야 함
  - 문서가 대기할 수 있도록 큐 구조를 사용
  -  "doc1", "doc2", "doc3" 문서를 순서대로 입력하고, 입력된 순서대로 출력
  - 실행 결과를 참고
  - 문제 설명 : PrinterQueueTest (/collection/deque/test/queue)
```java
package collection.deque.test.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class PrinterQueueTest {
    public static void main(String[] args) {
        Queue<String> printQueue = new ArrayDeque<>();

        // 코드 작성
        printQueue.offer("doc1");
        printQueue.offer("doc2");
        printQueue.offer("doc3");

        System.out.println("출력 : " + printQueue.poll());
        System.out.println("출력 : " + printQueue.poll());
        System.out.println("출력 : " + printQueue.poll());
    }
}
```

  - 실행 결과
```
출력 : doc1
출력 : doc2
출력 : doc3
```

2. 문제 2 - 작업 예약
   - 서비스를 운영중인데, 낮 시간에는 사용자가 많아서 서버에서 무거운 작업을 하기 부담스러움
   - 무거운 작업을 예약해두고 사용자가 없는 새벽에 실행하도록 개발
   - 다양한 무거운 작업을 새벽에 실행한다고 가정
   - 작업은 자유롭게 구현하고 자유롭게 예약할 수 있어야 함
   - 다음 예제 코드와 실행 결과를 참고해서 TaskScheduler 클래스를 완성
   - Task
```java
package collection.deque.test.queue;

public interface Task {
    void execute();
}
```

  - CompressionTask
```java
package collection.deque.test.queue;

public class CompressionTask implements Task {
    @Override
    public void execute() {
        System.out.println("데이터 압축...");
    }
}
```

  - BackupTest
```java
package collection.deque.test.queue;

public class BackupTask implements Task {
    @Override
    public void execute() {
        System.out.println("자료 백업...");
    }
}
```

  - CleanTask
```java
package collection.deque.test.queue;

public class CleanTask implements Task {
    @Override
    public void execute() {
        System.out.println("사용하지 않는 자원 정리...");
    }
}
```

  - SchedulerTest
```java
package collection.deque.test.queue;

public class SchedulerTest {
    public static void main(String[] args) {
        //낮에 작업을 저장
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.addTask(new CompressionTask());
        scheduler.addTask(new BackupTask());
        scheduler.addTask(new CleanTask());

        //새벽 시간에 실행
        System.out.println("작업 시작");
        run(scheduler);
        System.out.println("작업 완료");
    }

    private static void run(TaskScheduler scheduler) {
        while (scheduler.getRemainingTasks() > 0) {
            scheduler.processNextTask();
        }
    }
}
```

  - 실행 결과
```
작업 시작
데이터 압축...
자료 백업...
사용하지 않는 자원 정리...
작업 완료
```

  - TaskScheduler
```java
package collection.deque.test.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class TaskScheduler {
    private Queue<Task> tasks = new ArrayDeque<>();

    // 코드 작성
    public void addTask(Task task) {
        tasks.offer(task);
    }

    public int getRemainingTasks() {
        return tasks.size();
    }

    public void processNextTask() {
        Task task = tasks.poll();
        
        if(task != null) {
            task.execute();
        }
    }
}
```
