package com.thanksto.backthanksto.post.sevice;

import com.thanksto.backthanksto.post.dao.PostRepository;
import com.thanksto.backthanksto.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPostList() {
        return this.postRepository.findAll();
    }
}
