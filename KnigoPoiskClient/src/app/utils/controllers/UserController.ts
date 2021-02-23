import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginUser, UserDto} from "../entities/User";
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class UserController {

  constructor(private http: HttpClient) {
  }
  authUser(user: LoginUser){
    return this.http.post(path + 'auth', user);
  }

  registrationUser(userDto: UserDto){
    return this.http.post(path + 'api/v1/users/register', userDto)
  }

  userInfo(userDto: UserDto){
    return this.http.get(path + 'users/register')
  }
}
