import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loginUser} from "../entities/User";
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class AuthController{

  constructor(private http: HttpClient) {
  }
  authUser(user: loginUser){
    return this.http.post(path + 'auth', user);
  }
}
