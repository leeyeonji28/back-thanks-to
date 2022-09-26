package com.thanksto.backthanksto.post.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String postImg;

    @Column(length = 200)
    private String postTitle;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    private LocalDateTime postDate;

    private String filename;
    private String filepath;

    private long postLike;

    @Builder
    public Post(String postImg, String postTitle, String postContent){
        this.postImg = postImg;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postDate = LocalDateTime.now();
        this.postLike = 0;
    }

    public static Post createPost(CreatePostDto createPostDto) {
        return Post.builder()
                .postImg(createPostDto.getPostImg())
                .postTitle(createPostDto.getPostTitle())
                .postContent(createPostDto.getPostContent())
                .build();
    }
}
