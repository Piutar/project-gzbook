package com.gzbook.model.comment;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LogComment")
public class LogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long commentId;

    private String content;

    private String createdTime;

    public LogComment() {
    }

    public LogComment(Long logCommentId, Long commentId, String content, String commentTime) {
        this.id = logCommentId;
        this.commentId = commentId;
        this.content = content;
        this.createdTime = commentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long logCommentId) {
        this.id = logCommentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String commentTime) {
        this.createdTime = commentTime;
    }
}
