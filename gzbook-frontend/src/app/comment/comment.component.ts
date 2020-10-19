import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommentService} from '../service/comment.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TokenStorageService} from '../service/token-storage.service';
import {UsersService} from '../service/friend/users.service';
import {PostService} from '../service/post.service';
import {FriendService} from '../service/friend/friend.service';
import {IUser} from '../model/IUser';
import {IPost} from '../model/IPost';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit {

  addCommentForm: FormGroup;
  @Output() newComment = new EventEmitter();
  @Input() postId;
  user: IUser;
  isFriend = false;
  userLogin: IUser;
  post: IPost;
  isMe = false;

  constructor(private commentService: CommentService,
              private fb: FormBuilder,
              private tokenStorage: TokenStorageService,
              private userService: UsersService,
              private postService: PostService,
              private friendService: FriendService) {
  }

  ngOnInit(): void {
    this.getUser();
    this.checkFriend();
    this.addCommentForm = this.fb.group({
      userId: this.tokenStorage.getUser().id,
      postId: this.postId,
      content: '',
      commentLike: '',
      commentDislike: '',
      commentTime: ''
    });
  }

  addComment() {
    if (this.isFriend || this.isMe) {
      let comment = this.addCommentForm.value;

      this.commentService.addNewComment(comment).subscribe(
        res => {
          this.newComment.emit(comment);
        });
    } else {
      Swal.fire({
        icon: 'warning',
        title: 'Cảnh báo !',
        text: 'Chỉ bạn bè mới có thể bình luận!'
      });
    }
    this.addCommentForm.reset({
      userId: this.tokenStorage.getUser().id,
      postId: this.postId,
      content: '',
      commentLike: '',
      commentDislike: '',
      commentTime: ''
    });
  }

  getUser() {
    this.userService.findUserById(this.tokenStorage.getUser().id).subscribe(
      res => this.user = res as IUser
    );
  }

  checkFriend() {
    this.postService.getPostById(this.postId).subscribe(
      response => {
        this.post = response as IPost;
        this.userService.getUser().subscribe(
          response => {
            this.userLogin = response as IUser;
            let status;
            this.friendService.checkFriend(this.userLogin.id, this.post.userId).subscribe(
              response => {
                status = response;
                switch (status) {
                  case 0:
                    this.isFriend = false;
                    break;
                  case 1:
                    this.isFriend = false;
                    break;
                  case 2:
                    this.isFriend = true;
                    break;
                  case 3:
                    this.isFriend = false;
                    break;
                }
                if (this.userLogin.id === this.post.userId) {
                  this.isMe = true;
                }
              },
              error => console.log(error)
            );
          },
          error => console.error(error)
        );
      });
  }
}
