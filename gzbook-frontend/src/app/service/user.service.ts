import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from './token-storage.service';
import {catchError, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) {
  }

  private userUrl = environment.URL + 'user';

  getUser() {
    return this.findUserById(this.tokenStorage.getUser().id);
  }

  findUserById(id: number) {
    return this.http.get(this.userUrl + '/findUserById/' + id).pipe(
      tap(
        user => JSON.stringify(user)),
      catchError(err => of([]))
    );
  }

  editUser(id: number, user: any): Observable<any> {
    return this.http.put(this.userUrl + '/update/' + id, user);
  }

  combinePassword(id: number, password: JSON) {
    return this.http.post(this.userUrl + '/combinePassword/' + id, password);
  }

  changePassword(id: number, newPassword: JSON) {
    return this.http.post(this.userUrl + '/changePassword/' + id, newPassword,
      {responseType: 'text' as 'json'});
  }

  findUserByUsername(username: string) {
    return this.http.get(this.userUrl + '/findUserByName/' + username).pipe(
      tap(
        users => JSON.stringify(users)),
      catchError(err => of([]))
    );
  }

  findAllUser() {
    return this.http.get(this.userUrl + '/').pipe(
      tap(
        users => JSON.stringify(users)),
      catchError(err => of([]))
    );
  }

  checkEmailExist(email: string) {
    return this.http.post(this.userUrl + '/exists', email);
  }
}
