import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Author} from '../entities/Book';
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class AuthorController {

  constructor(private http: HttpClient) {
  }

  getAllAuthor() {
    return this.http.get(path + 'authors');
  }

  createAuthor(author: Author){
    return this.http.post(path + 'author/create', author);
  }
}
