package com.thanksto.backthanksto.post.controller;

import com.thanksto.backthanksto.post.domain.CreatePostDto;
import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.post.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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

    // post 생성
//    @Value("${resource.path}")
//    private String projectPath;

    @PostMapping(value = "/{id}/post/create", consumes = {"multipart/form-data"})
    public String CreatePost(@RequestPart(value = "createPostDto")CreatePostDto createPostDto,
                             @RequestPart(value = "postImage") MultipartFile postImage, @PathVariable Long id) throws Exception{

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/images/"; // 저장 경로

        String fileName = postImage.getOriginalFilename(); // 원래 file 이름

        UUID uuid = UUID.randomUUID(); // file 이름이 겹치지 않기 위해 uuid 사용

        String saveFileName = uuid + "_" + fileName; // uuid와 file 이름을 합쳐서 저장

        String filePath = projectPath + File.separator + saveFileName;

        postImage.transferTo(new File(filePath));

        String fileUrl = "http://localhost:8092/" + saveFileName;

        createPostDto.setPostImg(fileUrl);

        this.postService.create(createPostDto, id);

        return "post created";
    }

}
