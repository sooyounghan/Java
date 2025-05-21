-----
### 문제와 풀이 3 - Stack
-----
1. 문제 1 - 간단한 히스토리 확인
    - 스택에 push() 를 통해서 다음 데이터를 순서대로 입력
      + "youtube.com"
      + "google.com"
      + "facebook.com"
        
    - 스택에 pop() 을 통해서 데이터를 꺼내고, 꺼낸 순서대로 출력해라.
      + "facebook.com"
      + "google.com"
      + "youtube.com"
        
    - 입력 순서와 반대로 출력되는 것을 확인할 수 있음가장 마지막에 입력한 데이터가 가장 먼저 출력
    - SimpleHistoryTest (/collection/deque/test/stack)
```java
package collection.deque.test.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleHistoryTest {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();

        // 코드 작성
        stack.push("youtube.com");
        stack.push("google.com");
        stack.push("facebook.com");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
```

   - Stack 을 사용해도 되지만 Deque 인터페이스에 ArrayDeque 구현체를 사용하는 것이 성능상 더 나은 선택
   - 실행 결과
```
facebook.com
google.com
youtube.com
```

2. 문제 2 - 브라우저 히스토리 관리
  - BrowserHistoryTest와 실행 결과를 참고해서 BrowserHistory 클래스를 완성
  - 브라우저의 방문 기록 관리 기능을 개발
    + visitPage() : 특정 페이지 방문
    + goBack() : 뒤로가기

  - 뒤로가기는 가장 나중에 넣은 데이터가 먼저 나오므로, 스택 구조 고려
  - BrowserHistoryTest
```java
package collection.deque.test.stack;

public class BrowserHistoryTest {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();

        // 사용자가 웹페이지를 방문하는 시나리오
        browser.visitPage("youtube.com");
        browser.visitPage("google.com");
        browser.visitPage("facebook.com");
        
        // 뒤로 가기 기능을 사용하는 시나리오
        String currentPage1 = browser.goBack();
        System.out.println("currentPage1 = " + currentPage1);

        String currentPage2 = browser.goBack();
        System.out.println("currentPage2 = " + currentPage2);
    }
}
```
  - 실행 결과
```
방문: youtube.com
방문: google.com
방문: facebook.com
뒤로 가기: google.com
currentPage1 = google.com
뒤로 가기: youtube.com
currentPage2 = youtube.com
```

  - BrowserHistory
```java
package collection.deque.test.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {
    private Deque<String> history = new ArrayDeque<>();
    private String currentPage = null;

    public void visitPage(String url) {
        if(currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;

        System.out.println("방문 : " + url);
    }

    public String goBack() {
        if(!history.isEmpty()) {
            currentPage = history.pop();
            System.out.println("뒤로 가기 : " + currentPage);
            return currentPage;
        }
        return null;
    }
}
```
