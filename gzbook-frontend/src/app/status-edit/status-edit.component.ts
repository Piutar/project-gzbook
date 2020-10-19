import {Component, Input, OnInit} from '@angular/core';
import {AngularFireStorage} from '@angular/fire/storage';
import {ActivatedRoute, Router} from '@angular/router';
import {CommentService} from '../service/comment.service';
import {IPost} from '../model/IPost';
import {UserService} from '../service/user.service';
import {PostService} from '../service/post.service';
import {finalize} from 'rxjs/operators';
import {NgForm} from '@angular/forms';
import Swal from 'sweetalert2';

declare var $: any;

@Component({
  selector: 'app-status-edit',
  templateUrl: './status-edit.component.html',
  styleUrls: ['./status-edit.component.scss']
})
export class StatusEditComponent implements OnInit {

  @Input() post: IPost;

  constructor(private userService: UserService,
              private postService: PostService,
              private commentService: CommentService,
              private actRoute: ActivatedRoute,
              private storage: AngularFireStorage,
              private router: Router) {
  }

  ngOnInit(): void {
  }


  onSubmit(form: NgForm) {
    this.post.textPost = form.value.textPost;
    this.post.imageUrl = form.value.imagePost;
    this.postService.updatePost(this.post).subscribe(res => {
      Swal.fire({
        icon: 'success',
        title: 'Nội dung đã được thay đổi!'
      });
    });
  }

  deleteImage() {
    this.post.imageUrl = '';
  }

  uploadFile(event) {
    let file = event.target.files[0];
    let filePath = file.name;
    let fileRef = this.storage.ref(filePath);
    let task = this.storage.upload(filePath, file);

    task.snapshotChanges().pipe(
      finalize(() => fileRef.getDownloadURL().subscribe(
        url => this.post.imageUrl = url))
    )
      .subscribe();
  }

  selectStatus(event) {
    this.post.status = event;
    console.log(this.post.status);
  }
}
