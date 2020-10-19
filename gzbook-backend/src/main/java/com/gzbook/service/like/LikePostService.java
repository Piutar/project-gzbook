package com.gzbook.service.like;

import com.gzbook.model.like.LikePost;
import com.gzbook.repository.LikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService implements ILikePostService {
    @Autowired
    LikePostRepository likePostRepository;

    @Override
    public LikePost saveLikePost(LikePost LikePost) {
        return likePostRepository.save(LikePost);
    }

    @Override
    public void deleteLikePost(Long id) {
        likePostRepository.deleteById(id);
    }

    @Override
    public Iterable<LikePost> findAllLikePost() {
        return likePostRepository.findAll();
    }

    @Override
    public LikePost findLikePostById(Long id) {
        return likePostRepository.getOne(id);
    }

    @Override
    public Iterable<LikePost> findLikePostByPostId(Long postId) {
        return likePostRepository.findLikePostsByPostId(postId);
    }
}
