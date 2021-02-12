import { Component, OnInit } from '@angular/core';
import {Book} from '../../utils/entities/Book';
import {BookController} from '../../utils/controllers/BookController';

@Component({
  selector: 'app-main',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.css']
})
export class AllBooksComponent implements OnInit {
  public allBooks: Array<Book>;

  constructor(private bookcontroller: BookController) {
  }
  ngOnInit(): void {
    this.bookcontroller.getAll().subscribe((rec: any) => {
      this.allBooks = rec.content;
      console.log(this.allBooks);
    });
  }

  deleteBook(id: number) {
    this.bookcontroller.deleteById(id).subscribe((rec: any) => {
      this.bookcontroller.getAll().subscribe((record: any) => {
        this.allBooks = record.content;
      })
    });
  }


}
