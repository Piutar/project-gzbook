package com.gzbook.controller;

import com.gzbook.model.comment.Comment;
import com.gzbook.model.comment.LogComment;
import com.gzbook.service.logcomment.ILogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/logComment")
public class LogCommentController {

    @Autowired
    private ILogCommentService logCommentService;

    @PostMapping("/create")
    public ResponseEntity<LogComment> createComment(@RequestBody Comment comment) {
        LogComment logComment = new LogComment();
        logComment.setCommentId(comment.getId());
        logComment.setContent(comment.getContent());
        logComment.setCreatedTime(timeConvert());

        return new ResponseEntity<>(logCommentService.saveLogComment(logComment), HttpStatus.CREATED);
    }

    @GetMapping("findLogCommentById/{commentId}")
    public ResponseEntity<Iterable<LogComment>> findCommentById(@PathVariable Long commentId) {
        return new ResponseEntity<>(logCommentService.findAllLogCommentByCommentId(commentId), HttpStatus.OK);
    }

    private String timeConvert() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return myDateObj.format(myFormatObj);
    }
}

