package com.gzbook.service.like;

import com.gzbook.model.like.LikePost;

import java.util.List;

public interface ILikePostService {
    LikePost saveLikePost(LikePost LikePost);

    void deleteLikePost(Long id);

    Iterable<LikePost> findAllLikePost();

    LikePost findLikePostById(Long id);

    Iterable<LikePost> findLikePostByPostId(Long postId);
}
