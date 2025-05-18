-----
### Arrays Class
-----
1. 배열을 다루는데 필요한 유용한 메서드 정의
2. Arrays에 정의된 모든 메서드는 static 메서드

-----
### Arrays.toString()
-----
: 모든 기본형 배열과 참조형 배열 별로 하나씩 정의
```java
static String toString(booelan[] a)
static String toString(byte[] a)
static String toString(char[] a)
static String toString(short[] a)
static String toString(int[] a)
static String toString(long[] a)
static String toString(float[] a)
static String toString(double[] a)
static String toString(Object[] a)
```

-----
### 배열의 복사 : Arrays.copyOf(), Arrays.copyOfRange()
-----
1. copyOf(Array, Array.length) : 배열 전체 복사해 새로운 배열을 만들어 반환
2. copyOfRange(Array, start, [end]) : 배열의 일부를 복사해 새로운 배열을 만들어 반환
3. 단, 지정된 범위의 끝은 포함되지 않음
4. 복사하려는 배열의 크기보다 큰 경우, 기존 배열의 크기보다 큰 나머지 인덱스는 기본값으로 저장
```java
int[] arr = {0, 1, 2, 3 ,4};
int[] arr2 = Arrays.copyOf(arr, arr.length); // arr[2] = [0, 1, 2, 3, 4]
int[] arr3 = Arrays.copyOf(arr, 3); // arr[3] = [0, 1, 2]
int[] arr4 = Arrays.copyOf(arr, 7); // arr[4] = [0, 1, 2, 3, 4, 0, 0]
int[] arr5 = Arrays.copyOfRange(arr, 2, 4); // arr[5] = [2, 3]
int[] arr6 = Arrays.copyOfRange(arr, 0, 7); // arr[6] = [0, 1, 2, 3, 4, 0, 0]
```

-----
### 배열 채우기 : Arrays.fill(), Arrays.setAll()
-----
1. fill(Array, Value) : 배열의 모든 요소를 지정된 값으로 채움
2. setAll(Array, Funtion Interface) : 배열을 채우는데 사용할 함수형 인터페이스를 매개변수로 받음
   - 즉, 메서드를 호출할 때 함수형 인터페이스를 구현한 객체를 매개변수로 지정 또는 람다식 지정
```java
int[] arr = new int[5];
Arrays.fill(arr, 9); // arr = [9, 9, 9, 9, 9]
Arrays.setAll(arr, () -> (int)(Math.random() * 5) + 1); // arr = [1, 5, 2, 1, 1]
```

-----
### 배열의 정렬 : Arrays.sort() / 배열의 탐색 : Arrays.binarySearch()
-----
1. Arrays.sort(Array) : 배열을 정렬할 때 사용
2. Arrays.binarySearch(Array, Value) : 배열에 저장된 요소를 검색할 때 사용
   - 배열에 지정된 값이 지정된 위치(index)를 찾아서 반환
   - 💡 반드시 배열이 정렬된 상태이어야 올바른 결과를 얻음
   - 만일 검색한 값과 일치하는 요소들이 여러 개 있다면, 이 중에서 어떤 것의 위치가 반환될지 알 수 없음
```java
int[] arr = {3, 2, 0, 1, 4};
int idx = Arrays.binarySearch(arr, 2); // idx = -5 (잘못된 결과)

Arrays.sort(arr);
System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4]
int idx = Arrays.binarySearch(arr, 2); // idx = 2 (올바른 결과)
```

3. 순차 검색(Linear Search) : 배열의 첫 번째 요소부터 순서대로 하나씩 검색
   - 해당 방법은 배열이 정렬될 필요가 없지만, 배열의 요소를 하나씩 비교하므로 시간이 많이 걸림
4. 이진 검색(Binary Search) : 배열을 검색할 범위를 반복적으로 절반씩 줄여가면서 검색하므로 검색 속도가 상당히 빠름
   - 배열의 길이가 10개 늘어나도 검색 횟수는 3~4회 밖에 늘어나지 않음
   - 큰 배열 검색에 상당히 유리
   - 단, 배열이 정렬 되어 있는 경우에만 사용 가능 (단점)

-----
### 배열의 비교 : Arrays.equals() / 배열의 출력 : Arrays.toString()
-----
1. toString(Array) : 배열의 모든 요소를 문자열에 출력
   - toString() : 일차원 배열에서만 사용 가능
2. deepToString(Array) : 다차원 배열에서 사용 가능
   - 배열의 모든 요소를 재귀적으로 접근해 문자열을 구성
```java
int[] arr = {0, 1, 2, 3, 4};
int[][] arr2D = {{11, 12}, {21, 22}};

System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4]
System.out.println(Arrays.deepToString(arr2D)); // [[11, 12], [13, 14]]
```

3. equals(Array1, Array2) : 두 배열에 저장된 모든 요소를 비교해 같으면 true, 다르면 false 반환
   - equals() : 일차원 배열에서만 사용 가능
4. deepEquals(Array1, Array2) : 다차원 배열의 비교에서 사용 (다차원 배열은 배열의 배열 형태이므로 문자열 비교가 아닌 '배열에 저장된 배열의 주소'를 비교)
   - 서로 다른 배열은 항상 다른 주소를 가짐
```java
String[][] str2D = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};
String[][] str2D2 = new String[][] {{"aaa", "bbb"}, {"AAA", "BBB"}};

System.out.println(Arrays.equals(str2D, str2D2); // false
System.out.println(Arrays.deepEquals(str2D, str2D2); // true
````

-----
### 배열을 List로 변환 - Arrays.asList(Object... a)
-----
1. asList(Obejct... a) : 배열을 List에 담아 반환
2. 매개변수 타입이 가변인수 : 배열 생성 없이 저장할 요소들만 나열하는 것도 가능
```java
List list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5}); // list = [1, 2, 3, 4, 5]
List list = Arrays.asList(1, 2, 3, 4, 5); // list = [1, 2, 3, 4, 5]
list.add(6); // UnsupportedOperationException 예외 발생
```
3. 단, asList()가 반환한 List의 크기를 변경할 수 없음 (즉, 추가 또는 삭제 불가)
4. 대신, 저장한 내용은 변경 가능
5. 따라서, 크기를 변경할 수 있는 List가 필요하다면 다음과 같이 설정
```java
List list = new ArrayList(Arrays.asList(1, 2, 3, 4, 5));
```

-----
### parallelXXX(), spliterator(), stream()
-----
1. parallel로 시작하는 메서드 : 보다 빠른 결과 처리를 위해 여러 쓰레드가 나누어 처리
2. spliterator() : 여러 쓰레드가 처리할 수 있게 하나의 작업을 여러 작업으로 나누는 Spliterator 반환
3. stream() : 컬렉션을 스트림으로 반환

```java
import java.util.*;

class ArraysEx {
	public static void main(String[] args) {
		int[]	arr   =  {0,1,2,3,4};
		int[][] arr2D =  {{11,12,13}, {21,22,23}};

		System.out.println("arr="+Arrays.toString(arr));
		System.out.println("arr2D="+Arrays.deepToString(arr2D));

		int[] arr2 = Arrays.copyOf(arr, arr.length);
		int[] arr3 = Arrays.copyOf(arr, 3);          
		int[] arr4 = Arrays.copyOf(arr, 7);          
		int[] arr5 = Arrays.copyOfRange(arr, 2, 4);  
		int[] arr6 = Arrays.copyOfRange(arr, 0, 7);  

		System.out.println("arr2="+ Arrays.toString(arr2));
		System.out.println("arr3="+ Arrays.toString(arr3));
		System.out.println("arr4="+ Arrays.toString(arr4));
		System.out.println("arr5="+ Arrays.toString(arr5));
		System.out.println("arr6="+ Arrays.toString(arr6));

		int[] arr7 =  new int[5];
		Arrays.fill(arr7, 9);  // arr=[9,9,9,9,9]
		System.out.println("arr7="+Arrays.toString(arr7));

		Arrays.setAll(arr7, i -> (int)(Math.random()*6)+1);
		System.out.println("arr7="+Arrays.toString(arr7));

		for(int i : arr7) {
			char[] graph = new char[i];
			Arrays.fill(graph, '*');
			System.out.println(new String(graph)+i);
		}

		String[][] str2D  = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};
		String[][] str2D2 = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};

		System.out.println(Arrays.equals(str2D, str2D2));      // false
		System.out.println(Arrays.deepEquals(str2D, str2D2));  // true

		char[] chArr = { 'A', 'D', 'C',  'B', 'E' };

		int idx = Arrays.binarySearch(chArr, 'B');
		System.out.println("chArr="+Arrays.toString(chArr));
		System.out.println("index of B ="+Arrays.binarySearch(chArr, 'B'));
		System.out.println("= After sorting =");
		Arrays.sort(chArr);
		System.out.println("chArr="+Arrays.toString(chArr));
		System.out.println("index of B ="+Arrays.binarySearch(chArr, 'B'));
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/556b9650-ee5f-4419-97f5-2ef165441bb9">
</div>
