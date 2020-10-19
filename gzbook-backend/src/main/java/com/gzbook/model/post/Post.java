package com.gzbook.model.post;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String textPost;

    private String imageUrl;

    private String videoUrl;

    private String linkPost;

    private String createdTime;

    private Long status;

    private int postLike;

    private int postDislike;

    public Post() {
    }

    public Post(Long postId, Long userID, String textPost, int postLike, int postDislike, Long status) {
        this.id = postId;
        this.userId = userID;
        this.textPost = textPost;
        this.postLike = postLike;
        this.postDislike = postDislike;
        this.status = status;
    }

    public Post(Long postId, Long userId, String textPost, String imagePost, String videoPost, String linkPost, String createdTime, int postLike, int postDislike, Long status) {
        this.id = postId;
        this.userId = userId;
        this.textPost = textPost;
        this.imageUrl = imagePost;
        this.videoUrl = videoPost;
        this.linkPost = linkPost;
        this.createdTime = createdTime;
        this.postLike = postLike;
        this.postDislike = postDislike;
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long postId) {
        this.id = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long postUserId) {
        this.userId = postUserId;
    }

    public String getTextPost() {
        return textPost;
    }

    public void setTextPost(String textPost) {
        this.textPost = textPost;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imagePost) {
        this.imageUrl = imagePost;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoPost) {
        this.videoUrl = videoPost;
    }

    public String getLinkPost() {
        return linkPost;
    }

    public void setLinkPost(String linkPost) {
        this.linkPost = linkPost;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    public int getPostDislike() {
        return postDislike;
    }

    public void setPostDislike(int postDislike) {
        this.postDislike = postDislike;
    }
}
