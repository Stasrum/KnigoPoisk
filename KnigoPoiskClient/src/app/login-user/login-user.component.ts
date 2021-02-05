import {Component, OnInit} from '@angular/core';
import {LoginUser} from '../entities/User';
import {AuthController} from '../controllers/AuthController';
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  public user: LoginUser = new LoginUser('', '');
  public passwordCheck = 'password';

  constructor(private router: Router,
              private authController: AuthController) {
  }

  ngOnInit(): void {
  }

  loginUser(user: LoginUser) {
    this.authController.authUser(user).subscribe((rec: any) => {
      localStorage.setItem('auth_token', rec.token);
      this.router.navigateByUrl('');
      });
    localStorage.setItem('user', user.username);
  }

  visiblePassword() {
    if (this.passwordCheck == 'password') {
      this.passwordCheck = 'text';
    } else {
      this.passwordCheck = 'password';
    }
  }
}
