### cmd 내 자바 컴파일 및 실행
  1. JAVA / JAVAC : JAVA Complier (java-version\bin\… : Java 실행 시 필요한 실행파일 제공)
  2. Complie Processing : .java → JAVAC → .class
-------
### JAVA 변수 설정
 1. 시스템 변수 설정 : JAVA_HOME 변수 Java Home Directory(..\dk-version(ex)jdk-11))를 추가해 설정
 2. 환경 변수 설정 : PATH → %JAVA_HOME%\bin : JAVA의 컴파일러 디렉토리 설정
 
     + ###### %변수%, \ : 하위 디렉토리
    
     + ###### ; : 구분자 ( C:\Program Files\Common Files\Oracle\Java\javapath;%JAVA_HOME%\bin;…)
    
 5. Java 버전 확인 : java –version OR javac –version
