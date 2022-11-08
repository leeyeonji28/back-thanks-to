package com.thanksto.backthanksto.post.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class UpdatePostDto {
    private String postImg;
    private String postTitle;
    private String postContent;
    private String postLock;
}
