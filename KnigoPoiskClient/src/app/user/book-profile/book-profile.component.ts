import {Component, Input, OnInit} from '@angular/core';
import {Book, Comment} from '../../utils/entities/Book';
import {BookController} from '../../utils/controllers/BookController';
import {path} from '../../utils/entities/Constant';
import {CommentController} from '../../utils/controllers/CommentController';

@Component({
  selector: 'app-book-profile',
  templateUrl: './book-profile.component.html',
  styleUrls: ['./book-profile.component.css']
})
export class BookProfileComponent implements OnInit {
  @Input() id: number;
  public commentId: number;
  public book: Book;
  public comments = [];
  public path = path;
  public visible = false;
  public textComment: string;
  private newComment: Comment;
  public user = localStorage.getItem('user');

  constructor(private bookController: BookController,
              private commentController: CommentController) {
  }

  ngOnInit(): void {
    this.bookController.findById(this.id).subscribe((rec: any) => this.book = rec);
    this.commentController.getComments(this.id).subscribe((rec: any) => this.comments = rec);
  }

  addNewComment() {
    this.newComment = new Comment(this.id, null, this.textComment, Number(localStorage.getItem('id')), localStorage.getItem('user'));
    this.commentController.addComment(this.newComment).subscribe(error => {
      this.commentController.getComments(this.id).subscribe((rec: any) => this.comments = rec);
    });
  }

  delComment(id) {
    this.commentController.delComment(id).subscribe(error => {
      this.commentController.getComments(this.id).subscribe((rec: any) => this.comments = rec);
    });
  }

  editNewComment() {
    this.newComment = new Comment(this.id, this.commentId, this.textComment, Number(localStorage.getItem('id')), localStorage.getItem('user'));
    this.commentController.editComment(this.newComment).subscribe(error => {
      this.commentController.getComments(this.id).subscribe((rec: any) => this.comments = rec);
    });
  }
}
