import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Lang} from '../entities/book';

@Injectable({providedIn: 'root'})
export class Langcontroller{
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllLang() {
    return this.http.get(this.path + 'languages');
  }

  createLang(lang: Lang){
    return this.http.post(this.path + 'language/add', lang);
  }
}
