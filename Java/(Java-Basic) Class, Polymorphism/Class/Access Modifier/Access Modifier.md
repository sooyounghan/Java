-----
### 접근 제어자 이해 1
-----
1. 자바는 public, private 같은 접근 제어자(Access Modifier)를 제공
2. 접근 제어자를 사용하면 해당 클래스 외부에서 특정 필드나 메서드에 접근하는 것을 허용하거나 제한 가능
3. 접근 제어자가 필요한 이유
   - 스피커에 들어가는 소프트웨어를 개발
   - 스피커의 음량은 절대로 100을 넘으면 안된다는 요구사항 존재 (100을 넘어가면 스피커의 부품들이 고장)
   - 스피커 객체를 생성
   - 스피커는 음량을 높이고, 내리고, 현재 음량을 확인할 수 있는 단순한 기능을 제공
   - 요구사항 대로 스피커의 음량은 100까지만 증가할 수 있으며, 절대 100을 넘어가면 안 됨
   - Speaker (/acces)
```java
package access;

public class Speaker {
    int volume;
    
    Speaker(int volume) {
        this.volume = volume;
    }
    
    void volumeUp() {
        if(volume >= 100) {
            System.out.println("음량을 증가할 수 없습니다. 최대 음량입니다.");
        } else {
            volume += 10;
            System.out.println("음량을 10 증가합니다.");
        }
    }
    
    void volumeDown() {
        volume -= 10;
        System.out.println("volumeDown() 호출");
    }
    
    void showVolume() {
        System.out.println("현재 음량 : " + volume);
    }
}
```
   - 생성자를 통해 초기 음량 값을 지정할 수 있음
   - volumeUp() 메서드 : 음량을 한번에 10씩 증가 (단, 음량이 100을 넘게되면 더는 음량을 증가하지 않음)
   - SpeakerMain
```java
package access;

public class SpeakerMain {
    public static void main(String[] args) {
        Speaker speaker = new Speaker(90);

        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();
    }
}
```
  - 실행 결과
```
현재 음량: 90
음량을 10 증가합니다.
현재 음량: 100
음량을 증가할 수 없습니다. 최대 음량입니다.
현재 음량: 100
```
   - 초기 음량 값을 90으로 지정
   - 그리고 음량을 높이는 메서드를 여러번 호출 : 기대한 대로 음량은 100을 넘지 않음
  
   - Speaker 클래스를 보니 volume 필드를 직접 사용할 수 있었음
   - volume 필드의 값을 200으로 설정하고 이 코드를 실행한 순간 스피커의 부품들에 과부하가 걸리면서 폭발한다고 가정
   - SpeakerMain - 필드 직접 접근 코드 추가
```java
package access;

public class SpeakerMain {
    public static void main(String[] args) {
        Speaker speaker = new Speaker(90);

        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();

        speaker.volumeUp();
        speaker.showVolume();

        // 필드엥 직접 저근
        System.out.println("volume 필드 직접 접근 수정");
        speaker.volume = 200;
        speaker.showVolume();
    }
}
```
  - 실행 결과
```
현재 음량 : 90
음량을 10 증가합니다.
현재 음량 : 100
음량을 증가할 수 없습니다. 최대 음량입니다.
현재 음량 : 100
volume 필드 직접 접근 수정
현재 음량 : 200
```

<div align="center">
<img src="https://github.com/user-attachments/assets/cecdaa18-e2b4-4563-97ae-fafffcce1f52">
</div>
  
  - Speaker 객체를 사용하는 사용자는 Speaker의 volume 필드와 메서드에 모두 접근할 수 있음
  - 앞서 volumeUp()과 같은 메서드를 만들어서 음량이 100을 넘지 못하도록 기능을 개발했지만 소용이 없음
    + Speaker를 사용하는 입장에서는 volume 필드에 직접 접근해서 원하는 값을 설정할 수 있기 때문임
  - 이런 문제를 근본적으로 해결하기 위해서는 volume 필드의 외부 접근을 막을 수 있는 방법이 필요

-----
### 접근 제어자 이해 2
-----
1. 이 문제를 근본적으로 해결하는 방법은 volume 필드를 Speaker 클래스 외부에서는 접근하지 못하게 막는 것
2. Speaker - volume 접근 제어자를 private으로 수정
```java
package access;

public class Speaker {
    private int volume; // private 사용

    Speaker(int volume) {
        this.volume = volume;
    }

    void volumeUp() {
        if(volume >= 100) {
            System.out.println("음량을 증가할 수 없습니다. 최대 음량입니다.");
        } else {
            volume += 10;
            System.out.println("음량을 10 증가합니다.");
        }
    }

    void volumeDown() {
        volume -= 10;
        System.out.println("volumeDown() 호출");
    }

    void showVolume() {
        System.out.println("현재 음량 : " + volume);
    }
}
```
   - private 접근 제어자는 모든 외부 호출을 막음
   - 따라서 private 이 붙은 경우 해당 클래스 내부에서만 호출할 수 있음
   - volume 필드 - private 변경 후
<div align="center">
<img src="https://github.com/user-attachments/assets/76ac7c65-4933-4334-bfad-65abbe8b5c2f">
</div>

   - 그림을 보면 volume 필드를 private을 사용해서 Speaker 내부에 숨김  
   - 외부에서 volume 필드에 직접 접근할 수 없게 막은 것
   - volume 필드는 이제 Speaker 내부에서만 접근할 수 있음

3. 이제 SpeakerMain 코드를 다시 실행
```java
// 필드에 직접 접근
System.out.println("volume 필드 직접 접근 수정");
speaker.volume = 200; // private 접근 오류
```

4. IDE에서 speaker.volume = 200 부분에 오류가 발생하는 것을 확인 가능
   - 실행해보면 다음과 같은 컴파일 오류가 발생
   - 컴파일 오류 메시지
```
volume has private access in access.Speaker
```
   - volume 필드는 private 으로 설정되어 있기 때문에 외부에서 접근할 수 없다는 오류
   - volume 필드 직접 접근 - 주석 처리
```java
// 필드에 직접 접근
System.out.println("volume 필드 직접 접근 수정");

// speaker.volume = 200; // private 접근 오류
speaker.showVolume();
```
   - 이제 Speaker 외부에서 volume 필드에 직접 접근하는 것은 불가능
   - 이 경우 자바 컴파일러가 컴파일 오류를 발생시킴
   - 프로그램을 실행하기 위해서 volume 필드에 직접 접근하는 코드를 주석 처리
