import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {PostService} from '../service/post.service';
import {LikeCommentService} from '../service/like-comment.service';
import {TokenStorageService} from '../service/token-storage.service';
import {IComment} from '../model/IComment';
import {ILikeComment} from '../model/ILikeComment';

@Component({
  selector: 'app-comment-like',
  templateUrl: './comment-like.component.html',
  styleUrls: ['./comment-like.component.scss']
})
export class CommentLikeComponent implements OnInit {

  constructor(private userService: UserService,
              private postService: PostService,
              private likeCommentService: LikeCommentService,
              private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.checkCommentLikedStatus()
  }

  @Input() comment: IComment;

  likeComment = {
    id: null,
    commentId: null,
    userId: null,
  };
  likedCommentList: ILikeComment[];

  commentLiked: boolean;

  likeAComment() {
    this.likeComment.commentId = this.comment.id;
    this.likeComment.userId = this.tokenStorage.getUser().id;
    this.likeCommentService.newLikeComment(this.likeComment).subscribe(
      res => {
        this.commentLiked = true;
        this.comment.commentLike++;
      }
    );
  }

  unLikeAComment() {
    this.likeCommentService.findAllLikeCommentByCommentId(this.comment.id).subscribe(
      res => {
        this.likedCommentList = <ILikeComment[]> res;
        for (let i = 0; i < this.likedCommentList.length; i++) {
          if (this.likedCommentList[i].userId === this.tokenStorage.getUser().id && this.likedCommentList[i].commentId === this.comment.id) {
            this.likeCommentService.unLikeAComment(this.likedCommentList[i].id).subscribe(
              res => {
                this.commentLiked = false;
                this.comment.commentLike--;
              }
            );
          }
        }
      }
    )
  }

  checkCommentLikedStatus() {
    this.commentLiked = false;
    this.likeCommentService.findAllLikeCommentByCommentId(this.comment.id).subscribe(
      res => {
        this.likedCommentList = <ILikeComment[]> res;
        for (let j = 0; j < this.likedCommentList.length; j++) {
          if (this.likedCommentList[j].userId === this.tokenStorage.getUser().id) {
            this.commentLiked = true;
          }
        }
      }
    )
  }
}

