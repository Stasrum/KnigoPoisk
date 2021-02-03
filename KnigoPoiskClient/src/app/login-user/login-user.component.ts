import { Component, OnInit } from '@angular/core';
import {loginUser} from '../entities/User';
import {AuthController} from '../controllers/AuthController';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  public user: loginUser = new loginUser('', '');
  public passwordCheck = 'password';

  constructor(private authController: AuthController) { }

  ngOnInit(): void {
  }

  loginUser(user: loginUser) {
    this.authController.authUser(user).subscribe((rec: any) =>{
      localStorage.setItem('auth_token', rec.token);
    });
  }

  visiblePassword() {
    if (this.passwordCheck == 'password'){
      this.passwordCheck = 'text';
    } else {
      this.passwordCheck = 'password';
    }
  }
}
