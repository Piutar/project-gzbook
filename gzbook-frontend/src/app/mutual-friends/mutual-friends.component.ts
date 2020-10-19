import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {IUser} from '../model/IUser';
import {FriendService} from '../service/friend/friend.service';
import {TokenStorageService} from '../service/token-storage.service';

@Component({
  selector: 'app-mutual-friends',
  templateUrl: './mutual-friends.component.html',
  styleUrls: ['./mutual-friends.component.scss']
})
export class MutualFriendsComponent implements OnInit {
  friendList: IUser[];
  @Input() idUser;
  userLogin:IUser;
  idLogin: number;
  idUserWall: number;

  constructor(private userService: UserService,private friendService: FriendService,private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.getFriendList();
    this.idLogin = this.tokenStorage.getUser().id;
  }

  getFriendList() {
    this.idUserWall = this.idUser;
    this.friendService.getMutualFriendList(this.idUser,this.tokenStorage.getUser().id).subscribe(
      response => {this.friendList = response as IUser[]},
      error => console.error(error)
    )
  }
}
