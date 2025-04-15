package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited // 클래스 상속 시, 자식도 애너테이션 적용
@Retention(RetentionPolicy.RUNTIME)
public @interface InhertiedAnnotation {
}
