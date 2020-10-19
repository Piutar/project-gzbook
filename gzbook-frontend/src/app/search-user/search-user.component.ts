import {Component, OnInit} from '@angular/core';
import {UsersService} from '../service/friend/users.service';
import {NgForm} from '@angular/forms';
import {IUser} from '../model/IUser';
import {TokenStorageService} from '../service/token-storage.service';

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.scss']
})
export class SearchUserComponent implements OnInit {

  users: IUser[];
  sumUsers = 0;

  constructor(private userService: UsersService,private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    if (form.value.username === '') {
      this.userService.findAllUser().subscribe(
        response => {
          this.users = response as IUser[];
          this.sumUsers = this.users.length;
        },
        error => console.error(error)
      );
    } else {
      this.userService.findUserByUsername(form.value.username,this.tokenStorage.getUser().id).subscribe(
        response => {
          this.users = response as IUser[];
          this.sumUsers = this.users.length;
        },
        error => console.error(error)
      );
    }
    form.reset(
      {
        username: ''
      });
  }
}
