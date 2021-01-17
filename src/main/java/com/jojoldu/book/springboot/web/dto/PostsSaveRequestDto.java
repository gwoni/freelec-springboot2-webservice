package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가적으로 생성했습니다.
 * 하지만, 절대로 Entity 클래스를 Request/Response클래스로 사용해서는 안됩니다.
 * Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스 입니다.
 * Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경됩니다.
 * 화면 변경은 아주 사소한 기능 변경인데, 이를 위해 테이블과 연결된 Entity 클래스를 변경하는 건 아주 큰 변경입니다.
 * View Layer 와 DB Layer 의 역할 분리를 철저하게 하는 게 좋습니다.
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}