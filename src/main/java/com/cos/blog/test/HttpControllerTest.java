package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RequestMapping("/http")
@RestController
public class HttpControllerTest {

    @GetMapping("/get")
    public String getTest() {
        return "get 요청";
    }

    @PostMapping("/post")
    public String postTest(@RequestBody Member member) {
        return "post 요청 : " + member.toString();
    }

    @PutMapping("/put")
    public String putTest(@RequestBody Member member) {
        return "put 요청" + member.toString();
    }

    @DeleteMapping("/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
