import {Component, OnInit} from '@angular/core';
import {UserDto} from "../entities/User";
import {AuthController} from "../controllers/AuthController";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public userDto: UserDto = new UserDto('', '', '', '', '', '', '');
  visiblePassword = 'password';

  constructor(private authController: AuthController) {
  }

  ngOnInit(): void {
  }

  changeVisiblePassword() {
    if (this.visiblePassword == 'password')
      this.visiblePassword = 'text';
    else
      this.visiblePassword = 'password';
  }

  registration() {
    console.log(this.userDto);
    this.authController.registrationUser(this.userDto).subscribe(rec => console.log(rec), error => console.log(error));
  }
}
