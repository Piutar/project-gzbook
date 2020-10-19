package com.gzbook.service.comment;

import com.gzbook.model.comment.Comment;
import com.gzbook.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findCommentByCommentId(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Comment> findCommentsByPostId(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    @Override
    public Iterable<Comment> findCommentById(Long id) {
        return commentRepository.findCommentsById(id);
    }
}
