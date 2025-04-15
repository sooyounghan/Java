package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {
    // 자바가 기본적으로 제공하는 타입만 설정 가능
    String value();
    int count() default 0;
    String[] tags() default {};

    // MyLooger data(); // 다른 타입은 적용 불가
    Class<? extends MyLogger> annoData() default MyLogger.class; // Class에 대한 메타데이터 정보는 사용 가능
}
