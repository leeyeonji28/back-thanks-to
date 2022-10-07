package com.thanksto.backthanksto.post.domain;

import com.thanksto.backthanksto.user.domain.User;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Data
public class CreatePostDto {
    private String postImg;
    private String postTitle;
    private String postContent;
    private User user;

    @Builder
    public CreatePostDto(String postImg, String postTitle, String postContent, User user){
        this.postImg = postImg;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.user = user;
    }
}
