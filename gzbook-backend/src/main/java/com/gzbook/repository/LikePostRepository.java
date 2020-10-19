package com.gzbook.repository;

import com.gzbook.model.like.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Iterable<LikePost> findLikePostsByPostId(Long postId);

}
