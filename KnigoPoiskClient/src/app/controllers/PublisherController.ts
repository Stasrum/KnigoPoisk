import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publisher} from '../entities/Book';
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class PublisherController {

  constructor(private http: HttpClient) {
  }

  getAllPublisher() {
    return this.http.get(path + 'publishers');
  }

  createPublisher(publisher: Publisher){
    return this.http.post(path + 'publisher/add', publisher);
  }
}
