package com.gzbook.repository;

import com.gzbook.model.like.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Iterable<LikeComment> findLikeCommentsByCommentId(Long commentId);
}
