package com.gzbook.service.like;

import com.gzbook.model.like.LikeComment;
import com.gzbook.model.like.LikePost;

import java.util.List;

public interface ILikeCommentService {
    LikeComment saveLikeComment(LikeComment likeComment);

    void deleteLikeComment(Long id);

    Iterable<LikeComment> findAllLikeComment();

    LikeComment findLikeCommentById(Long id);

    Iterable<LikeComment> findLikeCommentsByCommentId(Long id);
}
