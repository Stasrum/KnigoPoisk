import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../entities/book';

@Injectable({providedIn: 'root'})
export class Bookcontroller {
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllBooks() {
    return this.http.get(this.path + 'books');
  }

  createBook(book: Book){
    return this.http.post(this.path + 'books/add', book);
  }
}
