import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../entities/Book';
import {path} from '../entities/Constant';

@Injectable({providedIn: 'root'})
export class BookController {
  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get(path + 'books?d=1');
  }

  findById(id: number) {
    return this.http.get(path + 'books/' + id)
  }

  add(book: Book) {
    return this.http.post(path + 'api/v1/admin/books/add', book);
  }

  edit(book: Book) {
    return this.http.post(path + 'api/v1/admin/books/edit', book);
  }

  deleteById(id) {
    return this.http.get(path + 'books/delete/' + id);
  }

}
