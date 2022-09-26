package com.thanksto.backthanksto.post.sevice;

import com.thanksto.backthanksto.post.DataNotFoundException;
import com.thanksto.backthanksto.post.dao.PostRepository;
import com.thanksto.backthanksto.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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
}
