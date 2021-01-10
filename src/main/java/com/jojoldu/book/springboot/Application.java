package com.jojoldu.book.springboot;
//패키지 가져오 mac 단축키 option + enter
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class 는 메인 클래스 역할
 * @SpringBootApplication 스프링 부트의 자동설정, 스프링 bean 읽기와 생성을 모두 자동으로 설정
 * @SpringBootApplication 이 있는 위치부처 설정을 읽어 가기 때문에 클래스는 항상 프로젝트의 최상단에 위치해야한다.
 * main 메소드에서 실행하는 SpringApplication.run 으로 인해 내장 WAS를 실행합니다.
 * 내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행 하는 것을 이야기합니다.
 * 톰캣 설정없이 스프링 부트로 만들어진 Jar 파일(실행가능한 java 패키징 파일)로  실행하면 됩니다.
 * 스프링 부트는 내장 WSA를 사용 권장
 * 언제나 같은 환경에서 스프링 부트를 배포할수 있기 때문
 * 성능 이슈는 톰캣과 동일한 코드를 사용하는 서블릿으로 이루어진 자바 애플리케이션의로 성능상 이슈는 고려 안해도 됨
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}