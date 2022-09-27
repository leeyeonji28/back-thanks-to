package com.thanksto.backthanksto.post.domain;

import com.thanksto.backthanksto.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
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

    private long postLike;

    @ManyToOne()
    @JoinColumn(name = "id")
    private User user;

    @Builder
    public Post(String postImg, String postTitle, String postContent, User user){
        this.postImg = postImg;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postDate = LocalDateTime.now();
        this.postLike = 0;
        this.user = user;
    }

    public static Post createPost(CreatePostDto createPostDto, User user) {
        return Post.builder()
                .postImg(createPostDto.getPostImg())
                .postTitle(createPostDto.getPostTitle())
                .postContent(createPostDto.getPostContent())
                .user(user)
                .build();
    }
}
