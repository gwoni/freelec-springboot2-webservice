package com.jojoldu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestControoler : 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줍니다.
 * 예전에는 @ResponseBody를 메소드 마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 됩니다.
 *
 * @GetMapping HTTP Method 인 Get 을  요청을 받을 수 있는 API를 만들어 줍니다.
 * 예전에는 @RequestMapping(method = RequestMethod.Get)으로 사용되었습니다.
 * 이제 이프로젝트는 /hello 요청이 오면 문자열로 hello를 반환하는 기능을 가지게 되었습니다.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}