package kr.co.log.common;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomUser 에서 등록된 member 변수 값이 user 이기 떄문에 user 로 선언
 * spring security @AuthenticationPrincipal 관련 자료를 참고하면 Account 로 설정된 것들이 많기 때문에 혼선이 생길 수 있음
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
public @interface CurrentUser {
}
