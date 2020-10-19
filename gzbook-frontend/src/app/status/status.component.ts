import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IUser} from '../model/IUser';
import {IPost} from '../model/IPost';
import {IComment} from '../model/IComment';
import {ILikePost} from '../model/ILikePost';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenStorageService} from '../service/token-storage.service';
import {PostService} from '../service/post.service';
import {UserService} from '../service/user.service';
import {LikePostService} from '../service/like-post.service';
import {CommentService} from '../service/comment.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.scss']
})
export class StatusComponent implements OnInit {

  @Output() indexDelPost = new EventEmitter();
  @Output() sharePostEvent = new EventEmitter();
  @Input() post: IPost;
  @Input() index: number;
  postList: IPost[];
  editPost: IPost;
  editPostId: number;
  userLogin: IUser;
  sharedPost: IPost;
  likePost = {
    id: null,
    postId: null,
    userId: null,
  };
  liked: boolean;
  likeList: ILikePost[];

  constructor(private userService: UserService,
              private postService: PostService,
              private commentService: CommentService,
              private likePostService: LikePostService,
              private tokenStorage: TokenStorageService,
              private actRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.showPost();
    this.checkLikedStatus();
    this.userService.getUser().subscribe(
      res => {
        this.userLogin = res as IUser;
      }
    );
  }

  showPost() {
    let id: number;
    if (this.actRoute.snapshot.params.id == null || !window.location.href.includes('status')) {
      id = this.post.id;
    } else {
      id = parseInt(this.actRoute.snapshot.params.id);
    }
    this.postService.getPostById(id).subscribe(
      post => {
        this.post = post as IPost;
        this.userService.findUserById(this.post.userId).subscribe(
          res => {
            let user = res as IUser;
            this.post.posterName = user.username;
            this.post.posterAvatar = user.avatarUrl;
            this.commentService.getCommentByPostId(this.post.id).subscribe(
              commentList => {
                this.post.commentList = commentList as IComment[];
              }
            );
          }
        );
        if (this.post.linkPost !== '') {
          this.postService.getPostById(parseInt(this.post.linkPost)).subscribe(
            sharedPost => {
              this.sharedPost = sharedPost as IPost;
              this.userService.findUserById(this.sharedPost.userId).subscribe(
                res => {
                  let user = res as IUser;
                  this.sharedPost.posterName = user.username;
                  this.sharedPost.posterAvatar = user.avatarUrl;
                });
            });
        }
      });
  }

  likeAPost() {
    this.likePost.postId = this.post.id;
    this.likePost.userId = this.tokenStorage.getUser().id;
    this.likePostService.newLikePost(this.likePost).subscribe(
      res => {
        this.checkLikedStatus();
        this.post.postLike++;
      }
    );
  }

  unLikeAPost() {
    this.likePostService.findAllLikePost().subscribe(
      res => {
        this.likeList = res as ILikePost[];
        for (let i = 0; i < this.likeList.length; i++) {
          if (this.likeList[i].userId === this.tokenStorage.getUser().id && this.likeList[i].postId === this.post.id) {
            this.likePostService.unLikeAPost(this.likeList[i].id).subscribe();
            this.post.postLike--;
            this.liked = false;
          }
        }
      }
    );
  }

  checkLikedStatus() {
    this.liked = false;
    this.likePostService.findAllLikePost().subscribe(
      res => {
        this.likeList = res as ILikePost[];
        for (let i = 0; i < this.likeList.length; i++) {
          if (this.actRoute.snapshot.params.id == null || !window.location.href.includes('status')) {
            if (this.likeList[i].postId === this.post.id) {
              if (this.likeList[i].userId === this.tokenStorage.getUser().id) {
                this.liked = true;
              }
            }
          } else {
            if (this.likeList[i].postId === parseInt(this.actRoute.snapshot.params.id)) {
              if (this.likeList[i].userId === this.tokenStorage.getUser().id) {
                this.liked = true;
              }
            }
          }
        }
      }
    );
  }

  deletePost(postId: number) {
    Swal.fire({
      title: 'Xóa bài viết?',
      text: 'Bạn có chắc chắn muốn xóa bài viết này không?',
      icon: 'warning'
    })
      .then(willDelete => {
        if (willDelete) {
          console.log(postId);
          this.commentService.getCommentByPostId(postId).subscribe(
            commentList => {
              let comments = commentList as IComment[];
              for (let i = 0; i < comments.length; i++) {
                this.commentService.deleteComment(comments[i].id).subscribe(
                  res => console.log('Đã xóa bài viết!')
                );
              }
            });
          this.postService.deletePost(postId).subscribe(
            res => {
              Swal.fire({
                icon: 'success',
                title: 'Bài viết của bạn đã được xóa!'
              });
              this.indexDelPost.emit(this.index);
              if (this.actRoute.snapshot.params.id != null) {
                this.router.navigate(['/home']);
              }
            });
        }
      });
  }

  addNewComment(value) {
    this.post.commentList.push(value);
  }

  delComment(value) {
    this.post.commentList.splice(value, 1);
  }

  sharePost(postId: number) {
    Swal.fire({
      title: 'Chia sẻ',
      text: 'Bạn có muốn chia sẻ bài viết này?',
      icon: 'info'
    })
      .then(share => {
        if (share) {
          this.postService.creatNewPost({
            userId: this.tokenStorage.getUser().id,
            textPost: '',
            imagePost: '',
            videoPost: '',
            linkPost: postId,
            postDate: '',
            postLike: 0,
            postDislike: 0,
            status: 3
          }).subscribe(
            res => {
              this.sharePostEvent.emit(postId);
            }
          );
          Swal.fire({
            icon: 'success',
            title: 'Bài viết này đã được chia sẻ!'
          });
        }
      });
  }
}
