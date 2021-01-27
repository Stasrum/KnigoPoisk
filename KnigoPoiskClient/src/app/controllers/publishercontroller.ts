import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Publisher} from '../entities/book';

@Injectable({providedIn: 'root'})
export class Publishercontroller{
  path = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllPublisher() {
    return this.http.get(this.path + 'publishers');
  }

  createPublisher(publisher: Publisher){
    return this.http.post(this.path + 'publisher/add', publisher);
  }
}
