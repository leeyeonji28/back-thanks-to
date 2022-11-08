package com.thanksto.backthanksto.post.domain;

import com.thanksto.backthanksto.user.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String postImg;

    @Column(length = 200)
    private String postTitle;

    @Column(columnDefinition = "TEXT")
    private String postContent;

    private LocalDate postDate;

    private long postLike;

    @Column(length = 50)
    private String postLock;

    @ManyToOne() // many = post, one = user
    @JoinColumn(name = "userId") // db에 userid로 저장됨
    private User user;

    @Builder
    public Post(String postImg, String postTitle, String postContent, String postLock, User user){
        this.postImg = postImg;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postDate = LocalDate.now();
        this.postLike = 0;
        this.postLock = postLock;
        this.user = user;
    }

    public static Post createPost(CreatePostDto createPostDto, User user) {
        return Post.builder()
                .postImg(createPostDto.getPostImg())
                .postTitle(createPostDto.getPostTitle())
                .postContent(createPostDto.getPostContent())
                .postLock(createPostDto.getPostLock())
                .user(user)
                .build();
    }
}
