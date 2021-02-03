import {Component, Injectable, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
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
