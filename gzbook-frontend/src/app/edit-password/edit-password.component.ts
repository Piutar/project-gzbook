import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../service/user.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../service/token-storage.service';
import Swal from 'sweetalert2';

function comparePassword(c: AbstractControl) {
  const v = c.value;
  return (v.newPassword === v.confirmPassword) ? null : {
    passwordNotMatch: true
  };
}

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.scss']
})
export class EditPasswordComponent implements OnInit {

  currentPassword;
  continue = false;
  userId = this.tokenStorage.getUser().id;
  changePasswordForm: FormGroup;

  constructor(private userService: UserService,
              private router: Router,
              private formBuilder: FormBuilder,
              private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, {validator: comparePassword});
  }

  get f() {
    return this.changePasswordForm.controls;
  }

  checkPassword() {
    this.userService.combinePassword(this.tokenStorage.getUser().id, this.currentPassword).subscribe(
      res => this.continue = true,
      error => this.continue = false
    );
  }

  changePassword() {
    this.userService.changePassword(this.tokenStorage.getUser().id, this.changePasswordForm.value.newPassword).subscribe(
      res => {
        Swal.fire({
          icon: 'success',
          title: 'Mật khẩu của bạn đã được thay đổi!'
        });
      });
    this.router.navigate(['/']);
  }
}
