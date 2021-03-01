import {Component, OnInit} from '@angular/core';
import {LoginUser, UserDto} from '../../utils/entities/User';
import {UserController} from '../../utils/controllers/UserController';
import {Router} from '@angular/router';
import {JwtService} from '../../utils/controllers/JwtService';
import {Jwt} from '../../utils/entities/JWT';


@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  public user: LoginUser = new LoginUser('', '');
  public passwordCheck = 'password';
  public jwtDecoder: Jwt;

  constructor(private router: Router,
              private userController: UserController,
              private jwtService: JwtService) {
  }

  ngOnInit(): void {
  }

  loginUser(user: LoginUser) {
    this.userController.authUser(user).subscribe((rec: any) => {
      if (rec.token) {
        localStorage.setItem('auth_token', rec.token);
        this.jwtDecoder = this.jwtService.DecodeToken(rec.token);
        console.log(this.jwtDecoder);
        localStorage.setItem('user', this.jwtDecoder.sub);
        for (let i = 0; i < this.jwtDecoder.roles.length; i++) {
          if (this.jwtDecoder.roles[i] == 'ADMIN')
            localStorage.setItem('roles', this.jwtDecoder.roles[i]);
        }
        this.userController.userInfo().subscribe((rec: any) =>  localStorage.setItem('id', rec.id));
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
