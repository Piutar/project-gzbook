package com.gzbook.service.logcomment;

import com.gzbook.model.comment.LogComment;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogCommentService {
    LogComment saveLogComment(LogComment logComment);

    Iterable<LogComment> findAllLogCommentByCommentId(Long commentId);
}