package com.gzbook.controller;

import com.gzbook.model.like.LikePost;
import com.gzbook.model.post.Post;
import com.gzbook.model.user.User;
import com.gzbook.service.like.ILikePostService;
import com.gzbook.service.post.IPostService;
import com.gzbook.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/like/post")
public class LikePostController {
    @Autowired
    ILikePostService likePostService;

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @PostMapping("/create")
    public ResponseEntity<LikePost> createLikePost(@RequestBody LikePost likePost) {
        Post post = this.postService.findPostById(likePost.getPostId());
        post.setPostLike(post.getPostLike() + 1);
        this.postService.savePost(post);
        return new ResponseEntity<>(likePostService.saveLikePost(likePost), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LikePost> updateLikePost(@PathVariable Long id, @RequestBody LikePost likePost) {
        likePost.setId(id);
        return new ResponseEntity<>(likePostService.saveLikePost(likePost), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLikePost(@PathVariable Long id) {
        Post post = this.postService.findPostById(this.likePostService.findLikePostById(id).getPostId());
        post.setPostLike(post.getPostLike() - 1);
        this.postService.savePost(post);
        likePostService.deleteLikePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<LikePost>> getAllLikePost() {
        return new ResponseEntity<>(likePostService.findAllLikePost(),HttpStatus.OK);
    }

    @GetMapping("/findLikerByPostId/{postId}")
    public ResponseEntity<Iterable<User>> findLikePostByPostId(@PathVariable Long postId) {
        Iterable<LikePost> likePosts = likePostService.findLikePostByPostId(postId);
        List<User> likerList = new ArrayList<>();
        for (LikePost likePost: likePosts) {
            likerList.add(this.userService.findUserById(likePost.getUserId()));
        }
        return new ResponseEntity<>( likerList, HttpStatus.OK);
    }
}

