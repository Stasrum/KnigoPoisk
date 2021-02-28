import {Component, Input, OnInit} from '@angular/core';
import {Book} from "../../utils/entities/Book";
import {BookController} from "../../utils/controllers/BookController";

@Component({
  selector: 'app-book-profile',
  templateUrl: './book-profile.component.html',
  styleUrls: ['./book-profile.component.css']
})
export class BookProfileComponent implements OnInit {
  @Input() id: number;
  public book: Book;
  public comments = new Array();

  constructor(private bookController: BookController) { }

  ngOnInit(): void {
    this.bookController.findById(this.id).subscribe((rec:any) => this.book = rec);
    this.bookController.getComments(this.id).subscribe((rec:any) => {
      this.comments = rec;
      console.log(this.comments);
    })
  }
}
