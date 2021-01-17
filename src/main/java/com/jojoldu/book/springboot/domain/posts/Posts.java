package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Entity : JPA 어노테이션션 ,테이블과 링크될 클래스임을 나타낸니다.
 *      기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
 *      ex) SalesManager.java -> sales-manager table
 * @Getter, @NoArgsConstructor 롬복 어노테이용션
 * @NoArgsConstructor : 기본 생성자 자동 추가, public Posts() {} 와 같은 효과
 * @Getter : 클래스 내 모든 필드의 Getter 메소드를 자동 생성
 * @Builder : 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
 * @Id : 해당 테이블의 PK 필드를 나타냅니다.
 * @GeneratedValue : PK의 생성 규칙을 나타냅니다.
 *      스프링부트 2.0 에서는 GenerationType.IDNTITY 옵션을 추가해야만 audo_increment 됩니다.
 * @Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 ㅣㄹ드는 모두 칼럼이 됩니다.
 *      사용이유는 기본값 이외 추가 변경이 필요한 옵션이 있을때 사용합니다.
 *      문자열 경우 VARCHAR(255)가 기본, 사이즈를 500으로 늘리고 싶거나 (ex:title) , 타입을 TEXT로 변경하고 싶거나 (ex: content) 등 사
 *
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts
        extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}