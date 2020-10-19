import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginAndRegisterComponent} from './login-and-register/login-and-register.component';
import {CommentComponent} from './comment/comment.component';
import {CommentListComponent} from './comment-list/comment-list.component';
import {SearchUserComponent} from './search-user/search-user.component';
import {FriendlistComponent} from './friendlist/friendlist.component';
import {UserInfoComponent} from './user-info/user-info.component';
import {EditUserInfoComponent} from './edit-user-info/edit-user-info.component';
import {EditPasswordComponent} from './edit-password/edit-password.component';
import {UserWallComponent} from './user-wall/user-wall.component';
import {StatusComponent} from './status/status.component';
import {StatusInfoComponent} from './status-info/status-info.component';


const routes: Routes = [
  {
    path: '',
    component: LoginAndRegisterComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'searchUser',
    component: SearchUserComponent
  },
  {
    path: 'list-friend',
    component: FriendlistComponent
  },
  {
    path: 'commentList',
    component: CommentListComponent
  }
  ,
  {
    path: 'user/:id',
    component: UserInfoComponent,
  },
  {
    path: 'edit',
    component: EditUserInfoComponent,
  },
  {
    path: 'change-password',
    component: EditPasswordComponent,
  },
  {
    path: 'wall/:id',
    component: UserWallComponent
  },
  {
    path: 'status/:id',
    component: StatusInfoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
