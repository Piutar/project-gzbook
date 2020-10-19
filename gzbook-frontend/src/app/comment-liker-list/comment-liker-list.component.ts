import {Component, Input, OnInit} from '@angular/core';
import {LikeCommentService} from '../service/like-comment.service';
import {IUser} from '../model/IUser';

@Component({
  selector: 'app-comment-liker-list',
  templateUrl: './comment-liker-list.component.html',
  styleUrls: ['./comment-liker-list.component.scss']
})
export class CommentLikerListComponent implements OnInit {

  constructor(private likeCommentService: LikeCommentService) { }

  ngOnInit(): void {
    this.getLikerList();
  }

  @Input() id;
  likerList: IUser[];

  getLikerList() {
    this.likeCommentService.findLikerByCommentId(this.id).subscribe(
      res => {
        this.likerList = <IUser[]> res;
      }
    )
  }
}
