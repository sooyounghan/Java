-----
### DAO(Date Access Object)
-----
1. DB에 접근하기 위한 객체
2. 직접 DB에 접근하여 Data를 삽입(INSERT), 삭제(DELETE), 조회(SELECT), 변경(UPDATE) 등 조작할 수 있는 기능(즉, Query를 수행)을 수행하는 클래스

-----
### DTO(Data Transfer Object)
-----
1. 계층 간(Controller, View, Business Layer)[쉽게 생각하면, DB-DB 내 데이터 이동] 데이터 교환을 위한 클래스
2. DTO는 로직을 가지지 않는 데이터 객체
3. getter, setter[두 메서드는 값을 얻거나 설정] 메소드만 가진 클래스를 의미
4. VO와는 다르게 가변의 성격을 가지며, 데이터 전송을 위해 존재

-----
### VO (Value Object)
-----
1. Read-Only 속성을 가진 값을 가진 객체
2. 단순히 값 타입을 표현하기 위하여 불변 클래스(Read-Only)를 만들어 사용  →  final
3. getter 기능만 사용
4. 리터럴(Literal) 개념
5. 필요에 따라 Object 클래스의 hashCode(), equals()를 오버라이딩 필요

```java
import java.util.*;

public class StudentDTO {
	// Field : [Access Modifier][Modifier] DataType Field_Name[=Initial value];
	private String sno; // 학번
	private String sname; // 학생 이름
	private Date enrollmentDate; // 입학일
 	
	// Constructor : [Access Modifier] Class_name(argument_list) { }
	public StudentDTO() {
		this(null, null, null);
	}
	
	public StudentDTO(String sno, String sname, Date enrollmentDate) {
		this.sno = sno;
		this.sname = sname;
		this.enrollmentDate = enrollmentDate;
	}
	
	// Method : [Access Modifier][Modifier] ReturnType Method_name(argument_list) { }
	public String getSname() { // getter만 가지고 있으면 VO
		return this.sname;
	}
	
	public String getSno() { // getter만 가지고 있으면 VO
		return this.sno;
	}
	
	public Date getEnorollmentDate() { // getter만 가지고 있으면 VO
		return this.enrollmentDate;
	}
	
	public void setSname(String sname) { // getter와 setter를 가지고 있으면 DTO
		this.sname = sname;
	}
	
	public void setSno(String sno) { // getter와 setter를 가지고 있으면 DTO
		this.sno = sno;
	}
	
	public void setEnrollment(Date enrollmentDate) { // getter와 setter를 가지고 있으면 DTO
		this.enrollmentDate = enrollmentDate;
	}
	
	@Override
	public String toString() {
		return "[" + this.sno + ", " + this.sname + ", " + this.enrollmentDate + "]";
	}
}
```

```java
import java.util.*;

/*
 * StudnetDTO를 List, Set, Map에 넣고 출력
 */
public class CFE_withDTO {
	public static void main(String[] args) {
		System.out.println("====List에 StudentDTO 객체 추가====");
		
		List<StudentDTO> list_dto = new ArrayList<StudentDTO>(); // 객체 StudentDTO를 담는 ArrayList
		
		list_dto.add(new StudentDTO("123456", "ABC", new Date())); // 객체 생성과 동시에 ArrayList에 삽입
		list_dto.add(new StudentDTO("345678", "BCD", new Date()));
		list_dto.add(new StudentDTO("900554", "CDF", new Date()));

		for(int i = 0; i < list_dto.size(); i++) {
			System.out.println(list_dto.get(i).toString()); // toString() Override을 통해 ArrayList 요소 출력
		}
		
		System.out.println("====Set에 StudentDTO 객체 추가====");
		
		Set<StudentDTO> set_dto = new HashSet<StudentDTO>();
		
		StudentDTO student1 = new StudentDTO(); // 기본 생성자를 통한 StudentDTO 객체 생성
		student1.setSno("123456");
		student1.setSname("ABC");
		student1.setEnrollment(new Date()); // Setter를 통한 초기화
		
		StudentDTO student2 = new StudentDTO(); // 기본 생성자를 통한 StudentDTO 객체 생성
		student2.setSno("345678");
		student2.setSname("BCD");
		student2.setEnrollment(new Date()); // Setter를 통한 초기화
		
		StudentDTO student3 = new StudentDTO(); // 기본 생성자를 통한 StudentDTO 객체 생성
		student3.setSno("900554");
		student3.setSname("CDF");
		student3.setEnrollment(new Date()); // Setter를 통한 초기화
		
		set_dto.add(student1); // student1 객체 삽입
		set_dto.add(student2);
		set_dto.add(student3);

		for(StudentDTO student : set_dto) { // 향상된 for문을 통한 set 요소 추출
			System.out.println(student.toString());
		}
		
		System.out.println();
		
		Iterator<StudentDTO> iterator = set_dto.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());		
		}

		System.out.println("====Map에 StudentDTO 객체 추가====");
		
		Map<String, StudentDTO> map_dto = new HashMap<String, StudentDTO>(); // Key는 학생의 학번
		
		map_dto.put(student1.getSno(), student1);
		map_dto.put(student2.getSno(), student2);
		map_dto.put(student3.getSno(), student3);
		
		Set<Map.Entry<String, StudentDTO>> entrySet = map_dto.entrySet();
		Iterator<Map.Entry<String, StudentDTO>> iter = entrySet.iterator();
		
		while(iter.hasNext()) {
			Map.Entry<String, StudentDTO> entry = iter.next();
			System.out.println(entry.getValue().toString());
		}
	}
}
```
