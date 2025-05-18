-----
### 입출력 (I/O; Input/Output)
-----
1. 입력과 출력, 즉 입/출력을 의미
2. 컴퓨터 내부 또는 외부 장치와 프로그램 간 데이터를 주고 받는 것

-----
### 스트림(Stream)
-----
1. 자바에서 입출력을 수행하려면, 즉 어느 한 쪽에서 다른 쪽으로 데이터를 전달하려면, 두 대상을 연결하고 데이터를 전송하게 해주는데 사용되는 연결통로
2. 스트림은 단방향통신만 가능하기 때문에, 하나의 스트림으로 입력과 출력을 동시에 처리 불가
3. 입력과 출력을 동시에 수행하려면 입력을 위한 입력 스트림(Input Stream), 출력을 위한 출력 스트림(Output Stream), 2개의 스트림 필요
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/3a08cad7-e0f9-4185-916f-86b79a7f6f3a">
</div>

4. 스트림은 먼저 보낸 데이터를 먼저 받게 되어있으며, 중간에 건너뜀 없이 연속적으로 데이터를 주고 받음(즉, FIFO 구조)

-----
### 바이트기반 스트림 - InputStream, OutputStream
-----
1. 스트림은 바이트 단위로 데이터를 전송하며, 입출력 대상에 따라 입출력 스트림 존재
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/120e47fe-1135-4bc5-909e-1ef06eacbade">
</div>

2. 이들은 모두 InputStream, OutputStream의 자손들이며, 각 읽고 쓰는데 필요한 추상 메서드들을 자신에 맞게 구현
3. 자바에서는 java.io Package를 통해 많은 종류의 입출력 관련 클래스들 제공
   - 입출력을 처리할 수 있는 표준화된 방법을 제공함으로 입출력 대상이 달라져도 동일한 방법으로 입출력이 가능
4. InputStream과 OutputStream에 정의된 읽기와 쓰기를 수행하는 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/4eaf9a1e-a044-4255-a941-e196ee099baa">
</div>

  - read()의 반환값이 int인 이유 : read()의 반환값 범위가 0 ~ 255와 -1이기 떄문임
  - InputStream의 read()와 OutputStream()의 write(int b)는 입출력 대상에 읽고 쓰는 방법이 다를 것이므로 각 상황에 맞게 알맞게 구현하라는 의미로 추상메서드로 정의
  - 나머지 메서드 모두 위 추상메서드들로 구현한 것이므로 이를 구현해야함

```java
public abstract class InputStream {
    ...
    // 입력스트림으로부터 1byte를 읽어서 반환. 읽을 수 없으면 -1 반환
    abstract int read();

    // 입력스트림으로부터 len개의 byte를 읽어 byte 배열 b의 off위치에서부터 저장
    int read(byte[] b, int off, int len) {
        ...
        for(int i = off; i < off + len; i++) {
            // read()를 호출해 데이터를 읽어 배열을 채움
            b[i] = (byte)read();
        }
        ...
    }

    ...

    // 입력스트림으로부터 byte배열 b의 크기만큼 데이터를 읽어서 배열 b에 저장
    int read(byte[] b) {
          return read(b, 0, b.length);
    }
    ...
}
```

5. 즉, read(), write()는 반드시 구현되어야하는 핵심적인 메서드이며, 이 메서드 없이는 다른 메서드 구현 불가

-----
### 보조 스트림
-----
1. 실제 데이터를 주고받는 스트림이 아니므로 데이터를 입출력할 수 있는 기능은 없지만, 스트림의 기능을 향상시키거나 새로운 기능 추가 가능
2. 보조스트림만으로는 입출력 처리 할 수 없으며, 스트림을 먼저 생성한 다음 이를 이용해 보조스트림 생성
3. 예) test.txt를 읽기위해 FileInputStream을 사용할 때, 성능 향상을 위해 버퍼를 사용하는 보조스트림인 BufferedInputStream 사용
```java
// 먼저 기반 스트림 생성
FileInputStream fis = new FileInputStream("test.txt");

// 기반 스트림 이용해 보조 스트림 생성
BufferedInputStream bis = new BufferedInputStream(fis);

bis.read(); // 보조 스트림인 BufferedInputStream으로부터 데이터를 읽음
```

  - 실제 입력 기능은 BufferedInputStream과 연결된 FileInputStream이 수행
  - 보조스트림인 BufferedInputStream은 버퍼만을 제공 (버퍼를 사용한 입출력과 사용하지 않은 입출력 간의 성능차이는 상당하므로 대부분 이용)

4. 보조스트림들은 InputStream, OutputStream의 자손들이므로 입출력 방법은 동일
5. 보조스트림의 종류
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/8a1d6000-db01-47d6-8d4d-6be6dd5d08c5">
</div>

-----
### 문자 기반 스트림 - Reader, Writer
-----
1. Java에서는 한 문자를 의미하는 char형이 2byte이므로 바이트 기반 스트림으로 2byte 인 문자 처리에 어려움이 존재
2. 이 점을 보완하기 위해 문자 기반 스트림이 제공 (문자데이터 입출력 시에 사용)
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/ac5edf25-e2de-46f1-8521-653045c22a15">
</div>

3. 바이트기반 스트림과 문자기반 스트림의 비교
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/c8312fc2-57cf-4bc7-a29e-39dcf33aec11">
</div>

  - StringBufferInputStream과 StringBufferOutputStream은 StringReader와 StringWriter로 대체되어 더 이상 사용하지 않음

4. InputStream은 Reader로, OutputStream은 Writer로 변경하면 됨 (단, ByteArrayInput/OutputStream에 대응하는 문자 기반 스트림은 char배열을 사용하는 CharArrayReader/Writer)
5. 바이트기반 스트림과 문자기반 스트림의 읽고 쓰는 메서드 비교
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/13800e16-696b-4b13-b656-bffcb37996a5">
</div>

  - byte 배열 대신 char배열을 사용한다는 것과 추상 메서드가 달라짐
  - read()를 추상메서드로 하는 것보다 int read(char[] cbuf, int off, int len)을 추상메서드로 하는 것이 바람직하여 변경

6. 바이트기반 보조스트림과 문자 기반 스트림
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/eafccd15-0d0b-433c-be65-21857be9686b">
</div>
