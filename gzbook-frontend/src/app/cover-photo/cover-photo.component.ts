import {Component, Input, OnInit} from '@angular/core';
import {IUser} from '../model/IUser';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {FriendService} from '../service/friend/friend.service';
import {AngularFireStorage} from '@angular/fire/storage';
import {UploadFileService} from '../service/upload-file.service';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-cover-photo',
  templateUrl: './cover-photo.component.html',
  styleUrls: ['./cover-photo.component.scss']
})
export class CoverPhotoComponent implements OnInit {

  user: IUser;
  isFriend = false;
  userLogin: IUser;
  @Input() relatingId: number;
  @Input() relatedId: number;

  constructor(private uploadService: UploadFileService,
              private userService: UserService,
              private router: Router,
              private friendService: FriendService,
              private storage: AngularFireStorage) {
  }

  ngOnInit(): void {
    this.userService.findUserById(this.relatedId).subscribe(
      response => {
        this.user = response as IUser;
      },
      error => console.log(error));
    this.checkFriend();
  }

  addFriend() {
    this.userService.findUserById(this.relatedId).subscribe(
      response => {
        this.user = response as IUser;
        this.friendService.addInviteFriend(this.relatingId, {
          id: this.user.id,
          username: null,
          email: null,
          password: null,
          gender: null,
          dateOfBirth: null,
          about: null,
          address: null,
          avatarUrl: null,
          coverPhotoUrl: null,
          roles: null
        }).subscribe();
      },
      error => console.log(error)
    );
  }

  uploadFile(event, option: number) {
    let file = event.target.files[0];
    let filePath = file.name;
    let fileRef = this.storage.ref(filePath);
    let task = this.storage.upload(filePath, file);

    task.snapshotChanges().pipe(
      finalize(() => fileRef.getDownloadURL().subscribe(
        url => {
          if (option === 1) {
            this.updateCoverPhoto(url);
          } else {
            this.updateAvatar(url);
          }
        })))
      .subscribe();
  }

  updateCoverPhoto(imgLink: string) {
    this.userService.findUserById(this.relatedId).subscribe(
      response => {
        this.user = response as IUser;
        this.user.coverPhotoUrl = imgLink;
        this.userService.editUser(this.user.id, this.user).subscribe();
      },
      error => console.log(error));
  }

  updateAvatar(avatarLink: string) {
    this.userService.findUserById(this.relatedId).subscribe(
      response => {
        this.user = response as IUser;
        this.user.avatarUrl = avatarLink;
        this.userService.editUser(this.user.id, this.user).subscribe();
      },
      error => console.log(error));
  }

  checkFriend() {
    this.userService.getUser().subscribe(
      response => {
        this.userLogin = response as IUser;
        let status;
        this.friendService.checkFriend(this.userLogin.id, this.relatedId).subscribe(
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
          },
          error => console.log(error)
        );
      },
      error => console.error(error)
    );
  }

  unfriend(relatedId: number, statusId: number) {
    this.userService.findUserById(relatedId).subscribe(
      response => {
        this.user = response as IUser;
        this.userService.getUser().subscribe(
          response => {
            this.userLogin = response as IUser;
            this.friendService.unfriend(this.userLogin.id, statusId, {
              id: this.user.id,
              username: null,
              email: null,
              password: null,
              gender: null,
              dateOfBirth: null,
              about: null,
              address: null,
              avatarUrl: null,
              coverPhotoUrl: null,
              roles: null
            }).subscribe(
              response => {
                this.isFriend = false;
              },
              error => console.error(error)
            );
          },
          error => console.error(error)
        );
      },
      error => console.error(error)
    );
  }

}
