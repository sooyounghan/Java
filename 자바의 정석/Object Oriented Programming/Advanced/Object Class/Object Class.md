-----
### Object Class
-----
1. 모든 클래스 상속 계층도의 최상위에 있는 조상 클래스이므로 자동적으로 Object 클래스로부터 상속받음
```java
class Tv (extends Object) {
    ...
}
```

2. 즉, 컴파일러가 자동적으로 'extends Object'를 추가해 Object 클래스를 상속받도록 함

```java
class Tv {
    ...
}

class CaptionTv extends Tv {
    ...
}
```

  - 위와 같은 클래스의 상속 계층도는 아래와 같음
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/fe5b3ffd-0ebd-41df-936e-ac1da6627ed9">
</div>

3. 이처럼 모든 상속계층도의 최상위에는 Object 클래스가 위치
4. Object 클래스의 멤버들을 상속 받기 때문에, Object 클래스에 정의된 멤버들을 사용 가능
5. 즉, toString()이나 equals(Object o)와 같은 메서드를 따로 정의하지 않고 사용 가능한 이유
