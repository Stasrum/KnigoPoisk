import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  title = 'KnigoPoiskClient';
  public username = localStorage.getItem('user');
  ngOnInit(): void {
  }

  delJwt() {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user');
    this.username = null;
  }
}
