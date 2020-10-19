import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './footer/footer.component';
import {HomeComponent} from './home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {LoginAndRegisterComponent} from './login-and-register/login-and-register.component';
import {HeaderComponent} from './header/header.component';
import {FriendlistComponent} from './friendlist/friendlist.component';
import {SearchUserComponent} from './search-user/search-user.component';
import {ShortcutComponent} from './shortcut/shortcut.component';
import {NewPostComponent} from './new-post/new-post.component';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment';
import {AngularFirestoreModule} from '@angular/fire/firestore';
import {AngularFireStorage} from '@angular/fire/storage';
import {authInterceptorProviders} from './auth.interceptor';
import {StatusComponent} from './status/status.component';
import {CommentComponent} from './comment/comment.component';
import {CommentListComponent} from './comment-list/comment-list.component';
import {UserInfoComponent} from './user-info/user-info.component';
import {EditUserInfoComponent} from './edit-user-info/edit-user-info.component';
import {EditComponent} from './edit/edit.component';
import {EditPasswordComponent} from './edit-password/edit-password.component';
import {CommentEditComponent} from './comment-edit/comment-edit.component';
import {StatusEditComponent} from './status-edit/status-edit.component';
import {UserWallComponent} from './user-wall/user-wall.component';
import {CoverPhotoComponent} from './cover-photo/cover-photo.component';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import { MutualFriendsComponent } from './mutual-friends/mutual-friends.component';
import { StatusLikerListComponent } from './status-liker-list/status-liker-list.component';
import { CommentLikeComponent } from './comment-like/comment-like.component';
import { CommentLikerListComponent } from './comment-liker-list/comment-liker-list.component';
import { StatusInfoComponent } from './status-info/status-info.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HomeComponent,
    LoginAndRegisterComponent,
    HeaderComponent,
    FriendlistComponent,
    CommentComponent,
    CommentListComponent,
    SearchUserComponent,
    ShortcutComponent,
    NewPostComponent,
    StatusComponent,
    UserInfoComponent,
    EditUserInfoComponent,
    EditComponent,
    EditPasswordComponent,
    CommentEditComponent,
    StatusEditComponent,
    UserWallComponent,
    CoverPhotoComponent,
    MutualFriendsComponent,
    StatusLikerListComponent,
    CommentLikeComponent,
    CommentLikerListComponent,
    StatusInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    AngularFireDatabaseModule
  ],
  providers: [
    authInterceptorProviders,
    AngularFirestoreModule,
    AngularFireStorage
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
