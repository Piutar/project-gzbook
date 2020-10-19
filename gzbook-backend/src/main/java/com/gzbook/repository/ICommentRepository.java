package com.gzbook.repository;

import com.gzbook.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findCommentsByPostId(Long postId);

    Iterable<Comment> findCommentsById(Long Id);
}