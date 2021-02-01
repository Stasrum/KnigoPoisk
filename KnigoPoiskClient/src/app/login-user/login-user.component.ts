import {Component, OnInit} from '@angular/core';
import {loginUser} from '../entities/User';
import {AuthController} from '../controllers/AuthController';
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  public user: loginUser = new loginUser('', '');
  public passwordCheck = 'password';

  constructor(private router: Router,
              private authController: AuthController) {
  }

  ngOnInit(): void {
  }

  loginUser(user: loginUser) {
    this.authController.authUser(user).subscribe((rec: any) => {
        localStorage.setItem('auth_token', rec.token);
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
