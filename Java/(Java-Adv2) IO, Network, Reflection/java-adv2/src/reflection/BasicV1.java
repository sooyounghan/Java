package reflection;

import reflection.data.BasicData;

public class BasicV1 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 메타데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class<BasicData> basicDataClass1 = BasicData.class; // Class 객체 생성 (Class 정보 저장)
        System.out.println("basicDataClass1 = " + basicDataClass1);
        
        // 2. 인스턴스에서 찾기
        BasicData basicInstance = new BasicData();
        Class<? extends BasicData> basicDataClass2 = basicInstance.getClass(); // getClass() [BasicData와 그 자식까지 가능]
        System.out.println("basicDataClass2 = " + basicDataClass2);
        
        // 3. 문자로 찾기
        String className = "reflection.data.BasicData"; // 패키지명까지 작성
        Class<?> basicDataClass3 = Class.forName(className); // Class.forName() 메서드 사용
        System.out.println("basicDataClass3 = " + basicDataClass3);
    }
}
