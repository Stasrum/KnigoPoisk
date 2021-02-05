import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginUser, UserDto} from "../entities/User";
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class AuthController{

  constructor(private http: HttpClient) {
  }
  authUser(user: LoginUser){
    return this.http.post(path + 'auth', user);
  }

  registrationUser(userDto: UserDto){
    return this.http.post(path + 'processRegistrationForm', userDto)
  }
}
