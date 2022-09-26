package com.thanksto.backthanksto.post.dao;

import com.thanksto.backthanksto.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
