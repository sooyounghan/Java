-----
### Properties
-----
1. HashMap의 구버전인 HashTable을 상속받아 구현
2. HashTable은 키와 값을 (Object, Object)의 형태로 저장하는데 비해, Properties는 (String, String)의 형태로 저장하는 보다 단순화된 컬렉션 클래스
3. 주로, 애플리케이션의 환경설정과 관련된 속성(Property)를 저장하는데 사용
4. 데이터를 파일로부터 읽고 쓰는 편리한 기능 제공하여, 간단한 입출력 등에 활용
5. Properties의 생성자와 메서드
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/0de2608e-29f9-4fda-9e34-4afcc1154f4f">
</div>

  - Set stringPropertyNames() : Properties에 저장되어 있는 모든 키(Key)를 Set에 담아 반환

```java
import java.util.*;

class PropertiesEx1 {
	public static void main(String[] args) {
		Properties prop = new Properties();

		// prop의 키와 값(key, value)을 저장
		prop.setProperty("timeout","30");
		prop.setProperty("language","kr");
		prop.setProperty("size","10");
		prop.setProperty("capacity","10");

		// prop에 저장된 요소들을 Enumeration을 이용해서 출력
		Enumeration e = prop.propertyNames();

		while(e.hasMoreElements()) {
			String element = (String)e.nextElement();
			System.out.println(element + "="+ prop.getProperty(element));
		}

		System.out.println();
		prop.setProperty("size","20");	// size의 값을 20으로 변경
		System.out.println("size="       + prop.getProperty("size"));
		System.out.println("capacity="   + prop.getProperty("capacity", "20"));
		System.out.println("loadfactor=" + prop.getProperty("loadfactor", "0.75"));

		System.out.println(prop);	// prop에 저장된 요소들을 출력
		prop.list(System.out);      // prop에 저장된 요소들을 화면(System.out)에 출력
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/HTTP/assets/34672301/9dafb0be-ca50-470a-9291-2392f0d56d4e">
</div>

  - setProperty()는 기존의 같은 키로 저장된 값이 있는 경우, 그 값을 Object 타입으로 변환하며, 그렇지 않을 때는 null 반환
```java
Object setProperty(String key, String value);
```
  - getProperty()는 Properties에 저장된 값을 읽어오는 일을 하는데, 만약 읽어오려는 키가 존재하지 않으면, 지정된 기본값(Default Value) 반환
```java
String getProperty(String key)
String getProperty(String key, String defaultValue)
```

6. Properties는 HashTable을 상속받아 구현한 것이라 Map의 특성상 저장 순서를 유지하지 않기 때문에, 출력된 순서가 저장순서와는 무관
7. 또한, 컬렉션 프레임워크 이전의 구버전이므로 Iterator가 아닌 Enumeration을 사용
8. 또한, list 메서드를 이용하면 Properties에 저장된 모든 데이터를 화면 또는 파일에 편리하게 출력
```java
void list(PrintStream out)
void list(PrintWriter out)
```
  - System.out은 화면과 연결된 표준 출력으로, System 클래스에 정의된 PrintStream 타입의 static 변수

```java
import java.io.*;
import java.util.*;

class PropertiesEx2 {
	public static void main(String[] args) {
		// commandline에서 inputline을 지정해주지 않으면, 프로그램은 종료
		if(args.length != 1) {
			System.out.println("USAGE: java PropertiesEx2 INPUTFILENAME");
			System.exit(0);
		}

		Properties prop = new Properties();

		String inputFile = args[0];

		try {
			prop.load(new FileInputStream(inputFile));
		} catch(IOException e) {
			System.out.println("지정된 파일을 찾을 수 없습니다.");
			System.exit(0);
		}

		String   name = prop.getProperty("name");
		String[] data = prop.getProperty("data").split(",");
		int max = 0;
		int min = 0;
		int sum = 0;

		for(int i=0; i < data.length; i++) {
			int intValue = Integer.parseInt(data[i]);
			if (i==0) max = min = intValue;

			if (max < intValue) {
				max = intValue;
			} else if (min > intValue) {
				min = intValue;
			}

			sum += intValue;
		}

		System.out.println("이름 :"  + name);		
		System.out.println("최대값 :" + max);
		System.out.println("최소값 :" + min);
		System.out.println("합계 :"  + sum);
		System.out.println("평균 :"  + (sum*100.0/data.length)/100);
	}
}
```

9. 외부파일의 형식은 라인 단위로 키와 값이 '='로 연결된 형태이어야 하며, 주석 라인은 첫 번째 문자가 #이어야 함
10. 정해진 규칙대로만 파일을 작성하면 load()를 통해 쉽게 데이터를 읽어올 수 있음 (다만, Encoding 문제로 한글이 깨질 수 있으므로 한글을 입력받으려면 다음과 같이 변경)
```java
String   name = prop.getProperty("name");

try {
     name = new String(name.getBytes("8859_1"), "EUC-KR");
} catch(Exception e) { }
```

  - 즉, 파일로부터 읽어온 데이터의 인코딩을 라틴문자 집합(8859_1)에서 EUC-KR 또는 KSC5601으로 변환해주는 과정 포함

```java
import java.util.*;
import java.io.*;

class PropertiesEx3 {
	public static void main(String[] args) {
		Properties prop = new Properties();

		prop.setProperty("timeout","30");
		prop.setProperty("language","한글");
		prop.setProperty("size","10");
		prop.setProperty("capacity","10");

		try {
			prop.store(new FileOutputStream("output.txt"), "Properties Example");
			prop.storeToXML(new FileOutputStream("output.xml"),  "Properties Example");
		} catch(IOException e) {
			e.printStackTrace();		
		}
	} // main
}
```
  - Properties에 저장된 데이터를 store()와 storeToXML()를 이용해 파일로 저장
  - 여기서도 한글 문제가 발생하는데, storeToXML()로 저장한 XML은 Editplus나 Eclipse에서 한글 편집이 가능하므로 데이터에 한글이 포함된 경우에는 storeToXML()를 이용해서 XML로 저장하는 것이 좋음

```java
import java.util.*;

class PropertiesEx4{
	public static void main(String[] args) {
		Properties sysProp = System.getProperties();
		System.out.println("java.version :" + sysProp.getProperty("java.version"));
		System.out.println("user.languag :" + sysProp.getProperty("user.language"));
		sysProp.list(System.out);
	}
}
```
  - 시스템 속성을 가져오는 방법을 보여주는 예제
  - System 클래스의 getProperties()를 호출하면, 시스템과 관련된 속성이 저장된 Properties를 가져올 수 있으며, 이 중에서 원하는 속성을 getProperty()를 통해 얻을 수 있음




