import jwt_decode from 'jwt-decode';
import {Injectable} from '@angular/core';
import {Jwt} from '../entities/JWT';

@Injectable()
export class JwtService {
  constructor() {
  }

  DecodeToken(token: string): Jwt {
    return (jwt_decode(token));
  }
}
