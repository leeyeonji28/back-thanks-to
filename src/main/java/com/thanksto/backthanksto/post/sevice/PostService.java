package com.thanksto.backthanksto.post.sevice;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.thanksto.backthanksto.post.DataNotFoundException;
import com.thanksto.backthanksto.post.dao.PostRepository;
import com.thanksto.backthanksto.post.domain.CreatePostDto;
import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.post.domain.UpdatePostDto;
import com.thanksto.backthanksto.user.dao.UserRepository;
import com.thanksto.backthanksto.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    // post 전체 조회
    public List<Post> getPostList() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Post getPostDetail(Long postId) {
        Optional<Post> post = this.postRepository.findById(postId);
        if (post.isPresent()){
            return post.get();
        } else {
            throw new DataNotFoundException("Post not found");
        }
    }

    public Long create(CreatePostDto createPostDto, Long id) throws Exception{

        User user = userRepository.findById(id).get();

        Post newPost = Post.createPost(createPostDto, user);

        postRepository.save(newPost);

        return newPost.getId();
     }

    public Optional<Post> getUserPostDetail(Long postId) {
        return this.postRepository.findById(postId);
    }

    public void postDelete(Long postId) {
        Post deletePost = postRepository.findById(postId).get();
        this.postRepository.delete(deletePost);
    }

    public Post postUpdate(UpdatePostDto updatePostDto, Long postId) {
        Post updatePost = postRepository.findById(postId).get();

        updatePost.setPostTitle(updatePostDto.getPostTitle());
        updatePost.setPostContent(updatePostDto.getPostContent());
        updatePost.setPostImg(updatePostDto.getPostImg());
        updatePost.setPostLock(updatePostDto.getPostLock());

        postRepository.save(updatePost);

        return updatePost;
    }

    public void setLike(Long postId) {
        Post post = postRepository.findById(postId).get();
        post.setPostLike(post.getPostLike() + 1);
        this.postRepository.save(post);
    }

    public List<Post> getPostSearchList(String searchKeyword) {
        List<Post> posts = postRepository.findByPostTitleContaining(searchKeyword);
        return posts.stream().filter(post -> post.getPostLock().equals("false")).collect(Collectors.toList());
    }

    public List<Post> getPostLikeList() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "postLike"));
    }

    // aws test
    public void awsUploadTest(MultipartFile imageSrc) throws IOException {
        String s3FileName = String.valueOf(UUID.randomUUID());
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(imageSrc.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, imageSrc.getInputStream(), objMeta);
    }
}
