-----
### 가변 배열
-----
1. 2차원 이상의 다차원 배열을 생성할 때, 전체 배열 차수 중 마지막 차수의 길이를 지정하지 않음
2. 추후에 각기 다른 길이의 배열을 생성함으로써, 고정된 형태가 아닌 보다 유동적인 가변 배열을 구성 가능
3. 다음과 같이 5 X 3 길이의 2차원 배열 score을 생성하고자 한다면?
```java
int[][] score = new int[5][3]; // 5행 3열의 2차원 배열 생성
```

4. 가변 배열로 표현하면,
```java
int[][] score = new int[5][]; // 두 번째 차원의 길이는 지정하지 않음
score[0] = new int[3];
score[1] = new int[3];
score[2] = new int[3];
score[3] = new int[3];
score[4] = new int[3];
```
  - 각 행마다 다른 길이의 배열을 생성하는 것이 가능
```java
int[][] score = new int[5][]; // 두 번째 차원의 길이는 지정하지 않음
score[0] = new int[4];
score[1] = new int[3];
score[2] = new int[2];
score[3] = new int[2];
score[4] = new int[3];
```

5. 위의 2차원 배열을 그림으로 표현하면,
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/ad852540-9f5e-45d4-8fe5-74d04bc45064">
</div>

6. score.length 값은 여전히 5이지만, 일반적인 2차원 배열과 달리 socre[0].length, score[1].length,... 값이 다름
7. 가변 배열 역시 중괄호{}를 이용해 다음과 같이 생성과 초기화 동시에 하는 것이 가능
```java
int[][] score = {
                  {100, 100, 100, 100},
                  {20, 20, 20},
                  {30, 30},
                  {40, 40},
                  {50, 50, 50}
                };
```
