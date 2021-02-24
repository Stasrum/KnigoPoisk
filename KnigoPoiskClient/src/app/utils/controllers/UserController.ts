import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ChangePassword, LoginUser, UserChangeDto, UserDto} from "../entities/User";
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

  userInfo(){
    return this.http.get(path + 'api/v1/user/profile')
  }

  userChangePassword(id: number, password: ChangePassword){
    return this.http.post(path + 'api/v1/user/' + id + '/changePassword', password)
  }

  userUpdate(user: UserChangeDto){
    return this.http.post(path + 'api/v1/user/update', user)
  }
}
