-----
### break문
-----
1. 자신이 포함된 가장 가까운 반복문을 벗어나는 것
2. 주로, if문과 함께 사용되어 특정 조건을 만족하면 반복문을 벗어나도록 함
```java
class FlowEx30 {
	public static void main(String[] args) { 
		int sum = 0;
		int i   = 0;

		while(true) {
			if(sum > 100)
				break; // break문이 실행되면, 해당 아래 문장 미실행하고, while문을 완전히 벗어나게 됨
			++i;
			sum += i;
		} // end of while

		System.out.println("i=" + i);
		System.out.println("sum=" + sum);
	}   
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e6b314de-c12f-400c-a58f-7b4426265046">
</div>

  - 숫자를 1부터 계속 더해 나가서 몇까지 더하면 합이 100이 되는지 알아내는 예제
  - 즉, sum의 값이 100을 넘으면 if문의 조건식은 참이므로 break문이 수행되어 자신이 속한 반복문을 즉시 벗어남
  - 이처럼, 무한 반복문에는 조건문과 break문이 항상 같이 사용

-----
### continue 문
-----
1. 반복문 내에서만 사용 가능
2. 반복이 진행되는 도중에 continue문을 만나면 반복문의 끝으로 이동해 다음 반복으로 넘어감
3. for문의 경우는 증감식으로 이동, while문과 do-while문은 조건식으로 이동
4. 반복문을 전체 벗어나지 않고, 다음 반복을 계속 수행하는 점이 break문과 다름
5. 주로 if문과 함께 사용되며 특정 조건을 만족하는 경우 continue 이후 문장들을 수행하지 않고, 다음 반복으로 넘어가서 계속 진행
6. 즉, 전체 반복 중 특정 조건을 만족하는 경우를 제외하고자할 때 유용
```java
class FlowEx31 {
	public static void main(String[] args) {
		for(int i=0;i <= 10;i++) {
			if (i%3==0)
				continue; // 조건식이 참이 되어 conitnue문을 수행하면 블럭의 끝으로 이동하나 break문과 달리 반복문을 벗어나지 않음
			System.out.println(i);
		}
	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/e9beeadd-aa1c-4955-b0e0-a5a419b9fd24">
</div>

  - 1과 10사이 숫자를 출력하되 그 중 3의 배수는 제외하여 출력하는 예제

```java
import java.util.*;

class FlowEx32 {
	public static void main(String[] args) { 
		int menu = 0;
		int num  = 0;

		Scanner scanner = new Scanner(System.in);

		while(true) {
			System.out.println("(1) square");
			System.out.println("(2) square root");
			System.out.println("(3) log");
			System.out.print("원하는 메뉴(1~3)을 선택하세요.(종료:0)>");

			String tmp = scanner.nextLine(); // 화면에서 입력받은 내용을 tmp에 저장
			menu = Integer.parseInt(tmp);    // 입력받은 문자열(tmp)을 숫자로 변환

			if(menu==0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (!(1 <= menu && menu <= 3)) {
				System.out.println("메뉴를 잘못 선택하셨습니다.(종료는 0)");
				continue;		
			}
			
			System.out.println("선택하신 메뉴는 "+ menu +"입니다.");
		}
	} // main
}
```

-----
### 이름 붙은 반복문
-----
1. break문은 단 하나의 근접한 반복문을 벗어나므로 여러 개의 반복문이 중첩된 경우에는 break문으로 중첩 반복문을 벗어날 수 없음
2. 이럴 경우, 중첩 반복문 앞에 이름을 붙이고 break문과 contiune문에 이름을 지정함으로써 하나 이상의 반복문을 벗어나거나 반복을 건너뛸 수 있음
```java
class FlowEx33 {
	public static void main(String[] args)
	{
        // for문에 Loop1이라는 이름을 붙임.
		Loop1 : for(int i=2;i <=9;i++) {	
				for(int j=1;j <=9;j++) {
					if(j==5)
						break Loop1; // Loop1 반복문 탈출
//						break; // 내부 for문 탈출
//						continue Loop1; // end of Loop1로 이동
//						continue; // end of for i로 이동
					System.out.println(i+"*"+ j +"="+ i*j);
				} // end of for i
				System.out.println();
		} // end of Loop1

	}
}
```
<div align="center">
<img src="https://github.com/sooyounghan/Java/assets/34672301/58ebf633-372f-4ef4-8369-f6fb64fb4f97">
</div>
