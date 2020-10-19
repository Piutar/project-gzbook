package com.gzbook.repository;

import com.gzbook.model.comment.LogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogCommentRepository extends JpaRepository<LogComment, Long> {
    Iterable<LogComment> findAllByCommentId(Long commentId);
}
