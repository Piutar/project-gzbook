package com.gzbook.service.like;

import com.gzbook.model.like.LikeComment;
import com.gzbook.repository.LikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeCommentService implements ILikeCommentService {

    @Autowired
    LikeCommentRepository likeCommentRepository;


    @Override
    public LikeComment saveLikeComment(LikeComment likeComment) {
        return likeCommentRepository.save(likeComment);
    }

    @Override
    public void deleteLikeComment(Long id) {
        likeCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<LikeComment> findAllLikeComment() {
        return likeCommentRepository.findAll();
    }

    @Override
    public LikeComment findLikeCommentById(Long id) {
        return likeCommentRepository.getOne(id);
    }

    @Override
    public Iterable<LikeComment> findLikeCommentsByCommentId(Long id) {
        return likeCommentRepository.findLikeCommentsByCommentId(id);
    }

}
