-----
### 문제와 풀이
-----
1. 문제 1 : 다중 메시지 발송
   - 한번에 여러 곳에 메시지를 발송하는 프로그램을 개발
   - 다음 코드를 참고해서 클래스를 완성
   - 요구사항
     + 다형성을 활용
     + Sender 인터페이스를 사용
     + EmailSender, SmsSender, FaceBookSender를 구현

   - SendMain (/poly/ex/sender)
```java
package poly.ex.sender;

public class SendMain {
    public static void main(String[] args) {
        Sender[] senders = {new EmailSender(), new SmsSender(), new FaceBookSender()};

        for (Sender sender : senders) {
            sender.sendMessage("환영합니다!");
        }
    }
}
```

   - 실행 결과
```
메일을 발송합니다 : 환영합니다!
SMS을 발송합니다 : 환영합니다!
페이스북에 발송합니다 : 환영합니다!
```

   - Sender
```java
package poly.ex.sender;

public interface Sender {
    public void sendMessage(String message);
}
```

   - EmailSender
```java
package poly.ex.sender;

public class EmailSender implements Sender {
    @Override
    public void sendMessage(String message) {
        System.out.println("메일을 발송합니다 : " + message);
    }
}
```

   - SmsSender
```java
package poly.ex.sender;

public class SmsSender implements Sender {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS을 발송합니다 : " + message);
    }
}
```

   - FaceBookSender
```java
package poly.ex.sender;

public class FaceBookSender implements Sender {
    @Override
    public void sendMessage(String message) {
        System.out.println("페이스북에 발송합니다 : " + message);
    }
}
```

2. 문제2 : 결제 시스템 개발
   - 현재 2가지 결제 수단을 지원
   - 앞으로 5개의 결제 수단을 추가로 지원할 예정
   - 새로운 결제수단을 쉽게 추가할 수 있도록, 기존 코드를 리펙토링할 것
   - 요구사항
       + OCP 원칙을 지킬 것
       + 메서드를 포함한 모든 코드를 변경 가능, 클래스나 인터페이스를 추가 가능
       + 단, 프로그램을 실행하는 PayMain0 코드는 변경하지 않고, 그대로 유지해야 함
       + 리펙토링 후에도 실행 결과는 기존과 같아야 함

   - KakaoPay (/poly/ex/pay0)
```java
package poly.ex.pay0;

public class KakaoPay {
    public boolean pay(int amount) {
        System.out.println("카카오페이 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
```

   - NaverPay
```java
package poly.ex.pay0;

public class NaverPay {
    public boolean pay(int amount) {
        System.out.println("네이버페이 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
```

  - PayService
```java
package poly.ex.pay0;

public class PayService {
    public void processPay(String option, int amount) {
        boolean result;
        
        System.out.println("결제를 시작합니다. : option = " + option + ", amount = " + amount);
        
        if(option.equals("kakao")) {
            KakaoPay kakaoPay = new KakaoPay();
            result = kakaoPay.pay(amount);
        } else if (option.equals("naver")) {
            NaverPay naverPay = new NaverPay();
            result = naverPay.pay(amount);
        } else {
            System.out.println("결제 수단이 없습니다.");
            result = false;
        }
        
        if(result) {
            System.out.println("결제가 성공했습니다.");
        } else {
            System.out.println("결제가 실패했습니다.");
        }
    }
}
```

   - PayMain0
```java
package poly.ex.pay0;

public class PayMain0 {
    public static void main(String[] args) {
        PayService payService = new PayService();
        
        // Kakao 결제
        String payOption1 = "kakao";
        int amount1 = 5000;
        payService.processPay(payOption1, amount1);
        
        // Naver 결제
        String payOption2 = "naver";
        int amount2 = 10000;
        payService.processPay(payOption2, amount2);
        
        // 잘못된 결제 수단 선택
        String payOption3 = "bad";
        int amount3 = 15000;
        payService.processPay(payOption3, amount3);
    }
}
```
   - 실행 결과
```
결제를 시작합니다. : option = kakao, amount = 5000
카카오페이 시스템과 연결합니다.
5000원 결제를 시도합니다.
결제가 성공했습니다.

결제를 시작합니다. : option = naver, amount = 10000
네이버페이 시스템과 연결합니다.
10000원 결제를 시도합니다.
결제가 성공했습니다.

결제를 시작합니다. : option = bad, amount = 15000
결제 수단이 없습니다.
결제가 실패했습니다.
```

   - 리팩토링 - Pay
```java
package poly.ex.pay0;

public interface Pay {
    boolean pay(int amount);
}
```

  - KakaoPay
```java
package poly.ex.pay0;

public class KakaoPay implements Pay {
    @Override
    public boolean pay(int amount) {
        System.out.println("카카오페이 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
```

  - NaverPay
```java
package poly.ex.pay0;

public class NaverPay implements Pay {
    @Override
    public boolean pay(int amount) {
        System.out.println("네이버페이 시스템과 연결합니다.");
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
```

   - DefaultPay
```java
package poly.ex.pay0;

public class DefaultPay implements Pay {
    @Override
    public boolean pay(int amount) {
        System.out.println("결제 수단이 없습니다.");
        return false;
    }
}
```
   - 결제 수단을 찾지 못했을 때 사용하며, DefaultPay 사용하면 null 체크를 피할 수 있음

   - PayStore
```java
package poly.ex.pay0;

public abstract class PayStore {
    // 결제 수단 추가 시 변하는 부분
    public static Pay findPay(String option) {
        if(option.equals("kakao")) {
            return new KakaoPay();
        } else if(option.equals("naver")) {
            return new NaverPay();
        } else {
            return new DefaultPay();
        }
    }
}
```
   - 결제 수단 이름으로 실제 결제 수단 구현체를 찾음
   - static 으로 기능을 제공 : 추상 클래스로 선언해서 객체 생성을 막음
   - 결제 수단을 찾지 못했을 때 null 대신에 항상 실패하는 결제 수단을 사용

   - PayService
```java
package poly.ex.pay0;

public class PayService {
    public void processPay(String option, int amount) {
        System.out.println("결제를 시작합니다. : option = " + option + ", amount = " + amount);

        Pay pay = PayStore.findPay(option);
        boolean result = pay.pay(amount);

        if(result) {
            System.out.println("결제가 성공했습니다.");
        } else {
            System.out.println("결제가 실패했습니다.");
        }
    }
}
```

   - PayService는 구체적인 결제 수단이 아니라 Pay에 의존
   - 따라서 결제수단을 추가해도 PayService의 코드에는 변경이 없음

   - PayMain1
```java
package poly.ex.pay0;

public class PayMain1 {
    public static void main(String[] args) {
        PayService payService = new PayService();

        // Kakao 결제
        String payOption1 = "kakao";
        int amount1 = 5000;
        payService.processPay(payOption1, amount1);

        // Naver 결제
        String payOption2 = "naver";
        int amount2 = 10000;
        payService.processPay(payOption2, amount2);

        // 잘못된 결제 수단 선택
        String payOption3 = "bad";
        int amount3 = 15000;
        payService.processPay(payOption3, amount3);
    }
}
```
   - 실행 결과
```
결제를 시작합니다. : option = kakao, amount = 5000
카카오페이 시스템과 연결합니다.
5000원 결제를 시도합니다.
결제가 성공했습니다.

결제를 시작합니다. : option = naver, amount = 10000
네이버페이 시스템과 연결합니다.
10000원 결제를 시도합니다.
결제가 성공했습니다.

결제를 시작합니다. : option = bad, amount = 15000
결제 수단이 없습니다.
결제가 실패했습니다.
```

3. 문제 3 : 결제 시스템 개발 - 사용자 입력
   - 기존 결제 시스템이 사용자 입력을 받도록 수정
```
결제 수단을 입력하세요:kakao
결제 금액을 입력하세요:5000
결제를 시작합니다: option=kakao, amount=5000
카카오페이 시스템과 연결합니다.
5000원 결제를 시도합니다.
결제가 성공했습니다.
결제 수단을 입력하세요:exit
프로그램을 종료합니다.
```

   - PayMain2
```java
package poly.ex.pay0;

import java.util.Scanner;

public class PayMain2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayService payService = new PayService();
        
        while(true) {
            System.out.println("결제 수단을 입력하세요 : ");
            String payOption = scanner.nextLine();
            
            if(payOption.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            System.out.println("결제 금액을 입력하세요 : "); 
            int amount = scanner.nextInt();
            scanner.nextLine();
            
            payService.processPay(payOption, amount);
        }
    }
}
```
