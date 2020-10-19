package com.gzbook.model.comment;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long postId;

    private String content;

    private int commentLike;

    private int commentDislike;

    private String commentTime;

    private int edited;

    public Comment() {
    }

    public Comment(Long commentId, Long commenterId, Long postId, String content, int commentLike, int commentDislike, String commentTime, int edited) {
        this.id = commentId;
        this.userId = commenterId;
        this.postId = postId;
        this.content = content;
        this.commentLike = commentLike;
        this.commentDislike = commentDislike;
        this.commentTime = commentTime;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long commentId) {
        this.id = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long commenterId) {
        this.userId = commenterId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(int commentLike) {
        this.commentLike = commentLike;
    }

    public int getCommentDislike() {
        return commentDislike;
    }

    public void setCommentDislike(int commentDislike) {
        this.commentDislike = commentDislike;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getEdited() {
        return edited;
    }

    public void setEdited(int edited) {
        this.edited = edited;
    }
}
