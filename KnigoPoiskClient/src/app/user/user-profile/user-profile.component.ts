import { Component, OnInit } from '@angular/core';
import {UserController} from '../../utils/controllers/UserController';
import {ChangePassword, UserChangeDto} from '../../utils/entities/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userDto = new UserChangeDto(null,'', '', '', '', '');
  public userChangeDto = new UserChangeDto(null,'', '', '', '', '');
  public visiblePassword = 'password';
  public visible = false;
  public newPassword = new ChangePassword('', '')

  constructor(public userController: UserController,
              private router: Router) {}

  ngOnInit(): void {
    this.userController.userInfo().subscribe((rec: any) => {
      this.userDto = rec;
      this.userChangeDto.userName = this.userDto.userName;
      this.userChangeDto.firstName = this.userDto.firstName;
      this.userChangeDto.lastName = this.userDto.lastName;
      this.userChangeDto.email = this.userDto.email;
      this.userChangeDto.birthDay = this.userDto.birthDay;
    });
  }

  changeVisiblePassword() {
    if (this.visiblePassword == 'password')
      this.visiblePassword = 'text';
    else
      this.visiblePassword = 'password';
  }

  savePassword() {
    this.userController.userChangePassword(this.userDto.id, this.newPassword).subscribe();
  }

  userUpdate() {
    this.userController.userUpdate(this.userDto).subscribe(error => {
      this.userController.userInfo().subscribe((rec: any) => {
        this.userDto = rec;
        this.userChangeDto.userName = this.userDto.userName;
        this.userChangeDto.firstName = this.userDto.firstName;
        this.userChangeDto.lastName = this.userDto.lastName;
        this.userChangeDto.email = this.userDto.email;
        this.userChangeDto.birthDay = this.userDto.birthDay;
      });
    });
  }
}
