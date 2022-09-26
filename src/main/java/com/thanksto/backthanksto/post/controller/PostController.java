package com.thanksto.backthanksto.post.controller;

import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.post.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // post 전체 조회
    @GetMapping("/list/all")
    public List<Post> getPostList() {
        return this.postService.getPostList();
    }
}
