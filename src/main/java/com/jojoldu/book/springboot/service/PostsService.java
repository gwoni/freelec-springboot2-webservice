package com.jojoldu.book.springboot.service;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Audowired 권장하지 않음
 * 생성자로 주입받는 방식 권장, 생성자로 bean 객체를 받도록 하여 @Audowired와 동일한 효과를 볼수 있다
 * @RequiredArgsConstructor 에서 final이 선언된 모든 필드를 인자값으로 하는 생성자를 롬복의
 * @RequiredArgsConstructor 가 대신 생성자를 생성해줍니다.
 *
 * update 기능에서 데이터에 쿼리를 날리는 부분이 없습니다.이게 가능한 이유는 JPA의 영속성 컨텍스트 때문입니다.
 * 영속서 컨텍스트란 엔티티를 영구저장하는 환경입니다.
 * 일종의 논리적 개념이라고 보시면 되며, JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈립니다.
 * JPA의 엔티티 매니저(EntityManager)가 활성화 된 상태로(Spring Data Jpa를 쓴다면 기본 옵션0 트랜젝션 안에서 데이터베이스에서
 * 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태입니다.
 * 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영합니다.
 * 즉 Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것이죠.
 * 이 개념을 더티 체킹 (dirty checking)이라고 합니다.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    /**
     * readOnly = true : 트랜재션 범위는 유지하되, 조회기능만 남겨두어 조회 속도가 개선, 등록,수정,삭제 기능이 없는 서비스 메소드에서 사용 추천
     * @return
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /**
     * JpaRepository 에서 이미 delete메소드를 지원하고 있으니 활용
     * 엔티티를 파라미터로 삭제할 수 도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있습니다.
     * 존재하는 Posts인지 확인 을 위해 엔티티 조회 후 그대로 삭제합니다.
     *
     * @param id
     */
    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}