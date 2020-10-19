import {Component, OnInit} from '@angular/core';
import {IUser} from '../model/IUser';
import {UserService} from '../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FriendService} from '../service/friend/friend.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.scss']
})
export class UserInfoComponent implements OnInit {

  user: IUser;
  userLogin: IUser;
  friendList: IUser[];
  friendListLogin: IUser[];
  mutualFriendList: IUser[] = [];
  idUser: number;
  idLogin: number;
  isLogin = false;
  isFriend: boolean;

  constructor(private userService: UserService,
              private router: Router,
              private friendService: FriendService,
              private actRoute: ActivatedRoute) {
    this.idUser = parseInt(this.actRoute.snapshot.params.id);
  }

  ngOnInit(): void {
    this.userService.findUserById(this.idUser).subscribe(
      response => this.user = response as IUser,
      error => console.log(error)
    );
    this.getFriendList();
    this.setUser();
    this.checkFriend();
    this.getMutualFriendList();
  }

  getFriendList() {
    this.friendService.getFriendList(this.idUser).subscribe(
      response => {
        this.friendList = response as IUser[];
      },
      error => console.error(error)
    );
  }

  getMutualFriendList() {
    this.userService.getUser().subscribe(
      response => {
        this.userLogin = response as IUser;
        this.friendService.getFriendList(this.userLogin.id).subscribe(
          response => {
            this.friendListLogin = response as IUser[];
            for (let i = 0; i < this.friendList.length; i++) {
              for (let j = 0; j < this.friendListLogin.length; j++) {
                if (this.friendList[i].id === this.friendListLogin[j].id) {
                  this.mutualFriendList.push(this.friendList[i]);
                  console.log(this.friendList[i]);
                }
              }
            }
          },
          error => console.error(error)
        );
      });
  }


  setUser() {
    this.userService.getUser().subscribe(
      response => {
        this.userLogin = response as IUser;
        this.idLogin = this.userLogin.id;
        if (this.idLogin === this.idUser) {
          this.isLogin = true;
        }
      },
      error => console.error(error)
    );
  }

  checkFriend() {
    this.userService.getUser().subscribe(
      response => {
        this.userLogin = response as IUser;
        let status;
        this.friendService.checkFriend(this.userLogin.id, this.idUser).subscribe(
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
}
