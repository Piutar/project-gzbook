import {Component, OnInit} from '@angular/core';
import {HeaderComponent} from '../header/header.component';
import {TokenStorageService} from '../service/token-storage.service';

@Component({
  providers: [HeaderComponent],
  selector: 'app-shortcut',
  templateUrl: './shortcut.component.html',
  styleUrls: ['./shortcut.component.scss']
})
export class ShortcutComponent implements OnInit {

  userId = this.tokenStorageService.getUser().id;

  constructor(private tokenStorageService: TokenStorageService,
              private header: HeaderComponent) {
  }

  ngOnInit(): void {
  }

  logOut() {
    this.header.logout();
  }
}
