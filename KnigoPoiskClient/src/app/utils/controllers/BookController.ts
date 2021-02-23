import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../entities/Book';
import {path} from '../entities/Constant';

@Injectable({providedIn: 'root'})
export class BookController {
  constructor(private http: HttpClient) {
  }

  getAll(page?: number, size?: number, title?: string, author?: string, genre?: string) {
    if (!page) page = 1;
    if (!size) size = 0;
    if (!author) author = '';
    if (!genre) genre = '';
    if (!title) title = '';
    return this.http.get(path + 'books?p=' + page + '&s=' + size + '&title=' + title + '&nameAuthor=' + author + '&nameGenre=' + genre);
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
    return this.http.get(path + 'api/v1/admin/books/delete/' + id);
  }

}
