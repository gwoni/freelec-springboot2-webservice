package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /**
     * Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
     *  여기서는 postsService.findAllDesc()로 가져온 결과를 posts index.mustache 에 전달합니다.
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
