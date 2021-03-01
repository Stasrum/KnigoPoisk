import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  title = 'KnigoPoiskClient';
  public userRoles = localStorage.getItem('roles');
  public userName = localStorage.getItem('user')
  ngOnInit(): void {
  }

  delJwt() {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user');
    localStorage.removeItem('roles');
    localStorage.removeItem('id');
    this.userRoles = null;
    this.userName = null;
  }
}
