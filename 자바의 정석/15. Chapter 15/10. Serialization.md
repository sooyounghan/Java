-----
### 직렬화 (Serialization)
-----
1. 객체를 데이터 스트림으로 만드는 것을 의미
2. 객체에 저장된 데이터를 스트림에 쓰기 (Write) 위해 연속적인 (Serial) 데이터로 변환하는 것
3. 반대로 스트림으로부터 데이터를 읽어서 객체를 만드는 것을 역직렬화(Deserialization)
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/078d6393-4e49-46bb-847a-2a92651409be">
</div>

4. 💡 객체란 클래스에 정의된 인스턴스 변수의 집합이며, 객체에는 클래스 변수나 메서드가 포함되지 않음 (오직 인스턴스 변수들로만 구성)
   - 즉, 객체에는 메서드가 포함되지 않음
   - 인스턴스 변수는 인스턴스마다 다른 값을 가질 수 있어야 하기 때문에, 별도의 메모리 공간이 필요
   - 하지만, 메서드는 변하는 것이 아니라서 메모리를 낭비해 가면서 인스턴스마다 같은 내용의 코드(메서드)를 포함 시킬 이유가 없음
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/84033990-3586-4ffa-bdcd-50613bf9ae69">
</div>

  - 즉, 객체를 저장한다는 것은 객체의 모든 인스턴스 변수의 값을 저장하는 것을 의미
  - 또한, 저장했던 객체를 다시 생성하려면, 객체를 생성한 후에 저장했던 값을 읽어서 생성한 객체의 인스턴스 변수에 저장하면 되는 것

5. 객체를 직렬화 / 역직렬화 하기위해 ObjectInputStream과 ObjectOutputStream을 사용

-----
### ObjectInputStream, ObjectOutputStream
-----
1. 직렬화(스트림에 객체를 출력)에는 ObjectInputStream 사용
2. 역직렬화(스트림으로부터 객체를 입력)에는 ObjectOutputStream 사용
3. ObjectInputStream과 ObjectOutputStream은 각각 InputStream과 OutputStream을 직접 상속받지만, 기반 스트림을 필요로 하는 보조 스트림
4. 따라서, 객체를 생성할 때 입출력(직렬화/역직렬화)할 스트림을 지정해줘야 함
```java
ObjectInputStream(InputStream in)
ObjectOutputStream(OutputStream out)
```
5. 반대로 파일에 객체를 저장(직렬화)하고 싶다면 다음과 같음
```java
FileOutputStream fos = new FileOutputStream("objectfile.ser");
ObjectOutputStream out = new ObjectOutputStream(fos);

out.writeObject(new UserInfo());
```
  - objectfile.ser라는 파일에 UserInfo 객체를 직렬화하여 저장
  - 출력 스트림(FileOutputStream)을 생성해 이를 기반스트림으로 하는 ObjectOutputStream 생성
  - ObjectOutputStream의 wrtieObject(Object obj)를 사용해 객체를 출력하면, 객체가 파일에 직렬화되어 저장

6. 역직렬화는 직렬화할 떄와는 달리 입력스트림을 사용하고, readObject()를 사용해 저장된 데이터를 읽기만 하면 객체로 역직렬화됨
   - 다만, readObject()의 반환타입이 Object이므로 원래 객체 타입으로 형변환해줘야 함
```java
FileInputStream fis = new FileInputStream("objectfile.ser");
ObjectInputStream in = new ObjectInputStream(fis);

UserInfo info = (UserInfo)in.readObject();
```

7. ObjectInputStream 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/6b6f0327-5ae5-4a37-b256-91dd3eb2589e">
</div>

8. ObjectOutputStream 메서드
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/92e779b2-971b-4fee-ac5a-99954e395f44">
</div>

9. 위 메서드들은 주로 직렬화와 역직렬화를 직접 구현할 때 사용하며, defaultReadObject()와 defaultWriteObject()는 자동 직렬화를 수행
10. 객체를 직렬화 / 역직렬화하는 작업은 객체의 모든 인스턴스 변수가 참조하고 있는 모든 객체에 대한 것이기 때문에 상당히 복잡하며 시간도 오래 걸림
11. 직렬화 작업 시간을 단축시키려면, 직렬화하고자 하는 객체의 클래스에 추가적으로 다음과 같은 2개의 메서드를 직접 구현해줘야 함
```java
private void writeObject(ObjectOutputStream out) throws IOException {
    // write 메서드를 사용해 직렬화 수행
}
```
```java
private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    // read 메서드를 사용해 역직렬화 수행
}
```

-----
### 직렬화가 가능한 클래스 만들기 - Serializable, transient
-----
1. 직렬화가 가능한 클래스를 만드는 방법은 직렬화하고자 하는 클래스가 java.io.Serializable 인터페이스를 구현하도록 하면 됨
2. 예시) UserInfo 클래스에 대해 직렬화가 가능하도록 하려면, 다음과 같이 구현하도록 변경
```java
public class UserInfo {
    String name;
    String password;
    int age;
}
```
```java
public class UserInfo impelements java.io.Serializable {
    String name;
    String password;
    int age;
}
```

3. Serializable 인터페이스는 아무런 내용도 없는 인터페이스이지만, 직렬화를 고려하여 작성한 클래스인지 판단하는 기준이 됨
```java
public interface Serializable { }
```

4. 하지만, Serializable을 구현한 클래스를 상속받는다면, 이를 구현하지 않아도 됨
```java
public class SuperUserInfo implements Serializable {
    String name;
    Stirng password;
}

public class UserInfo extends SuperUserInfo {
    int age;
}
```

   - UserInfo는 Serializable를 구현하지 않았지만, 조상인 SuperUserInfo가 Serializable을 구현했으므로 UserInfo 역시 직렬화 가능
   - UserInfo 객체를 직렬화하면, 조상인 SuperUserInfo에 정의된 인스턴스 변수 name, password도 함께 직렬화

5. 하지만, 조상 클래스가 Serializable을 구현하지 않았다면, 자손 클래스를 직렬화할 때 조상 클래스에 정의된 인스턴스 변수는 직렬화 대상에서 제외
```java
public class SuperUserInfo {
    String name;
    Stirng password;
}

public class UserInfo extends SuperUserInfo  implements Serializable {
    int age;
}
```
  - 조상 클래스에 정의된 인스턴스 변수 name과 password를 직렬화 대상에 포함시키기 위해서는 조상 클래스에 Serializable을 구현
  - 또는, UserInfo에서 조상의 인스턴스 변수들이 직렬화되도록 코드를 직접 추가해줘야 함
  - 현 상황에서 이 클래스의 객체를 직렬화하면, java.io.NotSerializableException이 발생하면서 직렬화 실패
  - 이유는, 직렬화할 수 없는 클래스의 객체를 인스턴스 변수가 참조하고 있기 떄문임

6. 모든 클래스의 최고 조상인 Object는 Serializable을 구현하지 않았기 때문에 직렬화할 수 없음
7. 만약, Object가 Serializable을 구현했다면, 모든 클래스가 직렬화가 가능했을 것임
```java
public class UserInfo implements Serializable {
    String name;
    Stirng password;
    int age;

    Object obj = new Object(); // Object 객체는 직렬화 불가
}
```

8. 하지만 다음과 같은 경우는 가능
```java
public class UserInfo implements Serializable {
    String name;
    Stirng password;
    int age;

    Object obj = new String("abc"); // String은 직렬화 가능
}
```
  - 실제로 저장된 객체는 직렬화가 가능한 String 인스턴스이기 때문에 직렬화가 가능
  - 💡 인스턴스 변수의 타입이 아닌 실제로 연결된 객체의 종류에 의해 결정됨

9. 직렬화하고자 하는 객체의 클래스에 직렬화가 안 되는 객체에 대한 참조를 포함하고 있다면, 제어자 'transient'를 붙여서 직렬화 대상에서 제외시키면 됨
  - UserInfo에서 password와 같이 보안상 직렬화하면 안되는 값 등을 의미
  - transient가 붙은 인스턴스 변수의 값은 그 타입의 기본값으로 직렬화 됨
```java
public class UserInfo implements Serializable {
    String name;
    transient Stirng password;
    int age;

    transient Object obj = new Object(); // Object 객체는 직렬화 불가
}
```
  - 즉, UserInfo 객체를 역직렬화하면 참조변수인 obj와 password의 값은 null

-----
### 직렬화 예제
-----
1. UserInfo 클래스
```java
public class UserInfo implements java.io.Serializable {
	String name;
	String password;
	int age;

	public UserInfo() {
		this("Unknown", "1111", 0);
	}

	public UserInfo(String name, String password, int age) {
		this.name = name;	
		this.password = password;	
		this.age = age;	
	}

	public String toString() {
		return "("+ name + "," + password + "," + age + ")";
	}
}
```

2. 직렬화 예제
```java
import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo.ser";
			FileOutputStream     fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo u1 = new UserInfo("JavaMan","1234",30);
			UserInfo u2 = new UserInfo("JavaWoman","4321",26);

			ArrayList<UserInfo> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);

			// 객체 직렬화
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			out.close();
			System.out.println("직렬화 완료.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // main
} // class
```

  - 생성한 객체를 직렬화 하여 파일(UserInfo.ser)에 저장 (확장자를 직렬화의 약자로 ser로 하는 것이 관례)
  - 버퍼를 이용한 FileOutputStream을 기반으로 하는 ObjectOutputStream을 생성한 다음, writeObject()를 이용해 객체를 ObjectOutputStream에 출력하면, UserInfo.ser 파일에 객체가 직렬화되어 저장
  - 객체에 정의된 모든 인스턴스에 대한 참조를 찾아들어가기 때문에 상당히 복잡하고 시간이 오래걸림
  - 따라서, ArrayList와 같은 객체를 직렬화하면 ArrayList에 저장된 모든 객체들과 각 객체의 인스턴스 변수가 참조하고 있는 객체들까지 모두 직렬화

3. 역직렬화 예제
```java
import java.io.*;
import java.util.ArrayList;

public class SerialEx2 {
	public static void main(String[] args) {
		try {
			String fileName = "UserInfo.ser";
			FileInputStream     fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);

			ObjectInputStream in = new ObjectInputStream(bis);

			// 객체를 읽을 때는 출력한 순서와 일치해야 함 
			UserInfo u1 = (UserInfo)in.readObject();
			UserInfo u2 = (UserInfo)in.readObject();
			ArrayList list = (ArrayList)in.readObject();

			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			in.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // main
} // class
```
  - FileInputStream과 ObjectInputStream을 사용하고, readObject() 사용
  - readObject()의 Return Type이 Object이므로 원래 타입으로 형변환 해줘야함
  - 💡 객체를 역직렬화할 때에는 직렬화할 때와의 순서와 일치해야 함
  - 따라서, 직렬화할 객체가 많을 때는 각 개별적으로 직렬화하는 것보다 ArrayList와 같은 컬렉션에 저장해 직렬화하는 것이 좋음 (객체 순서를 고려하지 않아도 되기 때문임)

4. 직렬화되지 않는 조상으로부터 상속받은 인스턴스 변수에 대한 직렬화 구현
```java
import java.io.*;

class SuperUserInfo {
	String name;
	String password;

	SuperUserInfo() {
		this("Unknown","1111");
	}

	SuperUserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}
} // class SuperUserInfo

public class UserInfo2 extends SuperUserInfo implements java.io.Serializable {
	int age;

	public UserInfo2() {
		this("Unknown", "1111", 0);
	}

	public UserInfo2(String name, String password, int age) {
		super(name, password);
		this.age = age;	
	}

	public String toString() {
		return "("+ name + "," + password + "," + age + ")";		
	}

	private void writeObject(ObjectOutputStream out)
		throws IOException {
		out.writeUTF(name);	
		out.writeUTF(password);	
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		password = in.readUTF();
		in.defaultReadObject();
		}
} // class UserInfo2 
```
  - 직렬화될 객체의 클래스에 아래와 같이 writeObject()와 readObject()를 추가해 조상으로부터 상속받은 인스턴스 변수인 name과 password가 직접 직렬화되도록 해야함
  - 이 메서드들은 직렬화 / 역직렬화 작업 시 자동적으로 호출됨

```java
private void writeObject(ObjectOutputStream out)
  throws IOException {
  out.writeUTF(name);	
  out.writeUTF(password);	
  out.defaultWriteObject();
}

private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
  name = in.readUTF();
  password = in.readUTF();
  in.defaultReadObject();
}
```
  - 💡 두 메서드의 접근자는 private (단순히 미리 정한 규칙임)
  - name과 password의 타입이 String 이므로 writeUTF() / readUTF()를 사용
  - 각 인스턴스 변수 타입에 맞는 것을 선택해서 사용하면 됨
  - defaultWriteObject()는 UserInfo2 클래스 자신에 정의된 인스턴스 변수 age 직렬화를 수행

-----
### 직렬화 가능한 클래스의 버전 관리
-----
1. 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스를 사용해야 함
2. 또한, 클래스의 이름이 같더라도 클래스의 내용이 변경된 경우 역직렬화가 실패하며, 다음과 같은 예외 발생
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/786344b1-0038-4f96-b307-600f8dd5bb47">
</div>

  - 직렬화 할 때와 역직렬화 할 때의 클래스의 버전이 같아야 하는데 달라서 생기는 예외
  - 객체가 직렬화될 때 클래스에 정의된 멤버들의 정보를 이용해 serialVersionUID라는 클래스의 버전을 자동 생성해서 직렬화 내용에 포함
  - 따라서, 역직렬화할 때 클래스의 버전을 비교함으로써, 직렬화할 때의 클래스의 버전과 일치하는지 확인 가능

3. 하지만, static 변수나 상수, transient가 붙은 인스턴스 변수가 추가되는 경우에는 직렬화에 영향을 미치지 않기 때문에 클래스의 버전을 다르게 인식하도록 할 필요가 없음
4. 클래스의 버전으로 수동으로 관리하는 방법은 다음과 같음
```java
class MyData impelements java.io.Serializable {
    int value1;
}
```
  - 위와 같이 MyData라는 직렬화가 가능한 클래스가 있을 때, 클래스의 버전을 수동으로 관리하려면 다음과 같이 serialVersionUID를 추가로 정의 해주면 됨
```java
class MyData impelements java.io.Serializable {
    static final long serialVersionUID = 9518731767529258119L;
    int value1;
}
```
5. 이처럼 클래스 내 serialVersionUID를 정의해주면, 클래스의 내용이 바뀌어도 클래스의 버전이 자동 생성된 값으로 변경되지 않음
6. serialVersionUID의 값은 정수값이면, 어떠한 값으로도 지정할 수 있지만, 서로 다른 클래스 간 같은 값을 갖지 않도록 serialver.exe를 사용해 생성된 값을 사용하는 것이 보통
<div align="center">
<img src="https://github.com/sooyounghan/Data-Base/assets/34672301/4b104123-d6ac-4336-a863-a3456f3a215c">
</div>

  - serialver.exe 뒤 serialVersionUID를 얻고자 하는 클래스의 이름만 적어주면 클래스의 serialVersionUID를 알아낼 수 있음
  - serialver.exe는 클래스에 serialVersionUID가 정의되어 있으면 그 값을 출력하고, 정의되어 있지 않으면 자동 생성한 값 출력
  - 이 값은 클래스의 멤버들에 대한 정보를 바탕으로 하므로 이 정보가 변경되지 않는 한 항상 같은 값 생성
