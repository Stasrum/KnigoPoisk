import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Genre} from '../entities/book';

@Injectable({providedIn: 'root'})
export class Genrecontroller{
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllGenre() {
    return this.http.get(this.path + 'genres');
  }

  createGenre(genre: Genre){
    return this.http.post(this.path + 'genre/add', genre);
  }
}
