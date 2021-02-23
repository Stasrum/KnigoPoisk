import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publisher} from '../entities/Book';
import {path} from "../entities/Constant";

@Injectable({providedIn: 'root'})
export class PublisherController {

  constructor(private http: HttpClient) {
  }

  getAllPublisher() {
    return this.http.get(path + 'api/v1/publishers');
  }

  createPublisher(publisher: Publisher){
    return this.http.post(path + 'api/v1/publisher/create', publisher);
  }
}
