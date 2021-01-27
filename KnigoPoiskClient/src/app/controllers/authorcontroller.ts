import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Author} from '../entities/book';

@Injectable({providedIn: 'root'})
export class Authorcontroller {
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllAuthor() {
    return this.http.get(this.path + 'authors');
  }

  createAuthor(author: Author){
    return this.http.post(this.path + 'author/add', author);
  }
}
