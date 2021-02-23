import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Lang} from '../entities/Book';
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class LangController {

  constructor(private http: HttpClient) {
  }

  getAllLang() {
    return this.http.get(path + 'api/v1/languages');
  }

  createLang(lang: Lang){
    return this.http.post(path + 'api/v1/language/create', lang);
  }
}
