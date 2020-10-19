package com.gzbook.controller;

import com.gzbook.model.comment.Comment;
import com.gzbook.model.like.LikeComment;
import com.gzbook.model.like.LikePost;
import com.gzbook.service.comment.ICommentService;
import com.gzbook.service.like.ILikeCommentService;
import com.gzbook.service.like.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @Autowired
    private ILikeCommentService likeCommentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        comment.setCommentTime(timeConvert());
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        return new ResponseEntity<>(commentService.saveComment(comment), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<Comment>> findAllComment() {
        return new ResponseEntity<>(commentService.findAllComment(), HttpStatus.OK);
    }

    @GetMapping("findCommentById/{commentId}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.findCommentByCommentId(commentId), HttpStatus.OK);
    }

    @GetMapping("findCommentsByPostId/{postId}")
    public ResponseEntity<Iterable<Comment>> findCommentsByPostId(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.findCommentsByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("findCommentsByCommenterId/{commenterId}")
    public ResponseEntity<Iterable<Comment>> findCommentsByCommenterId(@PathVariable Long commenterId) {
        return new ResponseEntity<>(commentService.findCommentById(commenterId), HttpStatus.OK);
    }

    @GetMapping("count/{id}")
    public ResponseEntity<Integer> countCommenterId(@PathVariable Long id) {
        List<Comment> list = (List<Comment>) commentService.findCommentsByPostId(id);
        return new ResponseEntity<>(list.size(), HttpStatus.OK);
    }

//    @PostMapping("/like")
//    public ResponseEntity<Integer> likePost(@RequestBody LikeComment likePost){
//        return new ResponseEntity<>(likeCommentService.create(likePost),HttpStatus.OK);
//    }
//
//    @GetMapping("/likes/{commentId}")
//    public ResponseEntity<List<LikeComment>> likePostCount(@PathVariable long commentId){
//        List listComment = likeCommentService.likeComment(commentId);
//        return new ResponseEntity<>(listComment,HttpStatus.OK);
//    }
//
//    @GetMapping("/checkLike/{commentId}/{userId}")
//    public ResponseEntity<Boolean> checkLike(@PathVariable long commentId, @PathVariable long userId){
//        Boolean isCheck = likeCommentService.checkLike(commentId,userId) == null?false:true;
//        return new ResponseEntity<>(isCheck,HttpStatus.OK);
//    }

    private String timeConvert() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return myDateObj.format(myFormatObj);
    }
}
