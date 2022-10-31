package com.thanksto.backthanksto.post.dao;

import com.thanksto.backthanksto.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostTitleContaining(String searchKeyword);
}
