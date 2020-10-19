import {Component, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TokenStorageService} from '../service/token-storage.service';
import {IUser} from '../model/IUser';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-user-info',
  templateUrl: './edit-user-info.component.html',
  styleUrls: ['./edit-user-info.component.scss']
})
export class EditUserInfoComponent implements OnInit {

  emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$';
  emailExist = false;
  user: IUser;
  userEditForm: FormGroup;
  dateOfBirth: [string, string, string];
  gender = ['Nam', 'Nữ'];
  address = ['Việt Nam', 'USA', 'Korea', 'Japan', 'China', 'Russia', 'Lào', 'Campuchia'];
  dates = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'];
  months = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
  years = ['1990', '1991', '1992', '1993', '1994', '1995', '1996', '1997', '1998', '1999', '2000', '2001', '2002', '2003', '2004', '2005', '2006', '2007', '2008', '2009', '2010'];

  constructor(private userService: UserService,
              private router: Router,
              private formBuilder: FormBuilder,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    this.userEditForm = this.formBuilder.group({
      id: [''],
      username: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.pattern(this.emailPattern)]],
      password: [''],
      gender: ['', [Validators.required]],
      dateOfBirth: ['', [Validators.required]],
      about: ['', [Validators.required]],
      address: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      avatarUrl: ['', [Validators.required]],
      coverPhotoUrl: ['', [Validators.required]],
      roles: ['']
    });

    this.userService.getUser().subscribe(
      response => {
        this.user = response as IUser;
        this.userEditForm.patchValue(response);
        this.dateOfBirth = [this.user.dateOfBirth.slice(0, 2), this.user.dateOfBirth.slice(3, 5), this.user.dateOfBirth.slice(6, 10)];
      },
      error => console.error(error)
    );
  }

  editUser() {
    this.userEditForm.value.dateOfBirth = this.dateOfBirth[0] + '-' + this.dateOfBirth[1] + '-' + this.dateOfBirth[2];
    let data = this.userEditForm.value;
    this.userService.editUser(this.tokenStorage.getUser().id, data).subscribe(
      res => {
        Swal.fire({
          icon: 'success',
          title: 'Thông tin cá nhân của bạn đã được thay đổi!'
        });
      });
    this.router.navigate(['/']);
  }

  get email() {
    return this.userEditForm.get('email');
  }

  get username() {
    return this.userEditForm.get('username');
  }

  checkEmailExist() {
    if (this.userEditForm.value.userEmail !== this.tokenStorage.getUser().email) {
      this.userService.checkEmailExist(this.userEditForm.value.userEmail).subscribe(
        res => {
          this.emailExist = res as boolean;
        });
    }
  }
}
