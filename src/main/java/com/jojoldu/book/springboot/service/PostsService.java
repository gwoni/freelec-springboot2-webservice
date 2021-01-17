package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Audowired 권장하지 않음
 * 생성자로 주입받는 방식 권장, 생성자로 bean 객체를 받도록 하여 @Audowired와 동일한 효과를 볼수 있다
 * @RequiredArgsConstructor 에서 final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의
 * @RequiredArgsConstructor 가 대신 생성자를 생성해줍니다.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}