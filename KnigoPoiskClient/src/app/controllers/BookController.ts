import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../entities/Book';
import { path } from '../entities/Constant';

@Injectable({providedIn: 'root'})
export class BookController {
  constructor(private http: HttpClient) {
  }

  getAllBooks() {
    return this.http.get(path + 'books');
  }

  createBook(book: Book){
    return this.http.post(path + 'books/add', book);
  }
}
