package com.gzbook.service.comment;

import com.gzbook.model.comment.Comment;

public interface ICommentService {
    Comment saveComment(Comment comment);

    void deleteComment(Long id);

    Iterable<Comment> findAllComment();

    Comment findCommentByCommentId(Long id);

    Iterable<Comment> findCommentsByPostId(Long postId);

    Iterable<Comment> findCommentById(Long id);
}

