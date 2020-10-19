package com.gzbook.controller;

import com.gzbook.model.comment.Comment;
import com.gzbook.model.like.LikeComment;
import com.gzbook.model.user.User;
import com.gzbook.service.comment.ICommentService;
import com.gzbook.service.like.ILikeCommentService;
import com.gzbook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/like/comment")
public class LikeCommentController {
    @Autowired
    ILikeCommentService likeCommentService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<LikeComment> createLikeComment(@RequestBody LikeComment likeComment) {
        Comment comment = this.commentService.findCommentByCommentId(likeComment.getCommentId());
        comment.setCommentLike(comment.getCommentLike() + 1);
        this.commentService.saveComment(comment);
        return new ResponseEntity<>(likeCommentService.saveLikeComment(likeComment), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LikeComment> updateLikeComment(@PathVariable Long id, @RequestBody LikeComment likeComment) {
        likeComment.setId(id);
        return new ResponseEntity<>(likeCommentService.saveLikeComment(likeComment), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLikeComment(@PathVariable Long id) {
        Comment comment = this.commentService.findCommentByCommentId(this.likeCommentService.findLikeCommentById(id).getCommentId());
        comment.setCommentLike(comment.getCommentLike() - 1);
        this.commentService.saveComment(comment);
        likeCommentService.deleteLikeComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public  ResponseEntity<Iterable<LikeComment>> getAllLikeComment() {
        return new ResponseEntity<>(likeCommentService.findAllLikeComment(),HttpStatus.OK);
    }

    @GetMapping("/finByCommentId/{commentId}")
    public  ResponseEntity<Iterable<LikeComment>> getAllLikeComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(likeCommentService.findLikeCommentsByCommentId(commentId),HttpStatus.OK);
    }

    @GetMapping("/findLikerByCommentId/{commentId}")
    public ResponseEntity<Iterable<User>> findLikePostByPostId(@PathVariable Long commentId) {
        Iterable<LikeComment> likeComments = likeCommentService.findLikeCommentsByCommentId(commentId);
        List<User> likerList = new ArrayList<>();
        for (LikeComment likeComment: likeComments) {
            likerList.add(this.userService.findUserById(likeComment.getUserId()));
        }
        return new ResponseEntity<>( likerList, HttpStatus.OK);
    }
}
