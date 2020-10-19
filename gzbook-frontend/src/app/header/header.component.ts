import {Component, OnInit} from '@angular/core';
import {IUser} from '../model/IUser';
import {AuthService} from '../service/auth.service';
import {TokenStorageService} from '../service/token-storage.service';
import {Router} from '@angular/router';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  user: IUser;
  RefreshToken = {
    token: ''
  };
  show = false;

  constructor(private userService: UserService,
              private authService: AuthService,
              private tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.setUser();
  }

  setUser() {
    this.userService.getUser().subscribe(
      response => {
        this.user = response as IUser;
      },
      error => console.error(error)
    );
  }

  logout(): void {
    this.RefreshToken.token = String(this.tokenStorageService.getToken());
    this.authService.logOut(this.RefreshToken).subscribe(
      res => {
        this.tokenStorageService.signOut();
        this.router.navigateByUrl('/');
      },
      error => {
        console.log(error);
      });
  }

  showMenu() {
    this.show = !this.show;
  }
}
