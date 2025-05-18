-----
### RandomAccessFile
-----
1. 자바에서는 입력과 출력이 각각 분리되어 별도로 작업하도록 설계
2. RandomAccessFile만은 하나의 클래스로 파일에 대한 입력과 출력을 모두 할 수 있도록 되어있음
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/5dc5da1b-68c5-4c44-b3f9-62341c79cc82">
</div>

3. InpuStream이나 OutputStream으로 상속받지 않고, DataInput 인터페이스와 DataOutput 인터페이스를 모두 구현했으므로 읽기와 쓰기가 모두 가능
   - DataInputStream은 DataInput 인터페이스를, DataOutputStream은 DataOuput 인터페이스를 구현
   - 이 두 클래스의 가본 자료형을 읽고 쓰기 위한 메서드들은 모두 이 2개의 인터페이스에 정의
   - 따라서, 기본 자료형 단위로 데이터를 읽고 쓸 수 있음

4. 가장 큰 장점은 파일의 어느 위치에서나 읽기 / 쓰기가 가능
5. 다른 입출력 클래스들은 입출력소스에 순차적으로 읽기 / 쓰기를 하기 때문에, 읽기와 쓰기가 제한적인데, 해당 클래스는 파일에 읽고 쓰는 위치에 제한이 없음
  - 이를 위해 내부적으로 파일 포인터를 사용하는데, 입출력 시 작업이 수행되는 곳이 바로 파일 포인터가 위치한 곳
6. 파일 포인터의 위치는 파일의 제일 첫 부분(0부터 시작)이며, 읽기 또는 쓰기를 수행할 떄마다 작업이 수행된 다음 위치로 이동
7. 순차적으로 읽거나 쓰기를 한다면, 파일 포인터를 이동시키기 위해 별도의 작업이 필요하지 않지만, 파일의 임의의 위치에 있는 내용에 대해 작업을 한다면, 먼저 파일 포인터를 원하는 위치로 옮긴 다음 작업을 수행해야 함
8. 현재 작업중인 파일에서 파일 포인터의 위치를 알고 싶다면, getFilePointer() 사용
9. 파일 포인터의 위치를 옮기기 위해서는 seek(long pos)나 skipBytes(int n)를 사용
10. RandomAccessFile의 생성자와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/4f625d72-37d8-41f0-8296-2ee4ed9c8502">
</div>

  - 사실 모든 입출력에 사용되는 클래스들은 입출력 시 다음 작업이 이루어질 위치를 저장하고 있는 포인터를 내부적으로 가지고 있음
  - 하지만, 내부적으로만 사용될 수 있기 때문에 작업자가 포인터의 위치를 마음대로 변경할 수 없다는 것이 다른 점
  - RandomAceessFile의 인스턴스를 "rw" mode로 생성할 때, 지정한 파일이 없으면 새로운 파일 생성
```java
import java.io.*;

class RandomAccessFileEx1 {
	public static void main(String[] args) {
		try {
			RandomAccessFile raf = new RandomAccessFile("test.dat", "rw");
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
			raf.writeInt(100);
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
			raf.writeLong(100L);
			System.out.println("파일 포인터의 위치: " + raf.getFilePointer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```
```
파일 포인터의 위치: 0
파일 포인터의 위치: 4
파일 포인터의 위치: 12
```
  - int는 4byte이기 때문에 writeInt()를 호출한 다음, 다음 파일 포인터의 위치가 0에서 4로 변경
  - 마찬가지로 long은 8byte이므로 4에서 12로 변경

```java
import java.io.*;

class RandomAccessFileEx2 {
	public static void main(String args[]) {
			     // 번호, 국어, 영어, 수학					
		int[] score = {	1, 100,  90,  90,
				2,  70,  90, 100,
				3, 100, 100, 100, 
				4,  70,  60,  80, 
				5,  70,  90, 100
                  }; 

		try {
		      RandomAccessFile raf = new RandomAccessFile("score2.dat", "rw");

		      for(int i=0; i<score.length;i++) {
		             raf.writeInt(score[i]);				
		      }

		      while(true) {
			           System.out.println(raf.readInt());
		      }
		} catch (EOFException eof) {
		       // readInt()를 호출했을 때, 더 이상 읽을 내용이 없으면 EOFException 발생
		} catch (IOException e) {
		       e.printStackTrace();		
		}
	} // main
}
```
  - int 배열 score에 저장된 내용을 score2.dat에 저장한 다음, 저장된 내용을 다시 readInt()로 읽어서 출력하지만, 아무것도 출력되지 않음
  - 💡 writeInt()를 수행하면서 파일 포인터의 위치가 파일의 마지막으로 이동된 상태에서 readInt()를 호출했으므로 파일의 앞부분이 아닌 마지막 부분부터 읽기 시작했기 때문에 아무것도 읽지 못하고 EOFException 발생
  - 따라서, 다음과 같이 seek(long pos)를 이용해 파일 포인터의 위치를 다시 처음으로 이동 시킨 후 readInt() 호출
```java
raf.seek(0);
while(true) {
     System.out.println(raf.readInt());
}
```
  - 이처럼, RandomAccessFile을 'rw(읽기 쓰기)'모드로 생성해서 작업할 때에는 이 점을 유의해야 함

```java
import java.io.*;

class RandomAccessFileEx3 {
	public static void main(String args[]) {
		int sum = 0;

		try {
			RandomAccessFile raf = new RandomAccessFile("score2.dat", "r");
			int i=4;

			while(true) {
				raf.seek(i);
				sum += raf.readInt();
				i+=16;
			}
		} catch (EOFException e) {
			System.out.println("sum : " + sum);
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
}
```
  - 한 학생의 데이터가 번호와 3과목의 점수로 모두 4개의 int 값(4 * 4 = 16 byte)으로 되어 있으므로
  - i += 16과 같이 파일 포인터의 값을 16씩 증가시켜가면서 readInt() 호출

