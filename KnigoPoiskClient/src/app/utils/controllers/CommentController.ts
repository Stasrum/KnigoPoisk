import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {path} from '../entities/Constant';
import {Comment} from '../entities/Book';

@Injectable({providedIn: 'root'})
export class CommentController {
  constructor(private http: HttpClient) {
  }
  getComments(id) {
    return this.http.get(path + 'api/v1/comments/book/' + id);
  }

  addComment(comment: Comment){
    return this.http.post(path + 'api/v1/comments/create', comment);
  }

  delComment(id: number){
    return this.http.delete(path + 'api/v1/comments/delete/' + id);
  }

  editComment(comment: Comment){
    return this.http.put(path + 'api/v1/comments/update', comment);
  }
}
