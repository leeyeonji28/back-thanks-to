package com.thanksto.backthanksto.post.domain;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Data
public class CreatePostDto {
    private String postImg;
    private String postTitle;
    private String postContent;
    private Long id;

    @Builder
    public CreatePostDto(String postImg, String postTitle, String postContent, Long id){
        this.postImg = postImg;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.id = id;
    }
}
