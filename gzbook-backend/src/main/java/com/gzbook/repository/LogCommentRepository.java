package com.gzbook.repository;

import com.gzbook.model.comment.LogComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogCommentRepository extends JpaRepository<LogComment, Long> {
}
