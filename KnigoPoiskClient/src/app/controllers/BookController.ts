import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../entities/Book';
import {path} from '../entities/Constant';

@Injectable({providedIn: 'root'})
export class BookController {
  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get(path + 'books');
  }

  findById(id: number) {
    return this.http.get(path + 'books/' + id)
  }

  create(book: Book) {
    return this.http.post(path + 'books/add', book);
  }

  deleteById(id) {
    return this.http.get(path + 'books/delete/' + id);
  }

}
