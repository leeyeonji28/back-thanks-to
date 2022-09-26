package com.thanksto.backthanksto.post.controller;

import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.post.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // post 전체 조회
    @GetMapping("/post/list/all")
    public List<Post> getPostList() {
        return this.postService.getPostList();
    }

    // post 단건 조회
    @GetMapping("/post/detail/{postId}")
    public Post getPostDetail(@PathVariable Long postId){
        return this.postService.getPostDetail(postId);
    }


}
