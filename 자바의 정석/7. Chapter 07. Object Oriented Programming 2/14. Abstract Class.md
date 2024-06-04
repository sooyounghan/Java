-----
### 추상 클래스 (Abstract Class)
-----
1. 추상 메서드(미완성 메서드)를 포함하고 있는 클래스
2. 그러므로, 추상 클래스는 인스턴스는 생성할 수 없으며, 상속을 통해서 자손 클래스에 의해서만 완성 가능
3. 키워드 'abstract'를 붙임. 이를 통해, 이 클래스를 사용할 때, 선언부를 보고 추상 메서드가 있으니 상속을 통해 구현해줘야 한다는 것을 알 수 있음
```java
abstract class 클래스이름 {
      ...
}
```

4. 추상 메서드를 가지고 있는 것을 제외하고 다른 점이 없음
5. 추상클래스에도 생성자를 가질 수 있으며, 멤버변수와 메서드를 가질 수 있음
6. 또한, 추상메서드를 포함하고 있지 않은 클래스에도 'abstract'를 붙여서 추상클래스를 지정할 수 있음. 단, 지정되면 클래스의 인스턴스 생성 불가

-----
### 추상 메서드 (Abstract Method)
-----
1. 선언부만 작성하고 구현부는 작성하지 않은 채 남겨둔 것
2. 메서드의 내용이 상속받는 클래스에 의해 달라질 수 있기 때문에 조상 클래스는 선언부만 작성하고, 주석을 덧붙여 어떤 기능을 수행할 목적으로 작성되었는지 알려줌
3. 실제 내용은 상속받는 클래스에서 구현하도록 비워두는 것
4. 추상 메서드 역시, 추상 클래스와 동일하게 'abstract' 키워드를 붙여야 하며, 구현부가 없으므로 괄호 { } 대신 ;을 붙임
```java
/* 주석을 통해 어떤 기능을 수행할 목적으로 작성하였는지 설명 */
abstract 리턴타입 메서드이름();
```

5. 추상클래스로부터 상속받는 자속클래스는 오버라이딩을 통해 조상인 추상클래스의 '추상 메서드를 모두 구현'해야 함
6. 💡 만약, 조상으로부터 상속받은 추상메서드 중 하나라도 구현하지 않는다면, 자손 클래스 역시 추상클래스로 지정해줘야함
```java
abstract class Player { // 추상 클래스
    abstract void play(int pos); // 추상 메서드
    abstract void stop(); // 추상 메서드
}

class AudioPlayer extends Player {
    void play(int pos) { /* 내용 생략 */ } // 추상 메서드 구현
    void stop() { /* 내용 생략 */ } // 추상 메서드 구현
}

abstract class Abstractplayer extends Player {
    void play(int pos) { /* 내용 생략 */ } // 추상 메서드 구현
    // stop() 미구현 : 추상 클래스
}
```

7. 메서드를 사용하는 쪽에서는 메서드가 실제로 어떻게 구현되었는지 몰라도 메서드의 이름과, 매개변수, 리턴타입, 즉 선언부를 알고 있으면 내용이 없을지라도 추상메서드를 사용하는 코드 작성 가능
8. 실제로는, 자손클래스에 구현된 완성된 메서드가 호출되도록 할 수 있음

-----
### 추상 클래스 (Abstract Class) 작성
-----
1. 추상(抽象) : 구체적 표상이나 개념에서 공통된 성질을 뽑아 이를 일반적 개념으로 파악하는 것
2. 상속이 자손 클래스를 만드는데 조상 클래스를 사용하는 것이라면, 추상화는 기존 클래스의 공통부분을 뽑아내어 조상 클래스를 만드는 것
3. 즉, 추상화와 구체화는 서로 반대되는 개념으로 생각할 것
   - 상속 계층도를 따라 내려갈수록 클래스는 점점 기능이 추가되어 구체화의 정도가 심해짐
   - 상속 계층도를 따라 올라갈수록 클래스는 추상화의 정도가 심해짐
   - 즉, 상속 계층도를 따라 내려 갈수록 세분화, 올라갈수록 공통 요소만 남게 됨
```java
A. 추상화 : 클래스 간 공통점을 찾아내어 공통의 조상을 만드는 작업
B. 구체화 : 상속을 통해 클래스를 구현, 확장하는 작업
```

4. 예제
```java
abstract class Player {
    boolean pause; // 일시정지 상태를 저장하기 위한 변수
    int currentPos; // 현재 Play되고 있는 위치를 저장하기 위한 변수

    Player() {
        pause = false; // 추상 클래스도 생성자가 있어야 함
        currentPost = 0;
    }

    /* 지정된 위치(pos)에서 재생을 시작하는 기능이 수행하도록 작성 */
    abstract void play(int pos); // 추상 메서드

    /* 재생을 즉시 멈추는 기능을 수행하도록 작성 */
    abstract void stop(); // 추상 메서드

    void play() {
        play(currentPos); // 추상 메서드를 사용할 수 있음
    }

    void pause() {
        if(pause) { // pause가 true일 때, 즉 정지 상태에서 pause가 호출되면,
            pause = false; // pause 상태를 false로 변경
            play(currentPos); // 현재 위치에서 play
        } else {
            pause = true; // pause의 상태를 true로 변경
            stop(); // play를 멈춤
        }
    }
}

class CDPlayer extends Player {
    void play(int currentPos) {
        /* 조상의 추상 메서드 구현. 내용 생략 */
    }

    void stop() {
        /* 조상의 추상 메서드 구현. 내용 생략 */
    }

    // CDPlayer 클래스에 추가 정의된 멤버
    int currentTrack; // 현재 재생 중 트랙

    void nextTrack() {
        currentTrack++;
        ...
    }

    void preTrack() {
        if(currentTrack > 1) {
            currentTrack--;
        }
            ...
    }
}
```
  - 아무런 내용이 없이 CDPlayer 클래스에서 단지 괄호 { }만 있어도, 추상메서드가 아닌 일반 메서드로 간주
  - 그럼에도 abstract를 선언하여 추상메서드로 선언하는 이유는 '자손 클래스에서 추상메서드를 반드시 구현하도록 강요하기 위해서임'
  - 그렇지 않다면, 상속받는 자손 클래스에서는 이 메서드들이 온전히 구현된 것으로 인식하고 오버라이딩을 통해 자신의 클래스에 맞도록 구현하지 않을 수도 있기 때문임
  - 하지만, abstract를 사용해서 추상메서드로 정의한다면, 자손 클래스를 작성할 때 이 추상메서드이므로 내용을 구현해줘야 한다는 사실을 인식하고, 자신의 클래스에 맞게 구현

5. 예제
```java
class Marine { // 보병
    int x, y; // 현재 위치
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void stop() { /* 현재 위치에 정지 */ }
    void stimPack() { /* 스팀팩을 사용 */ }
}

class Marine { // 탱크
    int x, y; // 현재 위치
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void stop() { /* 현재 위치에 정지 */ }
    void changeMode() { /* 공격 모드로 변환 */ }
}

class Dropship { // 수송선
    int x, y; // 현재 위치
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void stop() { /* 현재 위치에 정지 */ }
    void load() { /* 선택된 대상을 태움 */ }
    void unload() { /* 선택된 대상을 내림 */ }
}
```
  - 이들의 공통부분을 하나의 클래스로 만들고, 이 클래스로부터 상속받도록 변경하면,
```java
abstract class Unit {
    int x, y; // 현재 위치
    abstract void move(int x, int y);
    void stop() { /* 현재 위치에 정지 */ }
}

class Marine { // 보병
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void stimPack() { /* 스팀팩을 사용 */ }
}

class Marine { // 탱크
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void changeMode() { /* 공격 모드로 변환 */ }
}

class Dropship { // 수송선
    void move(int x, int y) { /* 지정한 위치로 이동 */ }
    void load() { /* 선택된 대상을 태움 */ }
    void unload() { /* 선택된 대상을 내림 */ }
}
```
  - 즉, 각 클래스의 공통부분을 뽑아내어 Unit 클래스로 정의하고, 이로부터 상속받도록 함
  - Unit 클래스는 다른 유닛을 위한 클래스를 작성하는데 재활용 가능

```java
Unit[] group = new Unit[4];
group[1] = new Marine();
group[2] = new Tank();
group[3] = new Marine();
group[4] = new Dropship();

for(int i = 0; i < group.length; i++) {
    group[i].move(100, 200); // Unit 배열의 모든 유닛을 좌표 (100, 200)의 위치로 이동
}
```
  - Unit 클래스 타입의 참조변수 배열을 통해 서로 다른 종류의 인스턴스를 하나의 묶음으로 다룰 수 있음을 보여줌
  - 그러나, 이들 클래스 간의 공통 조상이 없다면 하나의 배열로 다룰 수 없음
  - 또한, Unit 클래스의 참조변수로 move 메서드를 호출하는 것이 가능한데, 메서드는 참조변수의 타입에 관계없이 실제 인스턴스에 구현된 것이 호출되기 때문
  - 그러므로, move 메서드는 Unit 클래스의 추상메서드인 move를 호출하는 것 처럼 보이지만, 실제로는 이 추상메서드가 구현된 각 인스턴스의 메서드가 호출되는 것

  - 모든 클래스의 조상인 Object 클래스 타입의 배열로도 이들을 묶을 수 있지만, Object 클래스에는 move가 정의되지 않았기 때문에 에러 발생
```java
Object[] group = new Unit[4];
group[1] = new Marine();
group[2] = new Tank();
group[3] = new Marine();
group[4] = new Dropship();

for(int i = 0; i < group.length; i++) {
    group[i].move(100, 200); // Error. Object 클래스에는 move 메서드가 정의되어 있지 않음
}
```
