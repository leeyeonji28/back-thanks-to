package com.thanksto.backthanksto.post.controller;

import com.thanksto.backthanksto.post.domain.CreatePostDto;
import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.post.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
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
    public Optional<Post> getUserPostDetail(@PathVariable Long postId) {
        return this.postService.getUserPostDetail(postId);
    }

    // post 생성
    @PostMapping(value = "/{id}/post/create", consumes = {"multipart/form-data"})
    public String CreatePost(@RequestPart(value = "createPostDto")CreatePostDto createPostDto,
                             @RequestPart(value = "postImage") MultipartFile postImage, @PathVariable Long id) throws Exception{

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/"; // 저장 경로


        if (!new File(projectPath).exists()){
            try{
                new File(projectPath).mkdirs();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        String fileName = postImage.getOriginalFilename(); // 원래 file 이름
        if (fileName != ""){
            UUID uuid = UUID.randomUUID(); // file 이름이 겹치지 않기 위해 uuid 사용

            String saveFileName = uuid + "_" + fileName; // uuid와 file 이름을 합쳐서 저장

            String filePath = projectPath + File.separator + saveFileName;

            postImage.transferTo(new File(filePath));

            String fileUrl = "http://localhost:8092/" + saveFileName;

            createPostDto.setPostImg(fileUrl);
        } else {
            createPostDto.setPostImg("");
        }

        this.postService.create(createPostDto, id);

        return "post created";
    }

}
