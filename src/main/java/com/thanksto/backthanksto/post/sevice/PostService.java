package com.thanksto.backthanksto.post.sevice;

import com.thanksto.backthanksto.post.DataNotFoundException;
import com.thanksto.backthanksto.post.dao.PostRepository;
import com.thanksto.backthanksto.post.domain.CreatePostDto;
import com.thanksto.backthanksto.post.domain.Post;
import com.thanksto.backthanksto.user.dao.UserRepository;
import com.thanksto.backthanksto.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // post 전체 조회
    public List<Post> getPostList() {
        return this.postRepository.findAll();
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
}
