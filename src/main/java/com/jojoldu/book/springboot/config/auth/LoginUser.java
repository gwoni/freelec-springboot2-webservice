package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(ElementType.PARAMETER) : 이 어노테이션이 생성될 수 있는 위치를 지정합니다
 *  PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있습니다
 *  이 이외도 클래스 선언문에 쓸수 있는 type등이 있습니다
 * @interface : 이 파일을 어노테이션 클래스로 지정합니다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}