-----
### 기본형 매개변수와 참조형 매개변수
-----
1. 매개변수 타입 : 기본형 매개변수(Primitive Type)과 참조형 매개변수(Reference Type)
2. 기본형 매개변수 : 기본형 값이 복사되므로, 변수의 값을 읽기만 할 수 있음 (Read Only)
```java
class Data { int x; }

class PrimitiveParamEx {
	public static void main(String[] args) {
		Data d = new Data();
		d.x = 10;
		System.out.println("main() : x = " + d.x);

		change(d.x);
		System.out.println("After change(d.x)");
		System.out.println("main() : x = " + d.x);
	}

	static void change(int x) {  // 기본형 매개변수
		x = 1000;
		System.out.println("change() : x = " + x);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/7de8251a-b2b8-41e2-9dec-c7f4504c6a5e">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/da0a6624-cfbc-4eac-803d-064e2a44f09c">
</div>

  - 즉, d.x값이 변경된 것이 아니라, change 메서드의 매개변수 x의 값이 변경된 것
  - 즉, 원본이 아닌 복사본이 변경된 것이라 원본에는 아무런 영향을 미치지 못하는 것

3. 참조형 매개변수 : 인스턴스의 주소가 복사되므로, 변수의 값을 읽고 변경할 수 있음 (Read & Write)
```java
class Data { int x; }

class ReferenceParamEx {
	public static void main(String[] args) {

		Data d = new Data();
		d.x = 10;
		System.out.println("main() : x = " + d.x);

		change(d);
		System.out.println("After change(d)");
		System.out.println("main() : x = " + d.x);

	}

	static void change(Data d) { // 참조형 매개변수
		d.x = 1000;
		System.out.println("change() : x = " + d.x);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/f1fbb808-1f27-4ad1-89d0-3e11d33551ef">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/338d59b9-a93e-4ab1-915e-4cb8cef96dce">
</div>

  - 참조형 매개변수이므로 x가 값이 아닌 주소로 매개변수 d에 복사
  - 즉, main 메서드의 참조변수 d와 change 메서드의 참조변수 d는 같은 객체를 가리킴

```java
class ReferenceParamEx2 {
	public static void main(String[] args) 
  {
		int[] x = {10};  // 크기가 1인 배열 x[0] = 10;
		System.out.println("main() : x = " + x[0]);

		change(x);
		System.out.println("After change(x)");
		System.out.println("main() : x = " + x[0]);
	}

	static void change(int[] x) { // 참조형 매개변수
		x[0] = 1000;
		System.out.println("change() : x = " + x[0]);
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/bd232c35-aad8-44fd-8587-ec05d47772b7">
</div>

  - 배열 또한 객체와 같이 참조변수를 통해 데이터가 저장된 공간에 접근

```java
class ReferenceParamEx3 {
	public static void main(String[] args) 
	{
		int[] arr = new int[] {3,2,1,6,5,4};

		printArr(arr);  // 배열의 모든 요소 출력
		sortArr(arr);   // 배열 정렬
		printArr(arr);  // 정렬 후 결과 출력
		System.out.println("sum = "+sumArr(arr)); // 배열 총합 출력
	}

	static void printArr(int[] arr) {  // 배열의 모든 요소 출력
		System.out.print("[");

		for(int i : arr)  // 향상된 for문
			System.out.print(i+",");
		System.out.println("]");
	}

	static int sumArr(int[] arr) {  // 배열 정렬
		int sum = 0;

		for(int i = 0; i < arr.length; i++)
			sum += arr[i];
		return sum;
	}

	static void sortArr(int[] arr) {  // 정렬 후 결과 출력
		for(int i = 0; i < arr.length-1; i++)
			for(int j = 0; j < arr.length-1-i; j++)
				if(arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
	} // sortArr(int[] arr)
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/a7443c5f-edb4-4144-9dca-d57e83c59df3">
</div>

```java
class ReturnTest {
	public static void main(String[] args) {
		ReturnTest r = new ReturnTest();

		int result = r.add(3, 5);
		System.out.println(result);

		int[] result2 = {0}; // 배열을 생성하고 result2[0]의 값을 0으로 초기화
		r.add(3, 5, result2);  // 배열을 add 메서드의 매개변수로 전달
		System.out.println(result2[0]);
	}

	int add(int a, int b) {
		return a + b;
	}

	void add(int a, int b, int[] result) {
		result[0] = a + b;  // 매개변수로 넘겨받은 배열에 연산결과 저장
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/86826aa0-f7f8-4d00-9f10-effef1b6932d">
</div>

-----
### 참조형 반환타입
-----
1. 매개변수 뿐만 아니라 반환타입도 참조형 가능
```java
class Data { int x; }

class ReferenceReturnEx {
	public static void main(String[] args) 
	{
		Data d = new Data();
		d.x = 10;

		Data d2 = copy(d); 
		System.out.println("d.x ="+d.x);
		System.out.println("d2.x="+d2.x);
	}

	static Data copy(Data d) {
		Data tmp = new Data(); // 새로운 객체 tmp를 생성
		tmp.x = d.x; // d.x의 값을 tmp.x에 복사

		return tmp; // 복사한 객체의 주소를 반환
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/JavaScript/assets/34672301/b4ef8714-c4e0-42d6-9f3e-0de4b26f9ad3">
</div>

  - copy 메서드를 호출하면서 참조변수 d의 값이 매개변수 d에 복사
  - 새로운 객체를 생성한 다음, d.x에 저장된 값을 tmp.x에 복사
  - copy 메서드가 종료되면서 반환한 tmp 값은 참조변수 d2에 저장
  - copy 메서드가 종료되어 tmp가 사라졌지만, d2로 새로운 객체를 다룰 수 있음

2. 반환타입이 참조형이라는 것 : 메서드가 객체의 주소를 반환한다는 것
