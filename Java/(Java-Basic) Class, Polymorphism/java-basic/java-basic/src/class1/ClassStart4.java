package class1;

public class ClassStart4 {
    public static void main(String[] args) {
        Student student1 = new Student();

        // 객체 값 대입
        student1.name = "학생1";
        student1.age = 15;
        student1.grade = 90;

        Student student2 = new Student();

        student2.name = "학생2";
        student2.age = 16;
        student2.grade = 80;

        System.out.println("student1 = " + student1);
        System.out.println("student2 = " + student2);

        Student[] students = new Student[2];
        students[0] = student1;
        students[1] = student2;

        // 객체 값 사용
        System.out.println("이름 : " + students[0].name + ", 나이 : " + students[0].age + ", 성적 : " + students[0].grade);
        System.out.println("이름 : " + students[0].name + ", 나이 : " + students[0].age + ", 성적 : " + students[0].grade);
    }
}
