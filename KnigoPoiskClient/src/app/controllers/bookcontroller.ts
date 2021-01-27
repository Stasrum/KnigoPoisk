import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class Bookcontroller {
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllBooks() {
    return this.http.get(this.path + 'allbooks');
  }
}
