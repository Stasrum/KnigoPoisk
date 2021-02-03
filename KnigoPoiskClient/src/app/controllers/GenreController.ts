import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Genre} from '../entities/Book';
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class GenreController {

  constructor(private http: HttpClient) {
  }

  getAllGenre() {
    return this.http.get(path + 'genres');
  }

  createGenre(genre: Genre){
    return this.http.post(path + 'genre/add', genre);
  }
}
