import {Component, OnInit} from '@angular/core';
import {LoginUser} from '../../utils/entities/User';
import {UserController} from '../../utils/controllers/UserController';
import {Router} from "@angular/router";
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  public user: LoginUser = new LoginUser('', '');
  public passwordCheck = 'password';

  constructor(private router: Router,
              private authController: UserController) {
  }

  ngOnInit(): void {
  }

  loginUser(user: LoginUser) {
    this.authController.authUser(user).subscribe((rec: any) => {
      if (rec.token) {
        localStorage.setItem('auth_token', rec.token);
        localStorage.setItem('user', user.username);
      }
      this.router.navigateByUrl('');
    });
  }

  visiblePassword() {
    if (this.passwordCheck == 'password') {
      this.passwordCheck = 'text';
    } else {
      this.passwordCheck = 'password';
    }
  }
}
